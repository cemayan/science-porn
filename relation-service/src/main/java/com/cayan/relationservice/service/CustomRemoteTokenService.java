package com.cayan.relationservice.service;



import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cayan.relationservice.util.CustomTokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

public class CustomRemoteTokenService extends RemoteTokenServices {

    private RemoteTokenServices remoteTokenServices;
    private final CustomTokenUtils customTokenUtils;

    public CustomRemoteTokenService(RemoteTokenServices remoteTokenServices) {
        this.remoteTokenServices = remoteTokenServices;
        this.customTokenUtils = new CustomTokenUtils();
    }

    @Autowired
    RedisTemplate redisTemplate;


    @Value("${auth-server.url}")
    private String authEndpoint;


    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {

        DecodedJWT jwt = null;
        String userId = "";

        try {
            jwt = JWT.decode(accessToken);
            userId = String.valueOf(jwt.getClaim("userId").asLong());
        } catch (JWTDecodeException jwtDecodeException) {
            //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }


        ObjectMapper objectMapper = new ObjectMapper();

        Boolean check = redisTemplate.opsForHash().hasKey("token-map", userId);
        if (check) {

            String strMap = (String) redisTemplate.opsForHash().get("token-map", userId);
            String exp = "";

            Map<String, Object> tokenMap = null;
            try {
                tokenMap = objectMapper.readValue(strMap, Map.class);
                exp = tokenMap.get("exp").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LocalDateTime localDateTimeExp=  LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(exp)), ZoneId.systemDefault());

            if(LocalDateTime.now().isAfter(localDateTimeExp)) {
                redisTemplate.opsForHash().delete("token-map",userId);
            }

            DefaultAccessTokenConverter userTokenConverter = new DefaultAccessTokenConverter();
            return userTokenConverter.extractAuthentication(tokenMap);
        } else {

            MultiValueMap<String, String> formData = new LinkedMultiValueMap();
            formData.add("token", accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", customTokenUtils.getAuthorizationHeader("adminapp", "password"));
            Map<String, Object> map = customTokenUtils.postForMap(authEndpoint + "/oauth/check_token", formData, headers);

            if (map.containsKey("error")) {
                throw new InvalidTokenException(accessToken);
            } else if (!Boolean.TRUE.equals(map.get("active"))) {
                throw new InvalidTokenException(accessToken);
            } else {

                try {
                    redisTemplate.opsForHash().put("token-map", userId, objectMapper.writeValueAsString(map));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return remoteTokenServices.loadAuthentication(accessToken);
            }
        }
    }

}

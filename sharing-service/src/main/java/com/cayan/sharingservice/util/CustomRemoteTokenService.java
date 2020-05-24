package com.cayan.sharingservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.Cacheable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomRemoteTokenService extends RemoteTokenServices {

    private RemoteTokenServices remoteTokenServices;

    public CustomRemoteTokenService(RemoteTokenServices remoteTokenServices) {
        this.remoteTokenServices = remoteTokenServices;
    }

    @Autowired
    RedisTemplate redisTemplate;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${auth-server.url}")
    private String authEndpoint;


    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {

        //TODO: Keylere TTL eklenecek token süresi dolunca silinmesi lazım

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
            Map<String, Object> tokenMap = null;
            try {
                tokenMap = objectMapper.readValue(strMap, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            DefaultAccessTokenConverter userTokenConverter = new DefaultAccessTokenConverter();
            return userTokenConverter.extractAuthentication(tokenMap);
        } else {

            MultiValueMap<String, String> formData = new LinkedMultiValueMap();
            formData.add("token", accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", this.getAuthorizationHeader("adminapp", "password"));
            Map<String, Object> map = this.postForMap(authEndpoint + "/oauth/check_token", formData, headers);

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

    private String getAuthorizationHeader(String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            this.logger.warn("Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 error.");
        }

        String creds = String.format("%s:%s", clientId, clientSecret);

        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }


        Map map = (Map) restTemplate.exchange(path, HttpMethod.POST, new HttpEntity(formData, headers), Map.class, new Object[0]).getBody();
        return map;
    }
}

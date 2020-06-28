package com.cayan.sharingservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cayan.common.model.CustomPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SecurityDetails {

    @Autowired
    ObjectMapper objectMapper;

    public CustomPrincipal getAuthenticatedUser() throws JsonProcessingException {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String tokenValue = ((OAuth2AuthenticationDetails)((OAuth2Authentication) authentication).getDetails()).getTokenValue();
        DecodedJWT jwt = JWT.decode(tokenValue);

        return new CustomPrincipal(jwt.getClaim("userId").asLong(), jwt.getClaim("user_name").asString(), jwt.getClaim("email").asString());
    }
}

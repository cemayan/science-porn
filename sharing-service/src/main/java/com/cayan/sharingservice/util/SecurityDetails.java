package com.cayan.sharingservice.util;

import com.cayan.common.model.CustomPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SecurityDetails {

    @Autowired
    ObjectMapper objectMapper;

    public  CustomPrincipal getAuthenticatedUser() throws JsonProcessingException {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> map =
                objectMapper.convertValue(authentication.getDetails(), Map.class);

        Jwt jwt = JwtHelper.decode((String) map.get("tokenValue"));
        JsonNode jsonNode =  objectMapper.readTree(jwt.getClaims());
        CustomPrincipal customPrincipal = new CustomPrincipal(jsonNode.get("userId").asLong(),jsonNode.get("user_name").asText(), jsonNode.get("email").asText());

        return  customPrincipal;
    }
}

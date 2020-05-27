package com.cayan.contentservice.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class CustomTokenUtils {

    private RestTemplate restTemplate = new RestTemplate();

    public String getAuthorizationHeader(String clientId, String clientSecret) {

        String creds = String.format("%s:%s", clientId, clientSecret);

        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    public Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        Map map = (Map) restTemplate.exchange(path, HttpMethod.POST, new HttpEntity(formData, headers), Map.class, new Object[0]).getBody();
        return map;
    }
}

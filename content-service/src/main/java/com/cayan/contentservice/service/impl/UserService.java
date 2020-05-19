package com.cayan.contentservice.service.impl;

import com.cayan.common.dto.UserDTO;

import com.cayan.contentservice.config.RibbonConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RibbonClient(name = "user-service", configuration = RibbonConfiguration.class)
public class UserService {

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "userfallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
    public UserDTO getUser(Long userId) {
        return this.restTemplate.getForObject("http://user-service/user/"+ userId, UserDTO.class);
    }

    private UserDTO userfallbackMethod(Long userId) {
        return  new UserDTO();
    }
}

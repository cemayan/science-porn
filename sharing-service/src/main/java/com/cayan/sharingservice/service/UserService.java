package com.cayan.sharingservice.service;

import com.cayan.sharingservice.config.RibbonConfiguration;
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
    public String getUser(Long userId) {
        return this.restTemplate.getForObject("http://user-service/api/user/"+ userId, String.class);
    }

    private String userfallbackMethod(Long userId) {
        return "Sorry!";
    }
}

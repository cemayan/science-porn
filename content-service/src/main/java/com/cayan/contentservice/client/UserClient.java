package com.cayan.contentservice.client;

import com.cayan.contentservice.config.ClientConfiguration;
import com.cayan.contentservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/user/getAllUser/")
    List<User> getUsers();
}

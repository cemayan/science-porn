package com.cayan.sharingservice.controller;


import com.cayan.sharingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SharingController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String getUser(@PathVariable("userId") Long userId) {
        return  userService.getUser(userId);
    }

}

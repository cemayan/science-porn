package com.cayan.userservice.web.rest;


import com.cayan.userservice.aop.LogExecutionTime;
import com.cayan.userservice.aop.LogRequest;
import com.cayan.userservice.dto.UserDTO;
import com.cayan.userservice.entity.User;
import com.cayan.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Api
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @LogExecutionTime
    @LogRequest
    @ApiOperation(value = "Search a person with an ID", response = UserDTO.class)
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET,  produces = "application/json; charset=UTF-8")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Long userId) {
        return  userService.getUser(userId)
                .map(resp -> ResponseEntity.ok().body(resp))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));
    }
}

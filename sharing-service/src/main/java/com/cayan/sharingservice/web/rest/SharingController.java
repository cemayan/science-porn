package com.cayan.sharingservice.web.rest;


import com.cayan.common.dto.ScienceContentDTO;
import com.cayan.common.dto.UserDTO;
import com.cayan.sharingservice.aop.LogExecutionTime;
import com.cayan.sharingservice.aop.LogRequest;
import com.cayan.sharingservice.service.IKafkaService;
import com.cayan.sharingservice.service.IUserService;
import com.cayan.sharingservice.util.SecurityDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Api
@RestController
public class SharingController {

    @Autowired
    IUserService userService;

    @Autowired
    IKafkaService kafkaService;

    @Autowired
    SecurityDetails securityDetails;

    @LogRequest
    @LogExecutionTime
    @ApiOperation(value = "Search a person with an ID", response = UserDTO.class)
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable("userId") Long userId) {
        return  userService.getUser(userId);
    }


    @ApiOperation(value = "Write contents topic")
    @PostMapping("/science-content")
    @PreAuthorize("hasAuthority('role_user')")
    public void writeContentsTopic(@RequestBody ScienceContentDTO scienceContentDTO) throws JsonProcessingException {

        if(!securityDetails.getAuthenticatedUser().getUserId().equals(scienceContentDTO.getUserId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        kafkaService.sendContent(scienceContentDTO);
    }
}

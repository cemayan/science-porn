package com.cayan.sharingservice.web.rest;


import com.cayan.common.dto.UserDTO;
import com.cayan.sharingservice.aop.LogExecutionTime;
import com.cayan.sharingservice.aop.LogRequest;
import com.cayan.sharingservice.service.IKafkaService;
import com.cayan.sharingservice.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class SharingController {

    @Autowired
    IUserService userService;

    @Autowired
    IKafkaService kafkaService;

    @LogRequest
    @LogExecutionTime
    @ApiOperation(value = "Search a person with an ID", response = UserDTO.class)
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable("userId") Long userId) {
        return  userService.getUser(userId);
    }


    @ApiOperation(value = "Write contents topic")
    @PostMapping("/sendTopic")
    public void writeContentsTopic(@RequestBody String str) {
        kafkaService.sendContent(str);
    }
}

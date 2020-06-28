package com.cayan.relationservice.web.rest;


import com.cayan.relationservice.aop.LogExecutionTime;
import com.cayan.relationservice.aop.LogRequest;
import com.cayan.relationservice.dto.RelationDTO;
import com.cayan.relationservice.service.RelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Api
@RestController
public class RelationController {

    @Autowired
    RelationService relationService;

    @LogExecutionTime
    @LogRequest
    @ApiOperation(value = "Search a person with an ID", response = RelationDTO.class)
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET,  produces = "application/json; charset=UTF-8")
    public ResponseEntity<RelationDTO> getRelation(@PathVariable("userId") Long userId) {
        return  relationService.getRelationByUserId(userId)
                .map(resp -> ResponseEntity.ok().body(resp))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User relation does not exist"));
    }

}

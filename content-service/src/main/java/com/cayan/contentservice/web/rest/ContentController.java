package com.cayan.contentservice.web.rest;

import com.cayan.contentservice.model.AuthorBy;
import com.cayan.contentservice.model.LikedBy;
import com.cayan.contentservice.model.ScienceContent;
import com.cayan.contentservice.service.IContentService;
import com.cayan.contentservice.service.impl.KafkaConsumerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class ContentController {

    @Autowired
    IContentService contentService;

    @Autowired
    KafkaConsumerService kafkaConsumerService;


    @PostMapping("/getMyContent")
    @PreAuthorize("hasAuthority('role_user')")
    public List<AuthorBy> getMyContents(){
        return  contentService.getAllMyContents();
    }


    @PostMapping("/getTop5")
    @PreAuthorize("hasAuthority('role_user')")
    public List<ScienceContent> getTop5(){
        return  contentService.getTop5();
    }

}

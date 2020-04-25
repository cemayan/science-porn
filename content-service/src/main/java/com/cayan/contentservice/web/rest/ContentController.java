package com.cayan.contentservice.web.rest;

import com.cayan.contentservice.service.IContentService;
import com.cayan.contentservice.service.impl.KafkaConsumerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class ContentController {

    @Autowired
    IContentService contentService;

    @Autowired
    KafkaConsumerService kafkaConsumerService;

}

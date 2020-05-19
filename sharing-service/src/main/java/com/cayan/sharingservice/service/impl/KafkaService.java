package com.cayan.sharingservice.service.impl;


import com.cayan.common.dto.ScienceContentDTO;
import com.cayan.sharingservice.service.IKafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaService implements IKafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void sendContent(ScienceContentDTO contentDTO) throws JsonProcessingException {

        String str_content =  objectMapper.writeValueAsString(contentDTO);

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic, str_content);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + str_content +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + str_content + "] due to : " + ex.getMessage());
            }
        });
    }
}

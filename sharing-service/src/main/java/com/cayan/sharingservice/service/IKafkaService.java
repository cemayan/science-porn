package com.cayan.sharingservice.service;

import com.cayan.common.dto.ScienceContentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IKafkaService {

   void sendContent(ScienceContentDTO msg) throws JsonProcessingException;
}

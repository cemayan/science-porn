package com.cayan.sharingservice.service;

import com.cayan.common.model.dto.UserDTO;

public interface IKafkaService {

   void sendContent(String msg);
}

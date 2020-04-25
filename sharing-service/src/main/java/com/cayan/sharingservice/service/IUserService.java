package com.cayan.sharingservice.service;

import com.cayan.common.model.dto.UserDTO;

public interface IUserService {

    UserDTO getUser(Long userId);
}

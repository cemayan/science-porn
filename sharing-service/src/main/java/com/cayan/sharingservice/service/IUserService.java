package com.cayan.sharingservice.service;

import com.cayan.common.dto.UserDTO;

public interface IUserService {

    UserDTO getUser(Long userId);
}

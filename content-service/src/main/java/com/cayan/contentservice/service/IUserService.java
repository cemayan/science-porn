package com.cayan.contentservice.service;

import com.cayan.common.dto.UserDTO;

public interface IUserService {

    UserDTO getUserFromAuthDb(Long userId);
}

package com.cayan.userservice.service;

import com.cayan.common.dto.UserDTO;
import com.cayan.userservice.dto.LoginDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUser(Long userId);
}

package com.cayan.userservice.service;

import com.cayan.userservice.dto.UserDTO;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUser(Long userId);
}

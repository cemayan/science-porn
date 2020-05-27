package com.cayan.userservice.service.impl;


import com.cayan.common.dto.UserDTO;
import com.cayan.userservice.dto.LoginDTO;
import com.cayan.userservice.dto.mapper.UserMapper;
import com.cayan.userservice.repository.UserRepository;
import com.cayan.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;


    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUser(Long userId) {
        return  userMapper.convertToDto(userRepository.findById(userId));
    }

}

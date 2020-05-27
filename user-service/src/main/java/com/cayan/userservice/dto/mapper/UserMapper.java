package com.cayan.userservice.dto.mapper;

import com.cayan.common.dto.UserDTO;
import com.cayan.userservice.entity.User;
import com.cayan.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class UserMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;


    public Optional<UserDTO> convertToDto(Optional<User> user) {
        if(user.isPresent()) {
            return Optional.of(modelMapper.map(user.get(), UserDTO.class));
        }
        return  Optional.empty();
    }

    public User convertToEntity(UserDTO userDTO) throws ParseException {
        User user = modelMapper.map(userDTO, User.class);

//        if (user.getId() != null) {
//             user = userService.getUser(userDTO.getId());
//        }
        return user;
    }


}

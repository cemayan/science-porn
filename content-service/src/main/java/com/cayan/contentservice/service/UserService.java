package com.cayan.contentservice.service;

import com.cayan.contentservice.client.UserClient;
import com.cayan.contentservice.interfaces.IUserService;
import com.cayan.contentservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  implements IUserService {

    @Autowired
    UserClient userClient;

    @Override
    public List<User> getAllUsers() {
        return userClient.getUsers();
    }
}

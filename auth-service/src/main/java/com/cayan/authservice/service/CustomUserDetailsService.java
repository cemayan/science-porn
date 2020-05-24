package com.cayan.authservice.service;

import java.util.Optional;

import com.cayan.authservice.entity.User;
import com.cayan.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input) {
        Optional<User> user = null;

        if (input.contains("@"))
            user = userRepository.findByEmail(input);
        else
            user = userRepository.findByUsername(input);

        if (!user.isPresent())
            throw new BadCredentialsException("Bad credentials");

        new AccountStatusUserDetailsChecker().check(user.get());

        user.map(x-> {
            x.setUsername(String.valueOf(x.getId()));
            return x;
        });

        return user.get();
    }

}
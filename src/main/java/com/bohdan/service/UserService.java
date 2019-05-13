package com.bohdan.service;

import com.bohdan.config.UserRegistrationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bohdan.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationConfiguration registration);

    List<User> findAllUsers();

    Optional<User> findUserById(int id);
}

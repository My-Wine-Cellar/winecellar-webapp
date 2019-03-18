package com.cellar.wine.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    List<User> findAll();

    void createUser(User user);

    void createAdmin(User user);

    void deleteUserById(Long userId);

}

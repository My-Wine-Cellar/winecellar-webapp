package com.cellar.wine.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void createUser(User user);

    void createAdmin(User user);
}

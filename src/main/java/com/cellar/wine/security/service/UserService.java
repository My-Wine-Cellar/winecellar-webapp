/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package com.cellar.wine.security.service;

import com.cellar.wine.security.model.User;
import com.cellar.wine.security.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User findUserByEmail(String email);

    List<User> findAll();

    void createUser(User user) throws Exception;

    void deleteUserById(Long userId);

    void registerNewUserAccount(UserDto accountDto) throws Exception;

    String createVerificationToken(User user);

    String validateVerificationToken(String token);
}

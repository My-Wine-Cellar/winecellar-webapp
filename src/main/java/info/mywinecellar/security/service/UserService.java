/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.service;

import info.mywinecellar.security.model.User;
import info.mywinecellar.security.model.UserRegisterDto;
import info.mywinecellar.security.model.UserResetDto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * @param username username
     * @return User
     */
    User findByUsername(String username);

    /**
     * @param email email
     * @return User
     */
    User findUserByEmail(String email);

    /**
     * @return User list
     */
    List<User> findAll();

    /**
     * @param user user
     * @throws Exception exception
     */
    void createUser(User user) throws Exception;

    /**
     * @param userId userId
     */
    void deleteUserById(Long userId);

    /**
     * @param accountDto accountDto
     * @throws Exception exception
     */
    void registerNewUserAccount(@Valid UserRegisterDto accountDto) throws Exception;

    /**
     * @param user user
     * @return String
     */
    String createVerificationToken(User user);

    /**
     * @param token token
     * @return String
     */
    String validateVerificationToken(String token);

    /**
     * @param userDto user
     */
    void resetPassword(UserResetDto userDto);

    /**
     * @param username username
     * @return true
     */
    boolean usernameExists(String username);

    /**
     * @param email email
     * @return true
     */
    boolean emailExists(String email);
}

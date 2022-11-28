/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.service;

import info.mywinecellar.dto.UserRegisterDto;
import info.mywinecellar.dto.UserResetDto;
import info.mywinecellar.exception.EmailException;
import info.mywinecellar.exception.PasswordException;
import info.mywinecellar.exception.UsernameException;
import info.mywinecellar.model.Authority;
import info.mywinecellar.model.User;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Find a user by their id
     *
     * @param id the id of the user
     * @return {@link User}
     */
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Find a user by their username
     *
     * @param username the username of the user
     * @return {@link User}
     */
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Load a user their username
     *
     * @param username the username of the user
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException the exception to be thrown
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username and password");
        }
        user.setLastLogin(new Date(System.currentTimeMillis()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    /**
     * Find all users
     *
     * @return the list of {@link User}'s
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Regiser a new user
     *
     * @param accountDto the object {@link UserRegisterDto} to be used for registration
     */
    @Transactional
    public void registerNewUserAccount(@Valid UserRegisterDto accountDto) {

        if (!accountDto.getPassword().equals(accountDto.getMatchingPassword())) {
            throw new PasswordException();
        }

        if (this.emailExists(accountDto.getEmail())) {
            throw new EmailException();
        }

        if (this.usernameExists(accountDto.getUserName())) {
            throw new UsernameException();
        }

        User user = User.createUser(accountDto);
        user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        user.setAuthorities(Collections.singleton(authorityRepository.findByAuthority("ROLE_USER")));
        userRepository.save(user);
    }

    /**
     * Check to see if the email already exists
     *
     * @param email the email to check
     * @return true if the email exists
     */
    public boolean emailExists(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }

    /**
     * Check to see if the username already exists
     *
     * @param username the username to check
     * @return true if the username exists
     */
    public boolean usernameExists(String username) {
        User user = userRepository.findUserByUsername(username);
        return user != null;
    }

    /**
     * Reset a users password
     *
     * @param userDto {@link UserResetDto}
     */
    public void resetPassword(UserResetDto userDto) {
        User user = userRepository.findUserByUsername(userDto.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}

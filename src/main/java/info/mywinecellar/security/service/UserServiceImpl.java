/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.service;

import info.mywinecellar.security.exception.EmailException;
import info.mywinecellar.security.exception.PasswordException;
import info.mywinecellar.security.exception.UsernameException;
import info.mywinecellar.security.model.Authority;
import info.mywinecellar.security.model.User;
import info.mywinecellar.security.model.UserDto;
import info.mywinecellar.security.model.VerificationToken;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Inject private UserRepository userRepository;
    @Inject private AuthorityRepository authorityRepository;
    @Inject private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Inject private VerificationTokenRepository tokenRepository;

    private static final String TOKEN_INVALID = "invalidToken";
    private static final String TOKEN_EXPIRED = "expired";
    private static final String TOKEN_VALID = "valid";

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username and password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId.intValue());
    }

    @Override
    @Deprecated
    public void createUser(User user) throws MessagingException {
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAuthorities(Collections.singleton(authorityRepository.findByAuthority("ROLE_USER")));
        userRepository.save(user);
        //emailService.sendRegistrationEmail(user.getEmail());
    }

    @Transactional
    @Override
    public void registerNewUserAccount(@Valid UserDto accountDto) throws MessagingException {

        if (!accountDto.getPassword().equals(accountDto.getMatchingPassword())) {
            throw new PasswordException();
        }

        if (this.emailExists(accountDto.getEmail())) {
            throw new EmailException();
        }

        if (this.usernameExists(accountDto.getUserName())) {
            throw new UsernameException();
        }

        final User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setUsername(accountDto.getUserName());
        user.setEnabled(true);
        user.setAuthorities(Collections.singleton(authorityRepository.findByAuthority("ROLE_USER")));
        userRepository.save(user);
        //String userToken = this.createVerificationToken(user);
        //emailService.sendVerificationEmail(user.getEmail(), userToken);
    }

    @Override
    public String createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
        return token;
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirationDate()
                .getTime() -
                cal.getTime()
                        .getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return TOKEN_VALID;
    }

    private boolean emailExists(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }

    private boolean usernameExists(String username) {
        User user = userRepository.findUserByUsername(username);
        return user != null;
    }
}

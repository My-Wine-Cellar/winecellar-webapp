package com.cellar.wine.security.service;

import com.cellar.wine.security.model.Authority;
import com.cellar.wine.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}

package com.cellar.wine.security.service;

import com.cellar.wine.security.model.User;
import com.cellar.wine.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}

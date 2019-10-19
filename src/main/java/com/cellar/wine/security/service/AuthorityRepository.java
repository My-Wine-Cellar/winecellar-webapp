package com.cellar.wine.security.service;

import com.cellar.wine.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}

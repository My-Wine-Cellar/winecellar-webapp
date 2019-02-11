package com.cellar.wine.security;

import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthoritySerivce {

    private AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void createAuthority(Authority authority) {
        authority.setAuthority(authority.getAuthority());
        authority.setUsername(authority.getUsername());
        authorityRepository.save(authority);
    }

}

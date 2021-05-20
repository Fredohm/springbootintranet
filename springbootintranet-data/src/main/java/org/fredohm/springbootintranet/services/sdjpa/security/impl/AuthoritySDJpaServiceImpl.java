package org.fredohm.springbootintranet.services.sdjpa.security.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.security.Authority;
import org.fredohm.springbootintranet.repositories.security.AuthorityRepository;
import org.fredohm.springbootintranet.services.sdjpa.security.AuthorityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Profile({"dev", "prod", "springdatajpa"})
public class AuthoritySDJpaServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Transactional
    @Override
    public List<Authority> findAll() {
        List<Authority> authorities = new ArrayList<>();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }

    @Transactional
    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    @Override
    public void delete(Authority authority) {
        authorityRepository.delete(authority);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        authorityRepository.deleteById(id);
    }
}

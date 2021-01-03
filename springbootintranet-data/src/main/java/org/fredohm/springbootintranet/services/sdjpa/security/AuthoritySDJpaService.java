package org.fredohm.springbootintranet.services.sdjpa.security;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.security.Authority;
import org.fredohm.springbootintranet.repositories.security.AuthorityRepository;
import org.fredohm.springbootintranet.services.security.AuthorityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Profile({"dev", "prod", "springdatajpa"})
public class AuthoritySDJpaService implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Transactional
    @Override
    public Set<Authority> findAll() {
        Set<Authority> authorities = new HashSet<>();
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

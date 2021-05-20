package org.fredohm.springbootintranet.services.sdjpa.security.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.repositories.security.RoleRepository;
import org.fredohm.springbootintranet.services.sdjpa.security.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Profile({"dev", "prod", "springdatajpa"})
public class RoleSDJpaServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Transactional
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}

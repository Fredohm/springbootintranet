package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.repositories.AppUserRepository;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"dev", "prod", "springdatajpa"})
public class UserSDJpaService implements UserService {

    private final AppUserRepository userRepository;

    public UserSDJpaService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Set<AppUser> findAll() {

        Set<AppUser> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(AppUser user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

package org.fredohm.springbootintranet.services.sdjpa.security;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.repositories.security.UserRepository;
import org.fredohm.springbootintranet.services.security.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Profile({"dev", "prod", "springdatajpa"})
public class UserSDJpaService implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

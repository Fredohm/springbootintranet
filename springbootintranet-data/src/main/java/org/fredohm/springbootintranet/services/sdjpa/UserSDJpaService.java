package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.repositories.UserRepository;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"dev", "prod", "springdatajpa"})
public class UserSDJpaService implements UserService {

    private final UserRepository userRepository;

    public UserSDJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return userRepository.findById(id).orElse(null);
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

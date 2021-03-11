package org.fredohm.springbootintranet.repositories.security;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> save(UserDTO userDTO);

    List<User> findAllByOrderByUsernameAsc();
}

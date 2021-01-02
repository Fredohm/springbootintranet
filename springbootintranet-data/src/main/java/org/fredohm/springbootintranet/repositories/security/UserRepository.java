package org.fredohm.springbootintranet.repositories.security;

import org.fredohm.springbootintranet.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

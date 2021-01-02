package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

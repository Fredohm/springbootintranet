package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}

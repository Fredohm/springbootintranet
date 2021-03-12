package org.fredohm.springbootintranet.services.sdjpa.security;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.services.sdjpa.CrudService;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

}

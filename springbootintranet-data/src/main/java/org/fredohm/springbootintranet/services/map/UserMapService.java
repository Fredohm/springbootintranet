package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.services.UserService;

import java.util.Set;

public class UserMapService extends AbstractMapService<User, Long> implements UserService {

    @Override
    public Set<User> findAll() {

        return super.findAll();
    }

    @Override
    public User findById(Long id) {

        return super.findById(id);
    }

    @Override
    public User save(User user) {

        return super.save(user);
    }

    @Override
    public void delete(User user) {

        super.delete(user);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}

package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class UserMapService extends AbstractMapService<User, Long> implements UserService {

    @Transactional
    @Override
    public Set<User> findAll() {

        return super.findAll();
    }

    @Transactional
    @Override
    public User findById(Long id) {

        return super.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {

        return super.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {

        super.delete(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}

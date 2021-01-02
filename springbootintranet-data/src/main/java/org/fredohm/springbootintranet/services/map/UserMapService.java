package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class UserMapService extends AbstractMapService<AppUser, Long> implements UserService {

    @Transactional
    @Override
    public Set<AppUser> findAll() {

        return super.findAll();
    }

    @Transactional
    @Override
    public AppUser findById(Long id) {

        return super.findById(id);
    }

    @Transactional
    @Override
    public AppUser save(AppUser user) {

        return super.save(user);
    }

    @Transactional
    @Override
    public void delete(AppUser user) {

        super.delete(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}

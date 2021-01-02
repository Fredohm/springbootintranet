package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapServiceTest {

    UserMapService userMapService;
    final Long userId = 1L;

    @BeforeEach
    void setUp() {
        userMapService = new UserMapService();
        userMapService.save(AppUser.builder().id(userId).build());
    }

    @Test
    void findAll() {
        Set<AppUser> users = userMapService.findAll();
        assertEquals(1, users.size());
    }

    @Test
    void findById() {
        AppUser user = userMapService.findById(userId);
        assertEquals(userId, user.getId());
    }

    @Test
    void save() {
        Long id = 2L;
        AppUser user2 = AppUser.builder().id(2L).build();
        AppUser savedUser = userMapService.save(user2);
        assertEquals(id, savedUser.getId());
    }

    @Test
    void delete() {
        userMapService.delete(userMapService.findById(userId));
        assertEquals(0, userMapService.findAll().size());
    }

    @Test
    void deleteById() {
        userMapService.deleteById(userId);
        assertEquals(0, userMapService.findAll().size());
    }
}
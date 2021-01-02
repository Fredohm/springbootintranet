package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSDJpaServiceTest {

    final Long userId = 1L;

    @Mock
    AppUserRepository userRepository;

    @Mock
    MeetingRepository meetingRepository;

    @InjectMocks
    UserSDJpaService service;

    AppUser returnUser;

    @BeforeEach
    void setUp() {
        returnUser = AppUser.builder().id(userId).build();
    }

    @Test
    void findAll() {
        List<AppUser> returnUsers = new ArrayList<>();
        returnUsers.add(AppUser.builder().id(1L).build());
        returnUsers.add(AppUser.builder().id(2L).build());
        when(userRepository.findAll()).thenReturn(returnUsers);

        Set<AppUser> users = service.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());

    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(returnUser));

        AppUser user = service.findById(userId);
        assertNotNull(user);
    }

    @Test
    void findByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        AppUser user = service.findById(userId);
        assertNull(user);
    }

    @Test
    void save() {
        AppUser userToSave = AppUser.builder().id(userId).build();
        when(userRepository.save(any())).thenReturn(returnUser);

        AppUser savedUser = service.save(userToSave);
        assertNotNull(savedUser);
        verify(userRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnUser);
        verify(userRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(userId);
        verify(userRepository).deleteById(anyLong());
    }
}
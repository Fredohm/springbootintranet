package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSDJpaServiceTest {

    final Long userId = 1L;

    @Mock
    UserRepository userRepository;

    @Mock
    MeetingRepository meetingRepository;

    @InjectMocks
    UserSDJpaService service;

    User returnUser;

    @BeforeEach
    void setUp() {
        returnUser = User.builder().id(userId).build();
    }

    @Test
    void findAll() {
        Set<User> returnUsers = new HashSet<>();
        returnUsers.add(User.builder().id(1L).build());
        returnUsers.add(User.builder().id(2L).build());
        when(userRepository.findAll()).thenReturn(returnUsers);

        Set<User> users = service.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());

    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(returnUser));

        User user = service.findById(userId);
        assertNotNull(user);
    }

    @Test
    void findByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        User user = service.findById(userId);
        assertNull(user);
    }

    @Test
    void save() {
        User userToSave = User.builder().id(userId).build();
        when(userRepository.save(any())).thenReturn(returnUser);

        User savedUser = service.save(userToSave);
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
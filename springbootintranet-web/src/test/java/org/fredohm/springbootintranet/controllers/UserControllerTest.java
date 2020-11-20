package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    Set<User> users;

    @BeforeEach
    void setUp() {
        users = new HashSet<>();
        users.add(User.builder().id(1L).build());
        users.add(User.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void list() throws Exception {
        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attribute("users", hasSize(2)));
    }

    @Test
    void display() throws Exception {
        mockMvc.perform(get("/user/display/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("user/display"));
    }

    @Test
    void addForm() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add-form"));
    }
}
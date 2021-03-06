package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.controllers.mvc.MeetingController;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.sdjpa.MeetingSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class MeetingControllerTest {

    @Mock
    MeetingSDJpaService meetingService;

    @InjectMocks
    MeetingController meetingController;

    MockMvc mockMvc;

    Set<Meeting> meetings;

    @BeforeEach
    void setUp() {
        meetings = new HashSet<>();
        meetings.add(Meeting.builder().id(1L)
                .meetingRoom(MeetingRoom.builder().id(1L).name("Test1").build())
                .build());
        meetings.add(Meeting.builder().id(2L)
                .meetingRoom(MeetingRoom.builder().id(2L).name("Test2").build())
                .build());

        mockMvc = MockMvcBuilders.standaloneSetup(meetingController).build();
    }

    @Disabled
    @Test
    void list() throws Exception {
        //when(meetingService.findAll()).thenReturn(meetings);

        mockMvc.perform(get("/meeting/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("meeting/list"))
                .andExpect(model().attribute("meetings", hasSize(2)));
    }

    @Test
    void display() throws Exception {
        mockMvc.perform(get("/meeting/display/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("meeting/display"));
    }

    @Test
    @Disabled
    void addForm() throws Exception {
        mockMvc.perform(get("/meeting/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("meeting/add-form"));
    }
}
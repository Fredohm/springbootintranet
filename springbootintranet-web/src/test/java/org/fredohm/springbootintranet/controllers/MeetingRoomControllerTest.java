package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.controllers.mvc.MeetingRoomController;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.fredohm.springbootintranet.services.sdjpa.MeetingRoomService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MeetingRoomControllerTest {

    @Mock
    MeetingRoomService meetingRoomService;

    @InjectMocks
    MeetingRoomController meetingRoomController;

    MockMvc mockMvc;

    Set<MeetingRoom> meetingRooms;

    @BeforeEach
    void setUp() {
        meetingRooms = new HashSet<>();
        meetingRooms.add(MeetingRoom.builder().id(1L).build());
        meetingRooms.add(MeetingRoom.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(meetingRoomController).build();
    }

    @Disabled
    @Test
    void list() throws Exception {
        when(meetingRoomService.findAll()).thenReturn(meetingRooms);

        mockMvc.perform(get("/meeting-room/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("meeting-room/list"))
                .andExpect(model().attribute("meetingRooms", hasSize(2)));
    }

    @Test
    void display() throws Exception{
        mockMvc.perform(get("/meeting-room/display/{id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("meeting-room/display"));
    }

    @Test
    public void displayMeetingRoomNotFound() throws Exception {
        when(meetingRoomService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/meeting-room/display/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }

    @Test
    void addForm() throws Exception {
        mockMvc.perform(get("/meeting-room/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("meeting-room/meeting-room-form"));
    }
}
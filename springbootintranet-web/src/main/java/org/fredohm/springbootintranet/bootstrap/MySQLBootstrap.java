package org.fredohm.springbootintranet.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.AppUserService;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Slf4j
@Component
@Profile({"dev", "prod", "springdatajpa"})
public class MySQLBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AppUserService userService;
    private final MeetingRoomService meetingRoomService;

    public MySQLBootstrap(AppUserService userService, MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (userService.findAll().size() == 0L) {
            log.debug("Loading users");
            loadUsers();
        }

        if (meetingRoomService.findAll().size() == 0L) {
            log.debug("Loading meeting-rooms");
            loadMeetingRooms();
        }
    }

    private void loadUsers() {
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        //admin.setPassword("admin");
        admin.setFirstName("Frédéric");
        admin.setLastName("Ohm");
        admin.setEmail("fredohm@onepiece.com");
        admin.setMeetings(new HashSet<>());
        userService.save(admin);
    }

    private void loadMeetingRooms() {
        MeetingRoom salleTest = new MeetingRoom();
        salleTest.setName("salle Test");
        salleTest.setAvailable(false);
        salleTest.setCapacity(4);
        salleTest.setDescription("Création d'une salle test lors du premier démarrage de l'application");
        salleTest.setLocation("Shinsekai");
        salleTest.setMeetings(new HashSet<>());
        meetingRoomService.save(salleTest);
    }
}

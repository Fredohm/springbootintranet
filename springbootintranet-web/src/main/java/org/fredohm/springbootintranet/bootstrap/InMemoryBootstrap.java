package org.fredohm.springbootintranet.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

@Slf4j
@Component
@Profile({"default","map"})
public class InMemoryBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final MeetingRoomService meetingRoomService;
    private final MeetingService meetingService;
    private final UserService userService;

    public InMemoryBootstrap(MeetingRoomService meetingRoomService, MeetingService meetingService, UserService userService) {
        this.meetingRoomService = meetingRoomService;
        this.meetingService = meetingService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadData();
    }

    private void loadData() {

        AppUser admin = new AppUser();
        admin.setUsername("admin");
        //admin.setPassword("admin");
        admin.setFirstName("Frédéric");
        admin.setLastName("Ohm");
        admin.setEmail("fredohm@onepiece.com");
        admin.setMeetings(new HashSet<>());

        AppUser jean = new AppUser();
        jean.setUsername("usoppu");
        //jean.setPassword("usoppu");
        jean.setFirstName("Jean");
        jean.setLastName("Dupont");
        jean.setEmail("ussoppu@onepiece.com");
        jean.setMeetings(new HashSet<>());

        MeetingRoom salle1 = new MeetingRoom();
        salle1.setName("salle 1");
        salle1.setAvailable(true);
        salle1.setCapacity(8);
        salle1.setDescription("Une petite salle");
        salle1.setLocation("Etage 2");
        salle1.setMeetings(new HashSet<>());

        MeetingRoom salle2 = new MeetingRoom();
        salle2.setName("salle 2");
        salle2.setAvailable(true);
        salle2.setCapacity(12);
        salle2.setDescription("Une plus grande salle");
        salle2.setLocation("Etage 1");
        salle2.setMeetings(new HashSet<>());

        Meeting staff1 = new Meeting();
        staff1.setUser(admin);
        staff1.setTitle("staff 1");
        staff1.setMeetingRoom(salle1);
        staff1.setContact("boss");
        staff1.setMembersNb(4);
        staff1.setDate(LocalDate.now().plusDays(1L));
        staff1.setStart(LocalTime.now().plusMinutes(5));
        staff1.setEnd(LocalTime.now().plusHours(1L));
        staff1.setDrinks(true);
        staff1.setFood(false);
        staff1.setProjection(false);
        staff1.setNotes("Meeting test");

        Meeting staff2 = new Meeting();
        staff2.setUser(jean);
        staff2.setMeetingRoom(salle2);
        staff2.setTitle("Staff 2");
        staff2.setMembersNb(6);
        staff2.setContact("Luffy");
        staff2.setDate(LocalDate.now().plusDays(2L));
        staff2.setStart(LocalTime.now().plusHours(2L));
        staff2.setEnd(LocalTime.now().plusHours(4L));
        staff2.setDrinks(false);
        staff2.setFood(false);
        staff2.setProjection(true);
        staff2.setNotes("Un petit film entre amis");


        admin.getMeetings().add(staff1);
        salle1.getMeetings().add(staff1);

        jean.getMeetings().add(staff2);
        salle2.getMeetings().add(staff2);

        userService.save(admin);
        System.out.println(admin);
        meetingRoomService.save(salle1);
        System.out.println(salle1);
        meetingService.save(staff1);
        System.out.println(staff1);

        userService.save(jean);
        meetingRoomService.save(salle2);
        meetingService.save(staff2);

        System.out.println(userService.findById(2L));
        System.out.println(meetingRoomService.findById(2L));
        System.out.println(meetingService.findById(2L));

    }
}

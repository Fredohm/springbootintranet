package org.fredohm.springbootintranet;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {

    private final MeetingRoomService meetingRoomService;
    private final MeetingService meetingService;
    private final UserService userService;

    public Bootstrap(MeetingRoomService meetingRoomService, MeetingService meetingService, UserService userService) {
        this.meetingRoomService = meetingRoomService;
        this.meetingService = meetingService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Frédéric");
        admin.setLastName("Ohm");
        admin.setEmail("fredohm@onepiece.com");
        admin.setMeetings(new HashSet<>());

        User jean = new User();
        jean.setUsername("usoppu");
        jean.setPassword("usoppu");
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
        staff1.setMeetingTitle("staff 1");
        staff1.setMeetingRoom(salle1);
        staff1.setMeetingLeader("boss");
        staff1.setMembersNb(4);
        staff1.setMeetingDate(LocalDate.now());
        staff1.setStartTime(LocalDate.now());
        staff1.setEndTime(LocalDate.now());
        staff1.setDrinks(true);
        staff1.setFood(false);
        staff1.setProjection(false);
        staff1.setNotes("Meeting test");

        Meeting staff2 = new Meeting();
        staff2.setUser(jean);
        staff2.setMeetingRoom(salle2);
        staff2.setMeetingTitle("Staff 2");
        staff2.setMembersNb(6);
        staff2.setMeetingLeader("Luffy");
        staff2.setMeetingDate(LocalDate.now());
        staff2.setStartTime(LocalDate.now());
        staff2.setEndTime(LocalDate.now());
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

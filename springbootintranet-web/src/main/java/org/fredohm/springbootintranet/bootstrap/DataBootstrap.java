package org.fredohm.springbootintranet.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.security.Authority;
import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.mappers.RoleMapper;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.fredohm.springbootintranet.services.api.v1.MeetingRoomRestService;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.fredohm.springbootintranet.services.sdjpa.security.AuthorityService;
import org.fredohm.springbootintranet.services.sdjpa.security.RoleService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile("h2")
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final MeetingRoomRestService meetingRoomService;
    private final MeetingRestService meetingService;
    private final UserRestService userService;

    private final AuthorityService authorityService;
    private final RoleService roleService;


    private final RoleMapper roleMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (authorityService.findAll().size() == 0L) {
            log.debug("Loading authorities");
            loadSecurityData();
        }

        if (meetingRoomService.getAllMeetingRooms().size() == 0L) {
            log.debug("Loading meeting-rooms");
            loadMeetingRooms();
        }

        if (meetingService.getAllMeetings().size() == 0L) {
            log.debug("Loading meetings");
            loadMeetings();
        }
    }

    private void loadSecurityData() {

        Authority adminAuthority = authorityService.save(Authority.builder().role("adminRole").build());
        Authority managerAuthority = authorityService.save(Authority.builder().role("managerRole").build());
        Authority userAuthority = authorityService.save(Authority.builder().role("userRole").build());

        Authority createMeetingRoom = authorityService.save(Authority.builder().role("meetingRoom.create").build());
        Authority readMeetingRoom = authorityService.save(Authority.builder().role("meetingRoom.read").build());
        Authority updateMeetingRoom = authorityService.save(Authority.builder().role("meetingRoom.update").build());
        Authority deleteMeetingRoom = authorityService.save(Authority.builder().role("meetingRoom.delete").build());

        Authority createMeeting = authorityService.save(Authority.builder().role("meeting.create").build());
        Authority readMeeting = authorityService.save(Authority.builder().role("meeting.read").build());
        Authority updateMeeting = authorityService.save(Authority.builder().role("meeting.update").build());
        Authority deleteMeeting = authorityService.save(Authority.builder().role("meeting.delete").build());

        Authority createUser = authorityService.save(Authority.builder().role("user.create").build());
        Authority readUser = authorityService.save(Authority.builder().role("user.read").build());
        Authority updateUser = authorityService.save(Authority.builder().role("user.update").build());
        Authority deleteUser = authorityService.save(Authority.builder().role("user.delete").build());

        Role adminRole = roleService.save(Role.builder().name("ADMIN").build());
        Role managerRole = roleService.save(Role.builder().name("MANAGER").build());
        Role userRole = roleService.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(adminAuthority,
                createMeetingRoom, readMeetingRoom, updateMeetingRoom, deleteMeetingRoom,
                createMeeting, readMeeting, updateMeeting, deleteMeeting,
                createUser, readUser, updateUser, deleteUser
        )));

        managerRole.setAuthorities(new HashSet<>(Set.of(managerAuthority,
                readMeetingRoom,
                createMeeting, readMeeting, updateMeeting
        )));

        userRole.setAuthorities(new HashSet<>(Set.of(userAuthority,
                readMeetingRoom, readMeeting
        )));

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(userRole);

        userService.createNewUser(UserDTO.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .firstName("Fred")
                .lastName("Ohm")
                .email("fredwaucquez@hotmail.fr")
                .role(roleMapper.roleToRoleDto(adminRole))
                .build());

        userService.createNewUser(UserDTO.builder()
                .username("manager")
                .password(passwordEncoder.encode("manager"))
                .firstName("Alice")
                .lastName("Chouchou")
                .email("achouchou@gmail.com")
                .role(roleMapper.roleToRoleDto(managerRole))
                .build());

        userService.createNewUser(UserDTO.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .firstName("Berte")
                .lastName("Namibie")
                .email("bena@ymail.co.jp")
                .role(roleMapper.roleToRoleDto(userRole))
                .build());
    }

    private void loadMeetingRooms() {
        MeetingRoomDTO salleVerte = new MeetingRoomDTO();
        salleVerte.setName("salle Verte");
        salleVerte.setAvailable(true);
        salleVerte.setCapacity(4);
        salleVerte.setDescription("Création d'une salle test lors du premier démarrage de l'application");
        salleVerte.setLocation("Shinsekai");
        salleVerte.setMeetings(new ArrayList<>());
        meetingRoomService.createNewMeetingRoom(salleVerte);

        MeetingRoomDTO salleBleue = new MeetingRoomDTO();
        salleBleue.setName("Salle Bleue");
        salleBleue.setAvailable(true);
        salleBleue.setCapacity(4);
        salleBleue.setDescription("Création d'une salle test lors du premier démarrage de l'application");
        salleBleue.setLocation("Shinsekai");
        salleBleue.setMeetings(new ArrayList<>());
        meetingRoomService.createNewMeetingRoom(salleBleue);

        MeetingRoomDTO salleRouge = new MeetingRoomDTO();
        salleRouge.setName("salle Rouge");
        salleRouge.setAvailable(true);
        salleRouge.setCapacity(4);
        salleRouge.setDescription("Création d'une salle test lors du premier démarrage de l'application");
        salleRouge.setLocation("Shinsekai");
        salleRouge.setMeetings(new ArrayList<>());
        meetingRoomService.createNewMeetingRoom(salleRouge);
    }

    private void loadMeetings() {
        MeetingDTO staff1 = new MeetingDTO();
        staff1.setTitle("Staff 1");
        staff1.setContact("Fred");
        staff1.setMembersNb(12);
        staff1.setDate(LocalDate.now().plusDays(1));
        staff1.setStart(LocalTime.of(9,0));
        staff1.setEnd(LocalTime.of(10,30));
        staff1.setMeetingRoomDTO(meetingRoomService.getMeetingRoomById(1L));
        staff1.setProjection(false);
        staff1.setFood(false);
        staff1.setDrinks(true);
        meetingService.createNewMeeting(staff1);
    }
}

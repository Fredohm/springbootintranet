package org.fredohm.springbootintranet.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.domain.security.Authority;
import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.services.AppUserService;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.security.AuthorityService;
import org.fredohm.springbootintranet.services.security.RoleService;
import org.fredohm.springbootintranet.services.security.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile({"dev", "prod", "springdatajpa"})
public class MySQLBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AppUserService appUserService;
    private final MeetingRoomService meetingRoomService;

    private final AuthorityService authorityService;
    private final RoleService roleService;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (authorityService.findAll().size() == 0) {
            log.debug("Loading authorities");
            loadSecurityData();
        }

        if (userService.findAll().size() == 0L) {
            log.debug("Loading app users");
            loadAppUsers();
        }

        if (meetingRoomService.findAll().size() == 0L) {
            log.debug("Loading meeting-rooms");
            loadMeetingRooms();
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

        userRole.setAuthorities(new HashSet<>(Set.of(
                readMeetingRoom, readMeeting
        )));

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(userRole);

        userService.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .role(adminRole)
                .build());

        userService.save(User.builder()
                .username("manager")
                .password(passwordEncoder.encode("manager"))
                .role(managerRole)
                .build());

        userService.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .role(userRole)
                .build());
    }

    private void loadAppUsers() {
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        //admin.setPassword("admin");
        admin.setFirstName("Frédéric");
        admin.setLastName("Ohm");
        admin.setEmail("fredohm@onepiece.com");
        admin.setMeetings(new HashSet<>());
        appUserService.save(admin);
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

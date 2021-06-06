package org.fredohm.springbootintranet.controllers.api.v1;


import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.config.permissions.user.CreateUser;
import org.fredohm.springbootintranet.config.permissions.user.DeleteUser;
import org.fredohm.springbootintranet.config.permissions.user.ReadUser;
import org.fredohm.springbootintranet.config.permissions.user.UpdateUser;
import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(UserRestController.BASE_URL)
public class UserRestController {

    public static final String BASE_URL = "/api/v1/users";
    private final UserRestService userRestService;

    @ReadUser
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getListOfUsers() {
        return userRestService.getAllUsers();
    }

    @ReadUser
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id) {
        return userRestService.getUserById(id);
    }

    @CreateUser
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@RequestBody UserDTO userDTO) {
        return userRestService.createNewUser(userDTO);
    }

    @UpdateUser
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userRestService.saveUserByDTO(id, userDTO);
    }

    @UpdateUser
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO patchUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userRestService.patchUser(id, userDTO);
    }

    @DeleteUser
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userRestService.deleteUserById(id);
    }
}

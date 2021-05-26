package org.fredohm.springbootintranet.services.api.v1;

import org.fredohm.springbootintranet.model.UserDTO;

import java.util.List;

public interface UserRestService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createNewUser(UserDTO userDTO);

    UserDTO saveUserByDTO(Long id, UserDTO userDTO);

    UserDTO patchUser(Long id, UserDTO userDTO);

    void deleteUserById(Long id);
}

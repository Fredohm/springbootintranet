package org.fredohm.springbootintranet.services.api.v1.impl;

import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserRestServiceImpl implements UserRestService {

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO saveUserByDTO(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO patchUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}

package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.RoleMapper;
import org.fredohm.springbootintranet.mappers.RoleToRoleListMapper;
import org.fredohm.springbootintranet.mappers.UserMapper;
import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.repositories.security.UserRepository;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Profile({"dev", "h2"})
public class UserRestServiceImpl implements UserRestService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleToRoleListMapper roleToRoleListMapper;
    private final RoleMapper roleMapper;

    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.userToUserDTO(userRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));

    }

    @Transactional
    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        return saveAndReturnDTO(userMapper.userDtoToUser(userDTO));
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setEmail(userMapper.userDtoToUser(userDTO).getEmail());
        userToUpdate.setUsername(userMapper.userDtoToUser(userDTO).getUsername());
        userToUpdate.setFirstName(userMapper.userDtoToUser(userDTO).getFirstName());
        userToUpdate.setLastName(userMapper.userDtoToUser(userDTO).getLastName());
        if (userMapper.userDtoToUser(userDTO).getPassword() != null) {
            userToUpdate.setPassword(userMapper.userDtoToUser(userDTO).getPassword());
        }
        userToUpdate.setRoles(roleToRoleListMapper.roleToList(roleMapper.roleDtoToRole(userDTO.getRole())));

        return saveAndReturnDTO(userToUpdate);
    }

    @Transactional
    @Override
    public UserDTO patchUser(Long id, UserDTO userDTO) {

        return userRepository.findById(id).map(user -> {
            if (userDTO.getUsername() != null) {
                user.setUsername(userDTO.getUsername());
            }
            if (userDTO.getFirstName() != null) {
                user.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                user.setLastName(userDTO.getLastName());
            }
            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }
            if (userDTO.getRole() != null) {
                user.setRoles(roleToRoleListMapper.roleToList(roleMapper.roleDtoToRole(userDTO.getRole())));
            }

            return userMapper.userToUserDTO(userRepository.save(user));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO saveAndReturnDTO(User user) {
        User savedUser = userRepository.save(user);

        return userMapper.userToUserDTO(savedUser);
    }
}

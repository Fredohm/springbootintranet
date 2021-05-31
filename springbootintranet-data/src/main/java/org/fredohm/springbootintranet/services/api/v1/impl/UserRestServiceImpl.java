package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.UserMapper;
import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.repositories.security.UserRepository;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Primary
@Profile({"dev", "h2"})
public class UserRestServiceImpl implements UserRestService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

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
    public UserDTO saveUserByDTO(Long id, UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        user.setId(id);

        return saveAndReturnDTO(user);
    }

    @Transactional
    @Override
    public UserDTO patchUser(Long id, UserDTO userDTO) {
        return null;
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

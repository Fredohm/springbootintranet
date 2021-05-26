package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "matchingPassword", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    User userDtoToUser(UserDTO userDTO);
}

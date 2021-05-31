package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, RoleMapper.class})
public interface UserMapper {

    @Mapping(target = "matchingPassword", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    User userDtoToUser(UserDTO userDTO);
}

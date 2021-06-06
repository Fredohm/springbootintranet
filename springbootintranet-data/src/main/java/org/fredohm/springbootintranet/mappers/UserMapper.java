package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class, RoleMapper.class, RoleToRoleListMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role", source = "roles")
    @Mapping(target = "matchingPassword", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "roles", source = "role")
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    User userDtoToUser(UserDTO userDTO);
}

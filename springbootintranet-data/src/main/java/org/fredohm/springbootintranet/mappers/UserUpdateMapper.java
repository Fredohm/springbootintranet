package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class, RoleMapper.class, RoleToRoleListMapper.class})
public interface UserUpdateMapper {

    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    @Mapping(target = "role", source = "roles")
    @Mapping(target = "matchingPassword", ignore = true)
    UserUpdateDTO userToUserUpdateDto(User user);

    @Mapping(target = "role", source = "roles")
    User userUpdateDtoToUser(User user);
}

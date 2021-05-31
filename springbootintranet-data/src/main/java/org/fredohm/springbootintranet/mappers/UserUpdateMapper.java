package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface UserUpdateMapper {

    UserUpdateDTO userToUserUpdateDto(User user);
}

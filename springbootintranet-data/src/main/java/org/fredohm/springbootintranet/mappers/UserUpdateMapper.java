package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserUpdateDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserUpdateMapper {

    UserUpdateDto userToUserUpdateDto(User user);


}

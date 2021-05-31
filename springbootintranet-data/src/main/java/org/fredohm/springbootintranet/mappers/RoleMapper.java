package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.model.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface RoleMapper {

    RoleDTO roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDTO role);
}

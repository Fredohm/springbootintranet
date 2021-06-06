package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.model.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDto(Role role);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "authority", ignore = true)
    Role roleDtoToRole(RoleDTO role);
}

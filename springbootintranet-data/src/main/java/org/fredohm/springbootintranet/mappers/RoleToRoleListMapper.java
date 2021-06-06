package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleToRoleListMapper {

    public List<Role> roleToList(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    public Role listToRole(List<Role> roles) {
        return roles.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
    }

}

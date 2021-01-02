package org.fredohm.springbootintranet.repositories.security;

import org.fredohm.springbootintranet.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

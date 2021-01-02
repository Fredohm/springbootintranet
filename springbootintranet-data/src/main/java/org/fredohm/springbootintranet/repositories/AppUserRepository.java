package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}

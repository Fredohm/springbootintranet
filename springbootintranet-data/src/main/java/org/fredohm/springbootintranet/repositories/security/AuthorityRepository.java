package org.fredohm.springbootintranet.repositories.security;

import org.fredohm.springbootintranet.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.Authority;
import org.fredohm.springbootintranet.model.AuthorityDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface AuthorityMapper {

    AuthorityDTO authorityToAuthorityDto(Authority authority);
}

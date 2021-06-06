package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.security.Authority;
import org.fredohm.springbootintranet.model.AuthorityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface AuthorityMapper {

    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    AuthorityDTO authorityToAuthorityDto(Authority authority);
}

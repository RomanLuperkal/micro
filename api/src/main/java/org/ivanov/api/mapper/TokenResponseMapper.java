package org.ivanov.api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface TokenResponseMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "accessToken", source = "accessToken")
    @Mapping(target = "refreshToken", source = "refreshToken")
    @Mapping(target = "expiresIn", source = "expiresIn")
    @Mapping(target = "tokenType", source = "tokenType")
    org.ivanov.individual.dto.TokenResponse toTokenResponse(
            org.ivanov.keycloak.dto.TokenResponse src
    );
}

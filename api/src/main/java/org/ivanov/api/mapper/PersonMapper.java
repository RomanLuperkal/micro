package org.ivanov.api.mapper;

import org.ivanov.individual.dto.IndividualDto;
import org.ivanov.individual.dto.IndividualWriteDto;
import org.ivanov.individual.dto.IndividualWriteResponseDto;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface PersonMapper {

    org.ivanov.person.dto.IndividualWriteDto from(IndividualWriteDto dto);

    org.ivanov.person.dto.IndividualDto from(IndividualDto dto);

    IndividualDto from(org.ivanov.person.dto.IndividualDto dto);

    IndividualWriteResponseDto from(org.ivanov.person.dto.IndividualWriteResponseDto dto);
}

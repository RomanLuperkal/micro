package org.ivanov.personservice.service;

import org.ivanov.person.dto.IndividualDto;
import org.ivanov.person.dto.IndividualPageDto;
import org.ivanov.person.dto.IndividualWriteDto;
import org.ivanov.person.dto.IndividualWriteResponseDto;

import java.util.List;
import java.util.UUID;

public interface IndividualService {
    IndividualWriteResponseDto register(IndividualWriteDto writeDto);

    IndividualPageDto findByEmails(List<String> emails);

    IndividualDto findById(UUID id);

    void softDelete(UUID id) ;

    void hardDelete(UUID id);

    IndividualWriteResponseDto update(UUID id, IndividualWriteDto writeDto);
}

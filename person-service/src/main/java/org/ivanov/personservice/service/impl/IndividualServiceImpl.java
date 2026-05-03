package org.ivanov.personservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.ivanov.person.dto.IndividualDto;
import org.ivanov.person.dto.IndividualPageDto;
import org.ivanov.person.dto.IndividualWriteDto;
import org.ivanov.person.dto.IndividualWriteResponseDto;
import org.ivanov.personservice.exception.PersonException;
import org.ivanov.personservice.mapper.IndividualMapper;
import org.ivanov.personservice.repository.IndividualRepository;
import org.ivanov.personservice.service.IndividualService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Setter
@Service
@RequiredArgsConstructor
public class IndividualServiceImpl implements IndividualService {

    private final IndividualMapper individualMapper;
    private final IndividualRepository individualRepository;

    @Override
    public IndividualWriteResponseDto register(IndividualWriteDto writeDto) {
        var individual = individualMapper.to(writeDto);
        individualRepository.save(individual);
        log.info("IN - register: individual: [{}] successfully registered", individual.getUser().getEmail());
        return new IndividualWriteResponseDto(individual.getId().toString());
    }

    @Override
    @Transactional(readOnly = true)
    public IndividualPageDto findByEmails(List<String> emails) {
        var individuals = individualRepository.findAllByEmails(emails);
        var from = individualMapper.from(individuals);
        var individualPageDto = new IndividualPageDto();
        individualPageDto.setItems(from);
        return individualPageDto;
    }

    @Override
    @Transactional(readOnly = true)
    public IndividualDto findById(UUID id) {
        var individual = individualRepository.findById(id)
                .orElseThrow(() -> new PersonException("Individual not found by id=[%s]", id));
        log.info("IN - findById: individual with id = [{}] successfully found", id);
        return individualMapper.from(individual);
    }

    @Override
    @Transactional
    public void softDelete(UUID id) {
        log.info("IN - softDelete: individual with id = [{}] successfully deleted", id);
        individualRepository.softDelete(id);
    }

    @Override
    public void hardDelete(UUID id) {
        var individual = individualRepository.findById(id)
                .orElseThrow(() -> new PersonException("Individual not found by id=[%s]", id));
        individualRepository.delete(individual);
        log.info("IN - hardDelete: individual with id = [{}] successfully deleted", id);
    }

    @Override
    public IndividualWriteResponseDto update(UUID id, IndividualWriteDto writeDto) {
        var individual = individualRepository.findById(id)
                .orElseThrow(() -> new PersonException("Individual not found by id=[%s]", id));
        individualMapper.update(individual, writeDto);
        individualRepository.save(individual);
        return new IndividualWriteResponseDto(individual.getId().toString());
    }
}
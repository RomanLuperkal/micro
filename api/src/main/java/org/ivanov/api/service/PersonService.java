package org.ivanov.api.service;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ivanov.api.mapper.PersonMapper;
import org.ivanov.individual.dto.IndividualWriteDto;
import org.ivanov.individual.dto.IndividualWriteResponseDto;
import org.ivanov.person.api.PersonApiClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonApiClient personApiClient;
    private final PersonMapper personMapper;

    @WithSpan("personService.register")
    public Mono<IndividualWriteResponseDto> register(IndividualWriteDto request) {
        return Mono.fromCallable(() -> personApiClient.registration(personMapper.from(request)))
                .mapNotNull(HttpEntity::getBody)
                .map(personMapper::from)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(t -> log.info("Person registered id = [{}]", t.getId()));
    }

    @WithSpan("personService.compensateRegistration")
    public Mono<Void> compensateRegistration(String id) {
        return Mono.fromRunnable(() -> personApiClient.compensateRegistration(UUID.fromString(id)))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}

package com.jullayro.tickets.controllers;


import com.jullayro.tickets.domain.CreateEventRequest;
import com.jullayro.tickets.domain.dtos.CreateEventRequestDto;
import com.jullayro.tickets.domain.dtos.CreateEventResponseDto;
import com.jullayro.tickets.domain.entities.Event;
import com.jullayro.tickets.mappers.EventMappers;
import com.jullayro.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMappers eventMappers;
    private final EventService eventService;


    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody
            @Valid CreateEventRequestDto createEventRequestDto
            ){

     CreateEventRequest createEventRequest = eventMappers.fromDto(createEventRequestDto);
     UUID userId = UUID.fromString(jwt.getSubject());
        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMappers.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);


    }



}

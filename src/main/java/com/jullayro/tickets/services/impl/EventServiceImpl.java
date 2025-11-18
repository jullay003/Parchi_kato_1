package com.jullayro.tickets.services.impl;

import com.jullayro.tickets.domain.CreateEventRequest;
import com.jullayro.tickets.domain.entities.Event;
import com.jullayro.tickets.domain.entities.User;
import com.jullayro.tickets.exceptions.UserNotFoundException;
import com.jullayro.tickets.repositories.UserRepository;
import com.jullayro.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;


    @Override
    public Event createEvent(UUID organizerId,
                             CreateEventRequest event) {
      User orgainzer = userRepository.findById(organizerId)
              .orElseThrow(() -> new UserNotFoundException(
                      String.format("user with ID '%s' not found", organizerId)
              ));
      Event eventToCreate = new Event();
      eventToCreate.setName(event.getName());
      eventToCreate.setStart(event.getStart());
      eventToCreate.setEnd(event.getEnd());
      eventToCreate.setVenue(event.getVenue());
      eventToCreate.setSalesStart(event.getSalesStart());

        return null;

    }
}

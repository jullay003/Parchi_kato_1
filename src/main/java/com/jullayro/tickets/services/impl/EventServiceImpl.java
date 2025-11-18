package com.jullayro.tickets.services.impl;

import com.jullayro.tickets.domain.CreateEventRequest;
import com.jullayro.tickets.domain.entities.Event;
import com.jullayro.tickets.domain.entities.TicketType;
import com.jullayro.tickets.domain.entities.User;
import com.jullayro.tickets.exceptions.UserNotFoundException;
import com.jullayro.tickets.repositories.EventRepository;
import com.jullayro.tickets.repositories.UserRepository;
import com.jullayro.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;



    @Override
    public Event createEvent(UUID organizerId,
                             CreateEventRequest event) {
      User orgainzer = userRepository.findById(organizerId)
              .orElseThrow(() -> new UserNotFoundException(
                      String.format("user with ID '%s' not found", organizerId)
              ));
     List<TicketType> ticketTypesToCreate =  event.getTicketTypes().stream().map(
              ticketType -> {
                  TicketType ticketTypeToCreate = new TicketType();
                  ticketTypeToCreate.setName(ticketType.getName());
                  ticketTypeToCreate.setPrice(ticketType.getPrice());
                  ticketTypeToCreate.setDescription(ticketType.getDescription());
                  ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                  return ticketTypeToCreate;
              }
      ).toList();

      Event eventToCreate = new Event();
      eventToCreate.setName(event.getName());
      eventToCreate.setStart(event.getStart());
      eventToCreate.setEnd(event.getEnd());
      eventToCreate.setVenue(event.getVenue());
      eventToCreate.setSalesStart(event.getSalesStart());
      eventToCreate.setSalesEnd(event.getSalesEnd());
      eventToCreate.setStatus(event.getStatus());
      eventToCreate.setOrganizer(orgainzer);
      eventToCreate.setTicketTypes(ticketTypesToCreate);

       return eventRepository.save(eventToCreate);

    }
}

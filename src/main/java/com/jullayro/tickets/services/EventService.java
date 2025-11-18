package com.jullayro.tickets.services;


import com.jullayro.tickets.domain.CreateEventRequest;
import com.jullayro.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest event);

}

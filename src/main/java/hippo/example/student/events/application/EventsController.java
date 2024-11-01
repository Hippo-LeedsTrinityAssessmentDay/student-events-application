package hippo.example.student.events.application;

import hippo.example.student.events.application.dto.Event;
import hippo.example.student.events.application.dto.EventDto;
import hippo.example.student.events.service.EventService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @GetMapping("/find-all")
    public List<EventDto> findAll() throws IOException {
        return eventService.getAllEvents();
    }

    @GetMapping("/find/{eventName}")
    public Optional<EventDto> find(
        @PathVariable("eventName") String eventId
    ) {
        //TODO not implemented
        return null;
    }

    @PostMapping("/create")
    public EventDto create(@Valid @RequestBody Event requestBody) throws IOException {
        return eventService.createEvent(requestBody);
    }
}

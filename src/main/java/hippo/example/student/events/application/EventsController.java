package hippo.example.student.events.application;

import hippo.example.student.events.application.dto.Event;
import hippo.example.student.events.application.dto.EventDto;
import hippo.example.student.events.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<EventDto>> findAll() throws IOException {
        try {
            List<EventDto> events = eventService.getAllEvents();
            return ResponseEntity.status(HttpStatus.OK).body(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/find/{eventName}")
    public ResponseEntity<EventDto> find(@PathVariable("eventName") String eventId) throws IOException {
        try {
            EventDto event = eventService.getEvent(eventId);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EventDto> create(@Valid @RequestBody Event requestBody, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 for validation errors
        }
        try {
            EventDto newEvent = eventService.createEvent(requestBody);
            return ResponseEntity.status(HttpStatus.OK).body(newEvent); // 200 for successful creation
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 for service failure
        }
    }
    
    
}

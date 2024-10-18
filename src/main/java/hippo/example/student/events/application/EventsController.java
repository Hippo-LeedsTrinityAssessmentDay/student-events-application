package hippo.example.student.events.application;

import hippo.example.student.events.application.dto.EventDto;
import hippo.example.student.events.service.JsonMapper;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class EventsController {

    @Autowired
    private JsonMapper jsonMapper;

    @GetMapping("/find-all")
    public List<EventDto> findAll() throws IOException {
        return jsonMapper.readFile();
    }

    @GetMapping("/find/{eventId}")
    public Optional<EventDto> find(
        @PathVariable("eventId") UUID eventId
    ) {
        //TODO - Implement me
        return Optional.empty();
    }

    //TODO - Update with incoming params
    @PostMapping("/create")
    public EventDto create() throws IOException {
        return jsonMapper.writeToFile();
    }
}

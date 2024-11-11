package hippo.example.student.events.service;

import hippo.example.student.events.application.dto.Event;
import hippo.example.student.events.application.dto.EventDto;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  private JsonMapper jsonMapper;

  public List<EventDto> getAllEvents() throws IOException {
    return jsonMapper.readFile();
  }

  public Optional<EventDto> getEvent(String eventName) throws NotImplementedException {
    // TODO: NOT IMPLEMENTED
    //return Optional.empty();
    throw new NotImplementedException("Not implemented");
  }

  public EventDto createEvent(Event event) throws IOException {
    EventDto aNewEventDto = EventDto.create(
        event
    );
    return jsonMapper.writeToFile(aNewEventDto);
  }

}

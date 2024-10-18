package hippo.example.student.events.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hippo.example.student.events.application.dto.EventDto;
import hippo.example.student.events.application.dto.EventType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper {

  @Autowired
  private ObjectMapper objectMapper;

  @Value("classpath:events.json")
  private Resource resource;

  public List<EventDto> readFile() throws IOException {
    var json = readFileAsJson();
    return objectMapper.readValue(json, new TypeReference<>() {
    });
  }

  private String readFileAsJson() throws IOException {
    return resource.getContentAsString(StandardCharsets.UTF_8);
  }

  public EventDto writeToFile() throws IOException {
    EventDto aNewEventDto = EventDto.create(
        "wobble",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        EventType.TWO
    );
      var currentEvents = readFile();
      currentEvents.add(aNewEventDto);
      objectMapper.writerWithDefaultPrettyPrinter()
          .writeValue(resource.getFile(), currentEvents);
      return aNewEventDto;
  }
}

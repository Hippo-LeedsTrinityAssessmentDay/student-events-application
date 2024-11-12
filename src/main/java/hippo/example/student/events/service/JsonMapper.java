package hippo.example.student.events.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hippo.example.student.events.application.dto.EventDto;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

  private String readFileAsJson() throws IOException {
    return resource.getContentAsString(StandardCharsets.UTF_8);
  }

  public List<EventDto> readFile() throws IOException {
    var json = readFileAsJson();
    return objectMapper.readValue(json, new TypeReference<>() {
    });
  }

  public EventDto getEvent(String eventName) throws IOException {
    List<EventDto> file = readFile(); 
    for (EventDto event : file) {
      if (event.getName().equals(eventName)) {
        return event;
      }
    }
    return null;
  }
  


  public EventDto writeToFile(EventDto newEvent) throws IOException {
      var currentEvents = readFile();
      currentEvents.add(newEvent);
      objectMapper.writerWithDefaultPrettyPrinter()
          .writeValue(resource.getFile(), currentEvents);
      return newEvent;
  }
}

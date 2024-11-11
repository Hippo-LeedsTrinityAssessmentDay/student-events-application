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

  public EventDto getEvent(String event) throws IOException {
    // var object = objectMapper.readValue(event, new TypeReference<>() { });
    // System.out.println(object);
    var file = readFile();
    var jsonFile = readFileAsJson();
    int jsonIndex = jsonFile.indexOf(event);
    var gotEvent = file.get(jsonIndex);
    System.out.println(gotEvent);
    return gotEvent;
  }
  


  public EventDto writeToFile(EventDto newEvent) throws IOException {
      var currentEvents = readFile();
      currentEvents.add(newEvent);
      objectMapper.writerWithDefaultPrettyPrinter()
          .writeValue(resource.getFile(), currentEvents);
      return newEvent;
  }
}

package hippo.example.student.events.application;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class EventsController {

    @Autowired
    private JsonMapper jsonMapper;

    @GetMapping
    public List<Event> helloWorld() throws IOException {
        return jsonMapper.readFile();
    }

    @PostMapping
    public Event create() throws IOException {
        return jsonMapper.writeToFile();
    }
}

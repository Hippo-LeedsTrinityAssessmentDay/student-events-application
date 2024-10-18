package hippo.example.student.events.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("hippo.example.student.events")
public class StudentEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentEventsApplication.class, args);
	}

}

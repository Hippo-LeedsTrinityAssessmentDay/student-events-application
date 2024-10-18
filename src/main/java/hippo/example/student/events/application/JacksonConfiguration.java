package hippo.example.student.events.application;

import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    return new Jackson2ObjectMapperBuilder()
        .indentOutput(true)
        .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
  }
}

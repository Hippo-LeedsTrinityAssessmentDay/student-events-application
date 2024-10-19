package hippo.example.student.events.application;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hippo.example.student.events.application.dto.EventDto;
import hippo.example.student.events.application.dto.EventType;
import hippo.example.student.events.service.JsonMapper;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EventsController.class)
class StudentEventsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JsonMapper mapper;

	@Test
	void pathNotFoundShouldReturnNotFound() throws Exception {
		this.mockMvc.perform(get("/events")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	void getAllEventsShouldReturnEmptyListFromService() throws Exception {
		// Arrange
		when(mapper.readFile()).thenReturn(List.of());

		//Assert
		this.mockMvc.perform(get("/events/find-all"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	void getAllEventsShouldReturnListFromService() throws Exception {
		// Arrange
		int noEvents = 5;
		List<EventDto> mockEventList = new LinkedList<EventDto>();
		for(int i = 0; i < noEvents; i++) {
			mockEventList.add(EventDto.create(
          String.valueOf(i),
					LocalDateTime.of(2020, 2, 25,10,15),
					LocalDateTime.of(2020, 3, 25,10,15),
					EventType.TWO
			));
		}
		when(mapper.readFile()).thenReturn(mockEventList);

		// Assert
		this.mockMvc.perform(get("/events/find-all"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(noEvents)));
	}

	@Test
	void getAllEventsShouldReturnErrorFromService() throws Exception {
		// Arrange
		when(mapper.readFile()).thenThrow(new MockitoException("Oops"));

		// Assert
		this.mockMvc.perform(get("/events/find-all"))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}

	@Test
	void getEventShouldReturnNoEventFromService() throws Exception {
		// Arrange
		// TODO
		//Assert
		this.mockMvc.perform(get("/events/find/some-id"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getEventShouldEventFromService() throws Exception {
		// Arrange
		// TODO

		// Assert
		this.mockMvc.perform(get("/events/find/some-id"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getEventShouldReturnErrorFromService() throws Exception {
		// Arrange
		// TODO

		// Assert
		this.mockMvc.perform(get("/events/find/some-id"))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}

	@Test
	void postEventsShouldReturnSuccess() throws Exception {
		// Arrange
		EventDto mockEvent = EventDto.create(
				"test",
				LocalDateTime.of(2020, 2, 25,10,15),
				LocalDateTime.of(2020, 3, 25,10,15),
				EventType.TWO
		);
		when(mapper.writeToFile()).thenReturn(mockEvent);

		// Assert
		this.mockMvc.perform(post("/events/create"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void postEventsShouldReturnErrorFromService() throws Exception {
		// Arrange
		when(mapper.writeToFile()).thenThrow(new MockitoException("Oops"));

		// Assert
		this.mockMvc.perform(post("/events/create"))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}

}

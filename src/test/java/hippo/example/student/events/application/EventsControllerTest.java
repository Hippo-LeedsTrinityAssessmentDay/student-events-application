package hippo.example.student.events.application;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hippo.example.student.events.application.dto.Event;
import hippo.example.student.events.application.dto.EventDto;
import hippo.example.student.events.application.dto.EventType;
import hippo.example.student.events.service.EventService;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EventsController.class)
class EventsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EventService eventService;

	@Test
	void pathNotFoundShouldReturnNotFound() throws Exception {
		this.mockMvc.perform(get("/events")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	void getAllEventsShouldReturnEmptyListFromService() throws Exception {
		// Arrange
		when(eventService.getAllEvents()).thenReturn(List.of());

		//Assert
		this.mockMvc.perform(get("/events/find-all"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	void getAllEventsShouldReturnListFromService() throws Exception {
		// Arrange
		int noOfEvents = 5;
		List<EventDto> mockEventList = new LinkedList<EventDto>();
		for(int i = 0; i < noOfEvents; i++) {
			mockEventList.add(EventDto.create(
          String.valueOf(i),
					LocalDateTime.of(2020, 2, 25,10,15),
					LocalDateTime.of(2020, 3, 25,10,15),
					EventType.TRAINING
			));
		}
		when(eventService.getAllEvents()).thenReturn(mockEventList);

		// Assert
		this.mockMvc.perform(get("/events/find-all"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(noOfEvents)));
	}

	@Test
	void getAllEventsShouldReturnErrorFromService() throws Exception {
		// Arrange
		when(eventService.getAllEvents()).thenThrow(new MockitoException("Oops"));

		// Assert
		this.mockMvc.perform(get("/events/find-all"))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}

	@Test
	void getEventShouldReturnNoEventFromService() throws Exception {
		// Arrange
		String eventName = "some-id";
		when(eventService.getEvent(eventName)).thenReturn(null);

		//Assert
		this.mockMvc.perform(get(String.format("/events/find/%s", eventName)))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getEventShouldReturnEventFromService() throws Exception {
		// Arrange
		String eventName = "some-id";
		EventDto mockEvent = EventDto.create(
				eventName,
				LocalDateTime.of(2020, 2, 25,10,15),
				LocalDateTime.of(2020, 3, 25,10,15),
				EventType.TRAINING
		);
		when(eventService.getEvent(eventName)).thenReturn(mockEvent);

		//Assert
		this.mockMvc.perform(get(String.format("/events/find/%s", eventName)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(eventName))
				.andExpect(jsonPath("$.startDateTime").value("2020-02-25T10:15:00"))
				.andExpect(jsonPath("$.eventType").value("TRAINING"));


	}

	@Test
	void getEventShouldReturnErrorFromService() throws Exception {
		// Arrange
		String eventName = "some-id";
		when(eventService.getEvent(eventName)).thenThrow(new MockitoException("Oops"));

		// Assert
		this.mockMvc.perform(get(String.format("/events/find/%s", eventName)))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}

	@Test
	void postEventsShouldReturnSuccess() throws Exception {
		// Arrange
		Event event = new Event(
				"Git Skills",
				"2024-10-18T15:45:26.669098",
				"2024-11-18T15:45:26.669098",
				"TRAINING"
		);
		EventDto eventDto = EventDto.create(event);
		String requestBody = objectMapper.writeValueAsString(event);
		when(eventService.createEvent(event)).thenReturn(eventDto);

		// Assert
		this.mockMvc.perform(post("/events/create")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"{}",
			"""
				{
					"name":"some event",
					"startDateTime": "2024-10-18T15:45:26.669098",
					"endDateTime": "2024-11-18T15:45:26.669098"
				}
			""",
			"""
				{
					"startDateTime":"2024-10-18T15:45:26.669098",
					"endDateTime":"2024-11-18T15:45:26.669098",
					"eventType":"SOCIAL"
				}
			""",
			"""
				{
					"name":"some event",
					"endDateTime":"2024-11-18T15:45:26.669098",
					"eventType":"TRAINING"
				}
			""",
			"""
				{
					"name":"some event",
					"startDateTime":"2024-11-18T15:45:26.669098",
					"eventType":"OHTER"
				}
			""",
			"""
				{
					"name":"some event",
					"startDateTime":"blah",
					"endDateTime":"blah",
					"eventType":"OTHER"
				}
			""",
	})
	void postEventsShouldReturnBadRequest(String requestBody) throws Exception {
		// Assert
		Event invalidEvent = new Event("event-name", "2024-11-12T10:00", "2024-11-12T12:00", null);
		this.mockMvc.perform(post("/events/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(invalidEvent)))  
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void postEventsShouldReturnErrorFromService() throws Exception {
		// Arrange
		Event event = new Event(
				"anEvent",
				"2024-10-18T15:45:26.669098",
				"2024-11-18T15:45:26.669098",
				"GAMING"
		);
		String requestBody = objectMapper.writeValueAsString(event);
		when(eventService.createEvent(event)).thenThrow(new MockitoException("Oops"));

		// Assert
		this.mockMvc.perform(post("/events/create")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}

}

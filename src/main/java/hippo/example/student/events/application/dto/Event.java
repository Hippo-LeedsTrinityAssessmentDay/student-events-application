package hippo.example.student.events.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Event {
  @NotNull(message = "Event name is mandatory")
  @Size(min = 1, message = "Event name cannot be empty")
  private String name;

  @NotNull(message = "Start date and time is mandatory")
  private String startDateTime;

  @NotNull(message = "End date and time is mandatory")
  private String endDateTime;

  @NotNull(message = "Event Type is mandatory")
  private String eventType;

  public Event(String name, String startDateTime, String endDateTime, String eventType) {
      this.name = name;
      this.startDateTime = startDateTime;
      this.endDateTime = endDateTime;
      this.eventType = eventType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(String startDateTime) {
    this.startDateTime = startDateTime;
  }

  public String getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(String endDateTime) {
    this.endDateTime = endDateTime;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }
}

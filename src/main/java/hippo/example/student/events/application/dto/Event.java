package hippo.example.student.events.application.dto;

import jakarta.validation.constraints.NotNull;

public class Event {
  private String name;
  private String startDateTime;
  private String endDateTime;
  @NotNull(message = "Event Type is mandatory")
  private String eventType;

  public Event(
      String someEvent,
      String s,
      String s1,
      String two
  ) {
    name = someEvent;
    startDateTime = s;
    endDateTime = s1;
    eventType = two;
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

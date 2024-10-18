package hippo.example.student.events.application;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

  private UUID eventId;
  private String name;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private EventType eventType;

  private Event(
      UUID eventId,
      String name,
      LocalDateTime startDateTime,
      LocalDateTime endDateTime,
      EventType eventType
  ) {
    this.eventId = eventId;
    this.name = name;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.eventType = eventType;
  }

  public static Event create(
      String name,
      LocalDateTime startDateTime,
      LocalDateTime endDateTime,
      EventType eventType
  ) {
    return new Event(
        UUID.randomUUID(),
        name,
        startDateTime,
        endDateTime,
        eventType
    );
  }


  public UUID getEventId() {
    return eventId;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(LocalDateTime startDateTime) {
    this.startDateTime = startDateTime;
  }

  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
}

import java.time.Duration;
import java.time.LocalDateTime;

public class Reminder extends Event {
    private Duration timeBefore;
    private Event event;

    public Reminder(Duration timeBefore, Event event) {
        super(event.getName(), event.getDateTime());
        this.timeBefore = timeBefore;
        this.event = event;
    }

    @Override
    public LocalDateTime getDateTime() {
        return event.getDateTime().minus(timeBefore);
    }

    @Override
    public String getName() {
        return "Reminder: " + event.getName() + " at " + event.getDateTime().toString();
    }

    // Getters and setters for timeBefore and event
    public Duration getTimeBefore() {
        return timeBefore;
    }

    public void setTimeBefore(Duration timeBefore) {
        this.timeBefore = timeBefore;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
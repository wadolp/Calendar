import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.Duration;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;
    private ArrayList<Reminder> reminders;

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
        this.complete = false;
    }

    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDateTime getEndTime() {
        return endDateTime;
    }

    public Duration getDuration() {
        return Duration.between(getDateTime(), endDateTime);
    }

    public String getLocation() {
        return location;
    }

    public void setEndDateTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addReminder(int daysBefore, int hoursBefore, int minutesBefore) {
        Duration duration = Duration.ofDays(daysBefore).plusHours(hoursBefore).plusMinutes(minutesBefore);
        Reminder reminder = new Reminder(duration, this);
        reminders.add(reminder);
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }
}
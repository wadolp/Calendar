import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Event implements Completable {
    private boolean complete;
    private ArrayList<Reminder> reminders;


    public Deadline(String name, LocalDateTime dateTime) {
        super(name, dateTime); // Call the appropriate Event constructor
        this.name = name;
        this.dateTime = dateTime;
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

    public void addReminder(int daysBefore, int hoursBefore, int minutesBefore) {
        Duration duration = Duration.ofDays(daysBefore).plusHours(hoursBefore).plusMinutes(minutesBefore);
        Reminder reminder = new Reminder(duration, this);
        reminders.add(reminder);
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }
}
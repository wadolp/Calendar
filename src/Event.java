import java.time.LocalDateTime;

abstract class Event implements Comparable<Event> {
    String name;
    LocalDateTime dateTime;

    Event(String name, LocalDateTime dateTime) {

    }

    public String getName() {
        return name;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int compareTo(Event o) {
        //
        return dateTime.compareTo(o.dateTime);
    }
}

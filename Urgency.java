import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public enum Urgency {
    DISTANT,
    IMMINENT,
    OVERDUE;

    private static Duration thresholdOfImminence = Duration.ofDays(1);//set default to 1 day

    public static void setThresholdOfImminence(Duration d) {
        if (d == null) {
            throw new IllegalArgumentException("Threshold duration cannot be null");
        }
        if (d.isNegative()) {
            throw new IllegalArgumentException("Threshold duration cannot be negative");
        }
        thresholdOfImminence = d;
    }

    public static Urgency getUrgency(Date time) {
        Instant now = Instant.now();
        Instant eventTime = time.toInstant();
        Duration duration = Duration.between(now, eventTime);

        if (eventTime.isBefore(now)) {
            return OVERDUE;
        } else if (duration.compareTo(thresholdOfImminence) > 0) {
            return DISTANT;
        } else {
            return IMMINENT;
        }
    }
}
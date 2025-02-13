import javax.swing.*;
import java.time.LocalDateTime;

// Main application window
public class EventPlanner extends JFrame {
    public static void main(String[] args) {
        EventPlanner planner = new EventPlanner();
        planner.setVisible(true);
    }

    public EventPlanner() {
        setTitle("Event Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        EventListPanel listPanel = new EventListPanel();
        add(listPanel);
        
        // Add some default events
        addDefaultEvents(listPanel);
    }

    public static void addDefaultEvents(EventListPanel panel) {
        LocalDateTime now = LocalDateTime.now();
        panel.addEvent(new Deadline("Submit Report", now.plusDays(3)));
        panel.addEvent(new Meeting("Team Standup", now.plusDays(1), 
                                 now.plusDays(1).plusHours(1), "Room 101"));
    }
}

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

// Panel for displaying individual events
class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;
    private static final DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // Create main info panel
        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.add(new JLabel("Name: " + event.getName()));
        infoPanel.add(new JLabel("Date: " + 
            event.getDateTime().format(formatter)));
        
        // Add specific info for Meeting
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            infoPanel.add(new JLabel("Location: " + meeting.getLocation()));
            infoPanel.add(new JLabel("Duration: " + meeting.getDuration().toHours() + " hours"));
        }
        
        add(infoPanel, BorderLayout.CENTER);
        
        // Add complete button for Completable events
        if (event instanceof Completable) {
            completeButton = new JButton("Mark Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeButton.setEnabled(false);
            });
            add(completeButton, BorderLayout.EAST);
        }
    }
}
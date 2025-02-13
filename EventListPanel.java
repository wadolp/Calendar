import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Main panel containing event list and controls
class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JPanel controlPanel;
    private JComboBox<String> sortDropDown;
    private JPanel filterPanel;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());
        
        // Create control panel
        controlPanel = new JPanel();
        
        // Add sort dropdown
        String[] sortOptions = {"Sort by Name", "Sort by Date", 
                              "Sort by Name (Reverse)", "Sort by Date (Reverse)"};
        sortDropDown = new JComboBox<>(sortOptions);
        sortDropDown.addActionListener(e -> sortEvents());
        controlPanel.add(sortDropDown);
        
        // Add filter checkboxes
        filterPanel = new JPanel();
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
        JCheckBox completeFilter = new JCheckBox("Hide Completed");
        JCheckBox deadlineFilter = new JCheckBox("Hide Deadlines");
        JCheckBox meetingFilter = new JCheckBox("Hide Meetings");
        
        // Add anonymous class for filter listener
        completeFilter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateDisplay();
            }
        });
        
        filterPanel.add(completeFilter);
        filterPanel.add(deadlineFilter);
        filterPanel.add(meetingFilter);
        controlPanel.add(filterPanel);
        
        // Add event button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e -> showAddEventDialog());
        controlPanel.add(addButton);
        
        add(controlPanel, BorderLayout.NORTH);
        
        // Create display panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);
    }

    private void showAddEventDialog() {
        AddEventModal dialog = new AddEventModal(
            SwingUtilities.getWindowAncestor(this), this);
        dialog.setVisible(true);
    }

    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    private void updateDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            displayPanel.add(new EventPanel(event));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void sortEvents() {
        // Implement sorting based on sortDropDown selection
        // This would use the Event's compareTo method
    }
}
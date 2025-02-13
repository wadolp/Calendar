import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Main panel containing event list and controls
class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JPanel controlPanel;
    private JComboBox<String> sortDropDown;
    private JPanel filterPanel;
    private JCheckBox completeFilter;
    private JCheckBox deadlineFilter;
    private JCheckBox meetingFilter;

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

        // Create filter panel
        filterPanel = new JPanel();
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
        
        completeFilter = new JCheckBox("Hide Completed");
        deadlineFilter = new JCheckBox("Hide Deadlines");
        meetingFilter = new JCheckBox("Hide Meetings");
        
        // Add anonymous class for filter listener
        completeFilter.addActionListener(e -> updateDisplay());
        deadlineFilter.addActionListener(e -> updateDisplay());
        meetingFilter.addActionListener(e -> updateDisplay());
        
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
        for (Event event : getFilteredEvents()) {
            displayPanel.add(new EventPanel(event));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private ArrayList<Event> getFilteredEvents() {
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (Event event : events) {
            if (completeFilter.isSelected() && event instanceof Completable && ((Completable) event).isComplete()) {
                continue;
            }
            if (deadlineFilter.isSelected() && event instanceof Deadline) {
                continue;
            }
            if (meetingFilter.isSelected() && event instanceof Meeting) {
                continue;
            }
            filteredEvents.add(event);
        }
        return filteredEvents;
    }

    private void sortEvents() {
        // Implement sorting based on sortDropDown selection
        String selectedSort = (String) sortDropDown.getSelectedItem();
        if ("Sort by Name".equals(selectedSort)) {
            Collections.sort(events, Comparator.comparing(Event::getName));
        } else if ("Sort by Date".equals(selectedSort)) {
            Collections.sort(events, Comparator.comparing(Event::getDateTime));
        }
        else if ("Sort by Name (Reverse)".equals(selectedSort)) {
            Collections.sort(events, Comparator.comparing(Event::getName).reversed());
        } else if ("Sort by Date (Reverse)".equals(selectedSort)) {
            Collections.sort(events, Comparator.comparing(Event::getDateTime).reversed());
        }
        updateDisplay();
        
    }
}
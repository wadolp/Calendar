import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


class AddEventModal extends JDialog {
    private EventListPanel parentPanel;
    private JTextField nameField;
    private JComboBox<String> typeCombo;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private JTextField locationField;
    private JSpinner endTimeSpinner;
    private JPanel formPanel;
    
    
    public AddEventModal(Window owner, EventListPanel parent) {
        super(owner, "Add New Event", ModalityType.APPLICATION_MODAL);
        this.parentPanel = parent;
        
        setLayout(new BorderLayout());
        setSize(400, 300);
        
        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Add name field
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        formPanel.add(nameField);
        
        // Add type selector
        formPanel.add(new JLabel("Type:"));
        typeCombo = new JComboBox<>(new String[]{"Deadline", "Meeting"});
        formPanel.add(typeCombo);
        
        // Add date/time picker
        formPanel.add(new JLabel("Date:"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        formPanel.add(dateSpinner);

        formPanel.add(new JLabel("Time:"));
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        formPanel.add(timeSpinner);

        // Add location field (initially hidden)
        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField(20);
        formPanel.add(locationField);
        locationField.setVisible(false);
        
        // Add end time picker (initially hidden)
        formPanel.add(new JLabel("End Time:"));
        endTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
        endTimeSpinner.setEditor(endTimeEditor);
        formPanel.add(endTimeSpinner);
        endTimeSpinner.setVisible(false);
        
        typeCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedType = (String) typeCombo.getSelectedItem();
                    if ("Meeting".equals(selectedType)) {
                        locationField.setVisible(true);
                        endTimeSpinner.setVisible(true);
                    } else {
                        locationField.setVisible(false);
                        endTimeSpinner.setVisible(false);
                    }
                    formPanel.revalidate();
                    formPanel.repaint();
                }
            }
        });

        add(formPanel, BorderLayout.CENTER);
        
        // Add buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> createEvent());
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void createEvent() {
        String name = nameField.getText();
        LocalDate date = ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalTime time = ((java.util.Date) timeSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        if ("Deadline".equals(typeCombo.getSelectedItem())) {
            Deadline deadline = new Deadline(name, dateTime);
            parentPanel.addEvent(deadline);
        } else {
            LocalTime endTime = ((java.util.Date) endTimeSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
            LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
            String location = locationField.getText();
            Meeting meeting = new Meeting(name, dateTime, endDateTime, location);
            parentPanel.addEvent(meeting);
        }
        // Close dialog
        setVisible(false);
    }
}
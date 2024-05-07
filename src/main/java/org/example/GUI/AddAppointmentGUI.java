package org.example.GUI;

import org.example.Appointment;
import org.example.AppointmentCRUD;
import org.example.Medic;
import org.example.MedicCRUD;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class AddAppointmentGUI {
    private JTextField pacientCNPTextField;
    private JTextField medicCNPTextField;
    private JTextField dateAndTimeTextField;
    private JButton addAppointmentButton;
    private JPanel panel1;

    public void addAppointment() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy HH:mm", Locale.ENGLISH);
        String pacientCNP = pacientCNPTextField.getText();
        String medicCNP = medicCNPTextField.getText();
        Date dateAndTime = null;
        try {
            dateAndTime = formatter.parse(dateAndTimeTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // convert Date to LocalDateTime
        LocalDateTime localDateTime = dateAndTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        AppointmentCRUD appointmentCRUD = AppointmentCRUD.getInstance();
        Appointment appointment = new Appointment(pacientCNP, medicCNP, localDateTime);
        appointmentCRUD.create(appointment);

    }

    public AddAppointmentGUI() {
        addAppointmentButton.addActionListener(e -> {
            try {
                addAppointment();
                JOptionPane.showMessageDialog(null, "Appointment added successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error adding appointment");
                ex.printStackTrace();

            }
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }


}

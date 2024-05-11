package org.example.GUI.Appointment;

import org.example.Models.Appointment;
import org.example.CRUD.AppointmentCRUD;

import javax.swing.*;
import java.time.LocalDateTime;

public class EditAppointmentGUI {
    private JPanel panel1;
    private JTextField pacientCNPTextField;
    private JTextField medicCNPTextField;
    private JTextField dateAndTimeTextField;
    private JButton editAppointmentButton;
    private JLabel appointmentIDText;
    private JLabel appointmentIDLabel;

    public void editAppointment() {
        String appointmentID = appointmentIDText.getText();
        String pacientCNP = pacientCNPTextField.getText();
        String medicCNP = medicCNPTextField.getText();
        String dateAndTime = dateAndTimeTextField.getText();


        LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
        AppointmentCRUD appointmentCRUD = AppointmentCRUD.getInstance();
        Appointment appointment = new Appointment(pacientCNP, medicCNP, localDateTime);
        appointment.setAppointmentId(Integer.parseInt(appointmentID));
        appointmentCRUD.update(appointment);
    }

    public EditAppointmentGUI(Appointment appointment) {
        appointmentIDText.setText(appointment.getAppointmentId() + "");
        pacientCNPTextField.setText(appointment.getPacientCnp());
        medicCNPTextField.setText(appointment.getMedicCnp());
        dateAndTimeTextField.setText(appointment.getDate().toString());
        editAppointmentButton.addActionListener(e -> {
            try {
                editAppointment();
                JOptionPane.showMessageDialog(null, "Appointment edited successfully");
                // close the window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error editing appointment");
                ex.printStackTrace();
            }
        });

    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

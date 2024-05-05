package org.example.GUI;

import org.example.Pacient;
import org.example.PacientCRUD;
import org.example.Treatment;
import org.example.TreatmentCRUD;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditTreatmentGUI {
    private JPanel panel1;
    private JButton editTreatmentButton;
    private JTextField TreatmentNameTextField;
    private JTextField durationTextField;
    private JTextField frequencyTextField;
    private JLabel CnpPacientLabel;

    public void editTreatment() {
        String pacientCnp = CnpPacientLabel.getText();
        String treatmentName = TreatmentNameTextField.getText();
        int durationDays = Integer.parseInt(durationTextField.getText());
        int frequencyPerDay = Integer.parseInt(frequencyTextField.getText());

        // Create a new Treatment object
        TreatmentCRUD treatmentCRUD = TreatmentCRUD.getInstance();
        Treatment treatment = new Treatment(pacientCnp, treatmentName, durationDays, frequencyPerDay);
        treatmentCRUD.update(treatment);

    }

    public EditTreatmentGUI(Treatment treatment) {
        CnpPacientLabel.setText(treatment.getPacientCnp());
        TreatmentNameTextField.setText(treatment.getTreatmentName());
        durationTextField.setText(String.valueOf(treatment.getDurationDays()));
        frequencyTextField.setText(String.valueOf(treatment.getFrequencyPerDay()));
        editTreatmentButton.addActionListener(e -> {
            try {
                editTreatment();
                JOptionPane.showMessageDialog(null, "Treatment edited successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error editing treatment");
                ex.printStackTrace();
            }
        });

    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

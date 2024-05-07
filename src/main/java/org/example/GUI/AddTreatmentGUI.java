package org.example.GUI;

import org.example.Treatment;
import org.example.TreatmentCRUD;

import javax.swing.*;

public class AddTreatmentGUI {
    private JPanel panel1;
    private JButton addTreatmentButton;
    private JTextField PacientCNPTextField;
    private JTextField TreatmentNameTextField;
    private JTextField durationTextField;
    private JTextField frequencyTextField;

    public void addTreatment() {
        String pacientCnp = PacientCNPTextField.getText();
        String treatmentName = TreatmentNameTextField.getText();
        int durationDays = Integer.parseInt(durationTextField.getText());
        int frequencyPerDay = Integer.parseInt(frequencyTextField.getText());

        // Create a new Treatment object
        Treatment treatment = new Treatment(pacientCnp, treatmentName, durationDays, frequencyPerDay);
        TreatmentCRUD treatmentCRUD = TreatmentCRUD.getInstance();
        treatmentCRUD.create(treatment);
    }

    public AddTreatmentGUI() {
        addTreatmentButton.addActionListener(e -> {
            try {
                addTreatment();
                JOptionPane.showMessageDialog(null, "Treatment added successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error adding treatment");
                ex.printStackTrace();
            }
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }

}

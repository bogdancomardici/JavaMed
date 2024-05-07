package org.example.GUI;

import org.example.Treatment;
import org.example.TreatmentCRUD;

import javax.swing.*;

public class EditTreatmentGUI {
    private JPanel panel1;
    private JButton editTreatmentButton;
    private JTextField TreatmentNameTextField;
    private JTextField durationTextField;
    private JTextField frequencyTextField;
    private JTextField cnpPacientTextField;
    private JLabel treatmentIDLabel;


    public void editTreatment() {
        String treatmentID = treatmentIDLabel.getText();
        String pacientCnp = cnpPacientTextField.getText();
        String treatmentName = TreatmentNameTextField.getText();
        int durationDays = Integer.parseInt(durationTextField.getText());
        int frequencyPerDay = Integer.parseInt(frequencyTextField.getText());

        // Create a new Treatment object
        TreatmentCRUD treatmentCRUD = TreatmentCRUD.getInstance();
        Treatment treatment = new Treatment(pacientCnp, treatmentName, durationDays, frequencyPerDay);
        treatment.setTreatmentId(Integer.parseInt(treatmentID));
        treatmentCRUD.update(treatment);

    }

    public EditTreatmentGUI(Treatment treatment) {
        treatmentIDLabel.setText(treatment.getTreatmentId() + "");
        cnpPacientTextField.setText(treatment.getPacientCnp());
        TreatmentNameTextField.setText(treatment.getTreatmentName());
        durationTextField.setText(String.valueOf(treatment.getDurationDays()));
        frequencyTextField.setText(String.valueOf(treatment.getFrequencyPerDay()));
        editTreatmentButton.addActionListener(e -> {
            try {
                editTreatment();
                JOptionPane.showMessageDialog(null, "Treatment edited successfully");
                // close the window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();

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

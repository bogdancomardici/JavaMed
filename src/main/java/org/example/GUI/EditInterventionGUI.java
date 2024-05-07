package org.example.GUI;

import org.example.InterventionCRUD;
import org.example.Intervention;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditInterventionGUI {
    private JPanel panel1;
    private JTextField cnpPacientTextField;
    private JTextField typeTextField;
    private JTextField descriptionTextField;
    private JTextField dateTextField;
    private JButton editInterventionButton;
    private JLabel InterventionIDText;
    private JLabel interventionIDLabel;

    public void editIntervention() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String interventionID = interventionIDLabel.getText();
        String cnpPacient = cnpPacientTextField.getText();
        String type = typeTextField.getText();
        String description = descriptionTextField.getText();
        Date date = null;
        try {
            date = formatter.parse(dateTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        InterventionCRUD interventionCRUD = InterventionCRUD.getInstance();
        Intervention intervention = new Intervention(cnpPacient, type, description, date);
        intervention.setInterventionId(Integer.parseInt(interventionID));
        interventionCRUD.update(intervention);
    }

    public EditInterventionGUI(Intervention intervention) {
        interventionIDLabel.setText(intervention.getInterventionId() + "");
        cnpPacientTextField.setText(intervention.getPacientCnp());
        typeTextField.setText(intervention.getType());
        descriptionTextField.setText(intervention.getDescription());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        dateTextField.setText(formatter.format(intervention.getDate()));
        editInterventionButton.addActionListener(e -> {
            try {
                editIntervention();
                JOptionPane.showMessageDialog(null, "Intervention edited successfully");
                // close the window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error editing intervention");
                ex.printStackTrace();
            }
        });

    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

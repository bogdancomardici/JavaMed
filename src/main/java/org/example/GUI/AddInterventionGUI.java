package org.example.GUI;

import org.example.InterventionCRUD;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.example.Intervention;

public class AddInterventionGUI {
    private JTextField cnpPacientTextField;
    private JTextField typeTextField;
    private JTextField descriptionTextField;
    private JTextField dateTextField;
    private JButton addInterventionButton;
    private JPanel panel1;

    public void addIntervention() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String cnpPacient = cnpPacientTextField.getText();
        String type = typeTextField.getText();
        String description = descriptionTextField.getText();
        Date date = null;
        try {
            date = formatter.parse(dateTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create a new Intervention object
        InterventionCRUD interventionCRUD = InterventionCRUD.getInstance();
        interventionCRUD.create(new Intervention(cnpPacient, type, description, date));

    }

    public AddInterventionGUI() {
        addInterventionButton.addActionListener(e -> {
            try {
                addIntervention();
                JOptionPane.showMessageDialog(null, "Intervention added successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error adding intervention");
                ex.printStackTrace();

            }
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }


}

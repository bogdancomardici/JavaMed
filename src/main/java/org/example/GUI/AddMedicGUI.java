package org.example.GUI;

import org.example.Pacient;
import org.example.PacientCRUD;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.example.Medic;
import org.example.MedicCRUD;
public class AddMedicGUI {
    private JPanel panel1;
    private JTextField CNPTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField birthDateTextField;
    private JTextField addressTextField;
    private JButton addMedicButton;
    private JTextField specialityTextField;
    private JTextField yearsOfExperienceTextField;

    public void addMedic() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String cnp = CNPTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        Date birthDate = null;
        try {
            birthDate = formatter.parse(birthDateTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String address = addressTextField.getText();
        String speciality = specialityTextField.getText();
        int yearsOfExperience = Integer.parseInt(yearsOfExperienceTextField.getText());

        // Create a new Medic object
        Medic medic = new Medic(cnp, firstName, lastName, phoneNumber, birthDate, address, speciality, yearsOfExperience);
        MedicCRUD medicCRUD = MedicCRUD.getInstance();
        medicCRUD.create(medic);


    }

    public AddMedicGUI() {
        addMedicButton.addActionListener(e -> {
            try {
                addMedic();
                JOptionPane.showMessageDialog(null, "Medic added successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error adding medic");
                ex.printStackTrace();

            }
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

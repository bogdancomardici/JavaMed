package org.example.GUI;

import org.example.Pacient;
import org.example.PacientCRUD;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class AddPacientGUI {
    private JPanel panel1;
    private JTextField CNPTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField birthDateTextField;
    private JTextField addressTextField;
    private JTextField bloodTypeTextField;
    private JTextField heightTextField;
    private JTextField weightTextField;
    private JTextField chronicDiseasesTextField;
    private JButton addPacientButton;


    public void addPacient() {
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
        String bloodType = bloodTypeTextField.getText();
        int height = Integer.parseInt(heightTextField.getText());
        int weight = Integer.parseInt(weightTextField.getText());
        String chronicDiseases = chronicDiseasesTextField.getText();

        // Create a new Pacient object
        Pacient pacient = new Pacient(cnp, firstName, lastName, phoneNumber, birthDate, address, bloodType, height, weight, chronicDiseases);
        PacientCRUD pacientCRUD = PacientCRUD.getInstance();
        pacientCRUD.create(pacient);
    }

    public AddPacientGUI() {
        addPacientButton.addActionListener(e -> {
            try {
                addPacient();
                JOptionPane.showMessageDialog(null, "Pacient added successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error adding pacient");
                ex.printStackTrace();

            }
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

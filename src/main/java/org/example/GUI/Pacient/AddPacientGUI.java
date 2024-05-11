package org.example.GUI.Pacient;

import org.example.CRUD.PacientCRUD;
import org.example.CustomExceptions.InvalidCNPException;
import org.example.CustomExceptions.InvalidDateException;
import org.example.CustomExceptions.InvalidPhoneNumberException;
import org.example.Enums.BloodTypes;
import org.example.Models.Pacient;
import org.example.Validators.ValidatorCNP;
import org.example.Validators.ValidatorPhoneNumber;

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
    private JTextField heightTextField;
    private JTextField weightTextField;
    private JTextField chronicDiseasesTextField;
    private JButton addPacientButton;
    private JComboBox bloodTypeComboBox;


    public void addPacient() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String cnp = CNPTextField.getText();
        try {
            if (!ValidatorCNP.validate(cnp)) {
                throw new InvalidCNPException("Invalid CNP");
            }
        }
        catch (InvalidCNPException e) {
            e.printStackTrace();
            cnp = null;
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        try {
            if (!ValidatorPhoneNumber.validatePhoneNumber(phoneNumber)) {
                throw new InvalidPhoneNumberException("Invalid phone number");
            }
        }
        catch (InvalidPhoneNumberException e) {
            e.printStackTrace();
            phoneNumber = null;
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        Date birthDate = null;
        try {
            birthDate = formatter.parse(birthDateTextField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            birthDate = null;
        }
        try {
            if (birthDate.after(new Date())) {
                throw new InvalidDateException("Birth date cannot be greater than the current date");
            }
        } catch (InvalidDateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            birthDate = null;
        }
        String address = addressTextField.getText();
        String bloodType = bloodTypeComboBox.getSelectedItem().toString();
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
        // add the blood type enum to the combo box
        for (BloodTypes bloodType : BloodTypes.values()) {
            bloodTypeComboBox.addItem(bloodType);
        }
        return panel1;
    }
}

package org.example.GUI.Medic;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.example.CRUD.MedicCRUD;
import org.example.CustomExceptions.InvalidCNPException;
import org.example.CustomExceptions.InvalidDateException;
import org.example.CustomExceptions.InvalidPhoneNumberException;
import org.example.Enums.MedicSpecialities;
import org.example.Models.Medic;
import org.example.Validators.ValidatorCNP;
import org.example.Validators.ValidatorPhoneNumber;

public class AddMedicGUI {
    private JPanel panel1;
    private JTextField CNPTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField birthDateTextField;
    private JTextField addressTextField;
    private JButton addMedicButton;
    private JTextField yearsOfExperienceTextField;
    private JComboBox specialityComboBox;

    public void addMedic() {
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
        // if the date is greater than the current date, throw an exception
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
        String speciality = specialityComboBox.getSelectedItem().toString();
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
        // add specialities to the combobox
        for (MedicSpecialities speciality : MedicSpecialities.values()) {
            specialityComboBox.addItem(speciality);
        }
        return panel1;
    }
}

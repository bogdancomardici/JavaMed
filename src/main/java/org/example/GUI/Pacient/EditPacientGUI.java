package org.example.GUI.Pacient;

import org.example.CRUD.PacientCRUD;
import org.example.CustomExceptions.InvalidDateException;
import org.example.CustomExceptions.InvalidPhoneNumberException;
import org.example.Enums.BloodTypes;
import org.example.Models.Pacient;
import org.example.Validators.ValidatorPhoneNumber;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditPacientGUI {
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
    private JButton editPacientButton;
    private JLabel CNPLabel;
    private JComboBox bloodTypeComboBox;


    public void editPacient() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String cnp = CNPLabel.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        try {
            if (!ValidatorPhoneNumber.validatePhoneNumber(phoneNumber)) {
                throw new InvalidPhoneNumberException("Invalid phone number");
            }
        } catch (InvalidPhoneNumberException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            phoneNumber = null;
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
        pacientCRUD.update(pacient);
    }

    public EditPacientGUI(Pacient pacient) {
        CNPLabel.setText(pacient.getCnp());
        firstNameTextField.setText(pacient.getFirstName());
        lastNameTextField.setText(pacient.getLastName());
        phoneNumberTextField.setText(pacient.getPhoneNumber());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        birthDateTextField.setText(formatter.format(pacient.getBirthDate()));
        addressTextField.setText(pacient.getAddress());
        bloodTypeComboBox.setSelectedItem(pacient.getBloodType());
        heightTextField.setText(String.valueOf(pacient.getHeight()));
        weightTextField.setText(String.valueOf(pacient.getWeight()));
        chronicDiseasesTextField.setText(pacient.getChronicDiseases());

        editPacientButton.addActionListener(e -> {
            try {
                editPacient();
                JOptionPane.showMessageDialog(null, "Pacient edited successfully");
                // close the window after editing
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error editing pacient");
                ex.printStackTrace();

            }
        });
    }

    public Container getMainPanel() {
        // add the blood type enum to the combo box
        for (BloodTypes bloodType : BloodTypes.values()) {
            bloodTypeComboBox.addItem(bloodType);
        }
        return panel1;
    }
}

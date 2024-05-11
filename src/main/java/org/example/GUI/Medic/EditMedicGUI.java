package org.example.GUI.Medic;

import org.example.CRUD.MedicCRUD;
import org.example.CustomExceptions.InvalidDateException;
import org.example.CustomExceptions.InvalidPhoneNumberException;
import org.example.Enums.MedicSpecialities;
import org.example.Models.Medic;
import org.example.Validators.ValidatorPhoneNumber;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditMedicGUI {
    private JPanel panel1;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField birthDateTextField;
    private JTextField addressTextField;
    private JButton editPacientButton;
    private JLabel CNPLabel;
    private JTextField yearsOfExperienceTextField;
    private JComboBox specialityComboBox;

    public void editMedic() {
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
        String speciality = specialityComboBox.getSelectedItem().toString();
        int yearsOfExperience = Integer.parseInt(yearsOfExperienceTextField.getText());

        // Create a new Medic object
        Medic medic = new Medic(cnp, firstName, lastName, phoneNumber, birthDate, address, speciality, yearsOfExperience);
        MedicCRUD medicCRUD = MedicCRUD.getInstance();
        medicCRUD.update(medic);

    }

    public EditMedicGUI(Medic medic) {
        CNPLabel.setText(medic.getCnp());
        firstNameTextField.setText(medic.getFirstName());
        lastNameTextField.setText(medic.getLastName());
        phoneNumberTextField.setText(medic.getPhoneNumber());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        birthDateTextField.setText(formatter.format(medic.getBirthDate()));
        addressTextField.setText(medic.getAddress());
        specialityComboBox.setSelectedItem(medic.getSpeciality());
        yearsOfExperienceTextField.setText(String.valueOf(medic.getYearsOfExperience()));
        editPacientButton.addActionListener(e -> {
            try {
                editMedic();
                JOptionPane.showMessageDialog(null, "Medic edited successfully");
                // close the window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error editing medic");
                ex.printStackTrace();

            }
        });
    }

    // get main panel
    public JPanel getMainPanel() {
        // add specialities to the combobox
        for (MedicSpecialities speciality : MedicSpecialities.values()) {
            specialityComboBox.addItem(speciality);
        }
        return panel1;
    }
}

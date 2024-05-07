package org.example.GUI;

import org.example.Medic;
import org.example.MedicCRUD;

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
    private JTextField specialityTextField;
    private JTextField yearsOfExperienceTextField;

    public void editMedic() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String cnp = CNPLabel.getText();
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
        specialityTextField.setText(medic.getSpeciality());
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
        return panel1;
    }
}

package org.example.GUI;

import org.example.Pacient;
import org.example.PacientCRUD;

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
    private JTextField bloodTypeTextField;
    private JTextField heightTextField;
    private JTextField weightTextField;
    private JTextField chronicDiseasesTextField;
    private JButton editPacientButton;
    private JLabel CNPLabel;


    public void editPacient() {
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
        String bloodType = bloodTypeTextField.getText();
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
        bloodTypeTextField.setText(pacient.getBloodType());
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
        return panel1;
    }
}

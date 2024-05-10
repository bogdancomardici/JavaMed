package org.example.GUI;

import org.example.Pacient;
import org.example.PacientCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class ListPacientsGUI {
    private JTable table1;
    private JPanel panel1;
    private JButton addANewPacientButton;

    private List<Pacient> pacients;

    public void populateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CNP");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone Number");
        model.addColumn("Birth Date");
        model.addColumn("Address");
        model.addColumn("Blood Type");
        model.addColumn("Height");
        model.addColumn("Weight");
        model.addColumn("Chronic Diseases");
        model.addColumn("Edit");
        model.addColumn("Delete");

        // add the data and edit delete buttons
        for (Pacient pacient : pacients) {
            model.addRow(new Object[]{pacient.getCnp(), pacient.getFirstName(), pacient.getLastName(), pacient.getPhoneNumber(), pacient.getBirthDate(), pacient.getAddress(), pacient.getBloodType(), pacient.getHeight(), pacient.getWeight(), pacient.getChronicDiseases(), "Edit", "Delete"});
        }

        table1.setModel(model);

    }
    public ListPacientsGUI() {
        PacientCRUD pacientCRUD = PacientCRUD.getInstance();
        pacients = pacientCRUD.readAll();
        populateTable();
        // make the table cells not editable
        table1.setDefaultEditor(Object.class, null);
        // add action listeners for the edit and delete buttons
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 10) {
                    // open the edit pacient gui
                    String cnp = (String) table1.getValueAt(row, 0);
                    Optional<Pacient> pacient = pacientCRUD.read(cnp);

                    JFrame frame = new JFrame("EditPacientGUI");
                    EditPacientGUI editPacientGUI = new EditPacientGUI(pacient.get());
                    frame.setContentPane(editPacientGUI.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    // update the table after all the editing windows are closed
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            pacients = pacientCRUD.readAll();
                            populateTable();
                        }
                    });
                } else if (row >= 0 && col == 11) {
                    ConfirmDelete confirmDelete = new ConfirmDelete();
                    confirmDelete.pack();
                    confirmDelete.setVisible(true);
                    confirmDelete.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            if (confirmDelete.isOk()) {
                                // delete the pacient
                                String cnp = (String) table1.getValueAt(row, 0);
                                pacientCRUD.delete(cnp);
                                // refresh the table
                                pacients = pacientCRUD.readAll();
                                populateTable();
                            }

                        }
                    });
                }
            }
        });

        // add action listener for the add a new pacient button
        addANewPacientButton.addActionListener(e -> {
            JFrame frame = new JFrame("AddPacientGUI");
            AddPacientGUI addPacientGUI = new AddPacientGUI();
            frame.setContentPane(addPacientGUI.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            // update the table after all the editing windows are closed
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    pacients = pacientCRUD.readAll();
                    populateTable();
                }
            });
        });

    }


}
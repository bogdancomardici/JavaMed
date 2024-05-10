package org.example.GUI;

import org.example.Medic;
import org.example.MedicCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class ListMedicsGUI {
    private JTable table1;
    private JPanel panel1;
    private JButton addMedicButton;

    private List<Medic> medics;

    public void populateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CNP");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone Number");
        model.addColumn("Birth Date");
        model.addColumn("Address");
        model.addColumn("Speciality");
        model.addColumn("Years of Experience");
        model.addColumn("Edit");
        model.addColumn("Delete");

        // add the data and edit delete buttons
        for (Medic medic : medics) {
            model.addRow(new Object[]{medic.getCnp(), medic.getFirstName(), medic.getLastName(), medic.getPhoneNumber(), medic.getBirthDate(), medic.getAddress(), medic.getSpeciality(), medic.getYearsOfExperience(), "Edit", "Delete"});
        }

        table1.setModel(model);

    }

    public ListMedicsGUI() {
        MedicCRUD medicCRUD = MedicCRUD.getInstance();
        medics = medicCRUD.readAll();
        populateTable();
        // make the table cells not editable
        table1.setDefaultEditor(Object.class, null);
        // add action listeners for the edit and delete buttons
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 8) {
                    // open the edit medic gui
                    String cnp = (String) table1.getValueAt(row, 0);
                    Optional<Medic> medic = medicCRUD.read(cnp);

                    JFrame frame = new JFrame("EditMedicGUI");
                    EditMedicGUI editMedicGUI = new EditMedicGUI(medic.get());
                    frame.setContentPane(editMedicGUI.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);

                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            // refresh the table
                            medics = medicCRUD.readAll();
                            populateTable();
                        }
                    });
                } else if (row >= 0 && col == 9) {
                    ConfirmDelete confirmDelete = new ConfirmDelete();
                    confirmDelete.pack();
                    confirmDelete.setLocationRelativeTo(null);
                    confirmDelete.setVisible(true);

                    confirmDelete.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            if (confirmDelete.isOk()) {
                                // delete the medic
                                String cnp = (String) table1.getValueAt(row, 0);
                                medicCRUD.delete(cnp);
                                // refresh the table
                                medics = medicCRUD.readAll();
                                populateTable();
                            }

                        }
                    });
                }
            }
        });

        // add the action listener for the add medic button
        addMedicButton.addActionListener(e -> {
            JFrame frame = new JFrame("AddMedicGUI");
            AddMedicGUI addMedicGUI = new AddMedicGUI();
            frame.setContentPane(addMedicGUI.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // refresh the table
                    medics = medicCRUD.readAll();
                    populateTable();
                }
            });
        });
    }

}

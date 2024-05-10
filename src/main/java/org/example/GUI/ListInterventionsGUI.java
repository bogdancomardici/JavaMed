package org.example.GUI;

import org.example.Intervention;
import org.example.InterventionCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class ListInterventionsGUI {
    private JTable table1;
    private JPanel panel1;
    private JButton addInterventionButton;

    private List<Intervention> interventions;

    public void populateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Intervention ID");
        model.addColumn("Pacient CNP");
        model.addColumn("Type");
        model.addColumn("Description");
        model.addColumn("Date");
        model.addColumn("Edit");
        model.addColumn("Delete");

        // add the data and edit delete buttons
        for (Intervention intervention : interventions) {
            model.addRow(new Object[]{intervention.getInterventionId(), intervention.getPacientCnp(), intervention.getType(), intervention.getDescription(), intervention.getDate(), "Edit", "Delete"});
        }

        table1.setModel(model);

    }

    public ListInterventionsGUI() {
        InterventionCRUD interventionCRUD = InterventionCRUD.getInstance();
        interventions = interventionCRUD.readAll();
        populateTable();
        // make the table cells not editable
        table1.setDefaultEditor(Object.class, null);
        // add action listeners for the edit and delete buttons
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 5) {
                    // open the edit intervention gui
                    String interventionID = table1.getValueAt(row, 0).toString();
                    Optional<Intervention> intervention = interventionCRUD.read(interventionID);

                    JFrame frame = new JFrame("EditInterventionGUI");
                    EditInterventionGUI editInterventionGUI = new EditInterventionGUI(intervention.get());
                    frame.setContentPane(editInterventionGUI.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    // refresh the table
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            interventions = interventionCRUD.readAll();
                            populateTable();
                        }
                    });
                } else if (row >= 0 && col == 6) {
                    ConfirmDelete confirmDelete = new ConfirmDelete();
                    confirmDelete.pack();
                    confirmDelete.setLocationRelativeTo(null);
                    confirmDelete.setVisible(true);

                    confirmDelete.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            if (confirmDelete.isOk()) {
                                // delete the intervention
                                String interventionID = table1.getValueAt(row, 0).toString();
                                interventionCRUD.delete(interventionID);
                                // refresh the table
                                interventions = interventionCRUD.readAll();
                                populateTable();
                            }

                        }
                    });;
                }
            }


        });

        addInterventionButton.addActionListener(e -> {
            JFrame frame = new JFrame("AddInterventionGUI");
            AddInterventionGUI addInterventionGUI = new AddInterventionGUI();
            frame.setContentPane(addInterventionGUI.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // refresh the table
                    interventions = interventionCRUD.readAll();
                    populateTable();
                }
            });
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

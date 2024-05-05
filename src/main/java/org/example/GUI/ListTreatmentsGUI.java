package org.example.GUI;

import org.example.Treatment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;
import org.example.TreatmentCRUD;

public class ListTreatmentsGUI {
    private JTable table1;
    private JPanel panel1;
    private JButton addTreatmentButton;


    private List<Treatment> treatments;

    public void populateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tratment ID");
        model.addColumn("Pacient CNP");
        model.addColumn("Treatment Name");
        model.addColumn("Duration Days");
        model.addColumn("Frequency Per Day");
        model.addColumn("Edit");
        model.addColumn("Delete");

        // add the data and edit delete buttons
        for (Treatment treatment : treatments) {
            model.addRow(new Object[]{treatment.getTreatmentId(),treatment.getPacientCnp(), treatment.getTreatmentName(), treatment.getDurationDays(), treatment.getFrequencyPerDay(), "Edit", "Delete"});
        }

        table1.setModel(model);

    }

    public ListTreatmentsGUI() {
        TreatmentCRUD treatmentCRUD = TreatmentCRUD.getInstance();
        treatments = treatmentCRUD.readAll();
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
                    // open the edit treatment gui
                    String treatmentID = table1.getValueAt(row, 0).toString();
                    Optional<Treatment> treatment = treatmentCRUD.read(treatmentID);

                    JFrame frame = new JFrame("EditTreatmentGUI");
                    EditTreatmentGUI editTreatmentGUI = new EditTreatmentGUI(treatment.get());
                    frame.setContentPane(editTreatmentGUI.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } else if (row >= 0 && col == 6) {
                    // delete the treatment
                    // convert int to string
                    String treatmentID = table1.getValueAt(row, 0).toString();
                    System.out.println(treatmentID);
                    treatmentCRUD.delete(treatmentID);
                    // refresh the table
                    treatments = treatmentCRUD.readAll();
                    populateTable();
                }
            }
        });

        addTreatmentButton.addActionListener(e -> {
            JFrame frame = new JFrame("AddTreatmentGUI");
            AddTreatmentGUI addTreatmentGUI = new AddTreatmentGUI();
            frame.setContentPane(addTreatmentGUI.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // update the table after all the editing windows are closed
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    treatments = treatmentCRUD.readAll();
                    populateTable();
                }
            });
        });
    }

}

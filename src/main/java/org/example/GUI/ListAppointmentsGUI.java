package org.example.GUI;

import org.example.Appointment;
import org.example.AppointmentCRUD;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class ListAppointmentsGUI {
    private JTable table1;
    private JPanel panel1;
    private JButton addANewAppointmentButton;

    private List<Appointment> appointments;

    public void populateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Appointment ID");
        model.addColumn("Pacient CNP");
        model.addColumn("Medic CNP");
        model.addColumn("Date and Time");
        model.addColumn("Edit");
        model.addColumn("Delete");

        // add the data and edit delete buttons
        for (Appointment appointment : appointments) {
            model.addRow(new Object[]{appointment.getAppointmentId(), appointment.getPacientCnp(), appointment.getMedicCnp(), appointment.getDate(), "Edit", "Delete"});
        }

        table1.setModel(model);

    }

    public ListAppointmentsGUI() {
        AppointmentCRUD appointmentCRUD = AppointmentCRUD.getInstance();
        appointments = appointmentCRUD.readAll();
        populateTable();
        // make the table cells not editable
        table1.setDefaultEditor(Object.class, null);
        // add action listeners for the edit and delete buttons
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 4) {
                    // open the edit appointment gui
                    String appointmentID = table1.getValueAt(row, 0).toString();
                    Optional<Appointment> appointment = appointmentCRUD.read(appointmentID);

                    JFrame frame = new JFrame("EditAppointmentGUI");
                    EditAppointmentGUI editAppointmentGUI = new EditAppointmentGUI(appointment.get());
                    frame.setContentPane(editAppointmentGUI.getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    // update the table after all the editing windows are closed
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            appointments = appointmentCRUD.readAll();
                            populateTable();
                        }
                    });
                } else if (row >= 0 && col == 5) {
                    // delete the appointment
                    String appointmentID = table1.getValueAt(row, 0).toString();
                    appointmentCRUD.delete(appointmentID);
                    // refresh the table
                    appointments = appointmentCRUD.readAll();
                    populateTable();
                }
            }
        });
        addANewAppointmentButton.addActionListener(e -> {
            JFrame frame = new JFrame("AddAppointmentGUI");
            AddAppointmentGUI addAppointmentGUI = new AddAppointmentGUI();
            frame.setContentPane(addAppointmentGUI.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // update the table after all the editing windows are closed
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    appointments = appointmentCRUD.readAll();
                    populateTable();
                }
            });
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}

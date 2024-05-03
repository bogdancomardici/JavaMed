package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class AppointmentCRUD  implements CRUDInterface<Appointment>{
    private static AppointmentCRUD instance;

    private AppointmentCRUD() {
    }

    public static AppointmentCRUD getInstance() {
        if (instance == null) {
            instance = new AppointmentCRUD();
        }
        return instance;
    }

    @Override
    public void create(Appointment appointment) {
        try(Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(appointment.getDate());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appointment  (medic_cnp, pacient_cnp, date) VALUES (?, ?, ?)");
            preparedStatement.setString(1, appointment.getMedicCnp());
            preparedStatement.setString(2, appointment.getPacientCnp());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Appointment> read(String id) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment WHERE appointment_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                String medicCnp = resultSet.getString("medic_cnp");
                String pacientCnp = resultSet.getString("pacient_cnp");
                java.sql.Timestamp timestamp = resultSet.getTimestamp("date");
                Appointment appointment = new Appointment(medicCnp, pacientCnp, timestamp.toLocalDateTime());
                appointment.setAppointmentId(appointmentId);
                return Optional.of(appointment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    @Override
    public void update(Appointment appointment) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(appointment.getDate());
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE appointment SET medic_cnp = ?, pacient_cnp = ?, date = ? WHERE appointment_id = ?");
            preparedStatement.setString(1, appointment.getMedicCnp());
            preparedStatement.setString(2, appointment.getPacientCnp());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setInt(4, appointment.getAppointmentId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @Override
    public void delete(String id) {
        try(Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM appointment WHERE appointment_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

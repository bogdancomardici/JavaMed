package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class TreatmentCRUD implements CRUDInterface<Treatment>{

    private static TreatmentCRUD instance = null;

    private TreatmentCRUD() {
    }

    public static TreatmentCRUD getInstance() {
        if (instance == null) {
            instance = new TreatmentCRUD();
        }
        return instance;
    }

    @Override
    public void create(Treatment treatment) {
        try(Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO treatment  (pacient_cnp, treatment_name, duration_days, frequency_per_day) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, treatment.getPacientCnp());
            preparedStatement.setString(2, treatment.getTreatmentName());
            preparedStatement.setInt(3, treatment.getDurationDays());
            preparedStatement.setInt(4, treatment.getFrequencyPerDay());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Treatment treatment) {
        try(Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE treatment SET pacient_cnp = ?, treatment_name = ?, duration_days = ?, frequency_per_day = ? WHERE treatment_id = ?");
            preparedStatement.setString(1, treatment.getPacientCnp());
            preparedStatement.setString(2, treatment.getTreatmentName());
            preparedStatement.setInt(3, treatment.getDurationDays());
            preparedStatement.setInt(4, treatment.getFrequencyPerDay());
            preparedStatement.setInt(5, treatment.getTreatmentId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try(Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM treatment WHERE treatment_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Treatment> read(String id) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM treatment WHERE treatment_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int treatmentId = resultSet.getInt("treatment_id");
                String pacientCnp = resultSet.getString("pacient_cnp");
                String treatmentName = resultSet.getString("treatment_name");
                int durationDays = resultSet.getInt("duration_days");
                int frequencyPerDay = resultSet.getInt("frequency_per_day");
                Treatment treatment = new Treatment(pacientCnp, treatmentName, durationDays, frequencyPerDay);
                treatment.setTreatmentId(treatmentId);
                return Optional.of(treatment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

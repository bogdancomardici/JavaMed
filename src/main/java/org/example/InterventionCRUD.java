package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InterventionCRUD implements CRUDInterface<Intervention> {

    private static InterventionCRUD instance = null;

    private InterventionCRUD() {
    }

    public static InterventionCRUD getInstance() {
        if (instance == null) {
            instance = new InterventionCRUD();
        }
        return instance;
    }

    @Override
    public void create(Intervention intervention) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            java.sql.Date date = new java.sql.Date(intervention.getDate().getTime());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO intervention  (pacient_cnp, type, description, date) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, intervention.getPacientCnp());
            preparedStatement.setString(2, intervention.getType());
            preparedStatement.setString(3, intervention.getDescription());
            preparedStatement.setDate(4, date);
            preparedStatement.executeUpdate();
            Logger.addLog("Intervention created: " + preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Intervention intervention) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            java.sql.Date date = new java.sql.Date(intervention.getDate().getTime());
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE intervention SET pacient_cnp = ?, type = ?, description = ?, date = ? WHERE intervention_id = ?");
            preparedStatement.setString(1, intervention.getPacientCnp());
            preparedStatement.setString(2, intervention.getType());
            preparedStatement.setString(3, intervention.getDescription());
            preparedStatement.setDate(4, date);
            preparedStatement.setInt(5, intervention.getInterventionId());
            preparedStatement.executeUpdate();
            Logger.addLog("Intervention updated: " + preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM intervention WHERE intervention_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            Logger.addLog("Intervention deleted: " + preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Intervention> read(String id) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM intervention WHERE intervention_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int interventionId = resultSet.getInt("intervention_id");
                String pacientCnp = resultSet.getString("pacient_cnp");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                Intervention intervention = new Intervention(pacientCnp, type, description, date);
                intervention.setInterventionId(interventionId);
                Logger.addLog("Intervention read: " + preparedStatement);
                return Optional.of(intervention);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Intervention> readAll() {
        List<Intervention> interventions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM intervention");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int interventionId = resultSet.getInt("intervention_id");
                String pacientCnp = resultSet.getString("pacient_cnp");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                Intervention intervention = new Intervention(pacientCnp, type, description, date);
                intervention.setInterventionId(interventionId);
                interventions.add(intervention);
            }
            Logger.addLog("Interventions read: " + preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interventions;
    }
}


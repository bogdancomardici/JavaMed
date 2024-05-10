package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacientCRUD implements CRUDInterface<Pacient> {

    private static PacientCRUD instance;
    private PacientCRUD() {
    }

    public static PacientCRUD getInstance() {
        if (instance == null) {
            instance = new PacientCRUD();
        }
        return instance;
    }

    @Override
    public void create(Pacient pacient) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pacient VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, pacient.getCnp());
            preparedStatement.setString(2, pacient.getFirstName());
            preparedStatement.setString(3, pacient.getLastName());
            preparedStatement.setString(4, pacient.getPhoneNumber());
            preparedStatement.setDate(5, new java.sql.Date(pacient.getBirthDate().getTime()));
            preparedStatement.setString(6, pacient.getAddress());
            preparedStatement.setString(7, pacient.getBloodType());
            preparedStatement.setInt(8, pacient.getHeight());
            preparedStatement.setInt(9, pacient.getWeight());
            preparedStatement.setString(10, pacient.getChronicDiseases());
            System.out.println(preparedStatement);
            Logger.addLog("Pacient created: " + preparedStatement);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    @Override
    public void update(Pacient pacient) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pacient SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, address = ?, blood_type = ?, height = ?, weight = ?, chronic_diseases = ? WHERE cnp = ?")) {
            preparedStatement.setString(1, pacient.getLastName());
            preparedStatement.setString(2, pacient.getFirstName());
            preparedStatement.setString(3, pacient.getPhoneNumber());
            preparedStatement.setDate(4,new java.sql.Date(pacient.getBirthDate().getTime()));
            preparedStatement.setString(5, pacient.getAddress());
            preparedStatement.setString(6, pacient.getBloodType());
            preparedStatement.setInt(7, pacient.getHeight());
            preparedStatement.setInt(8, pacient.getWeight());
            preparedStatement.setString(9, pacient.getChronicDiseases());
            preparedStatement.setString(10, pacient.getCnp());
            Logger.addLog("Pacient updated: " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(String cnp) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pacient WHERE cnp = ?")) {
            preparedStatement.setString(1, cnp);
            preparedStatement.executeUpdate();
            Logger.addLog("Pacient deleted: " + preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    @Override
    public Optional<Pacient> read(String cnp) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacient WHERE cnp = ?")) {
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                Date birthDate = resultSet.getDate("birth_date");
                String address = resultSet.getString("address");
                String bloodType = resultSet.getString("blood_type");
                int height = resultSet.getInt("height");
                int weight = resultSet.getInt("weight");
                String chronicDiseases = resultSet.getString("chronic_diseases");
                Pacient pacient = new Pacient(cnp, firstName, lastName, phoneNumber, birthDate, address, bloodType, height, weight, chronicDiseases);
                Logger.addLog("Pacient read: " + preparedStatement);
                return Optional.of(pacient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // readAll function
    public List<Pacient> readAll() {
        List<Pacient> pacients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacient")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String cnp = resultSet.getString("cnp");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                Date birthDate = resultSet.getDate("birth_date");
                String address = resultSet.getString("address");
                String bloodType = resultSet.getString("blood_type");
                int height = resultSet.getInt("height");
                int weight = resultSet.getInt("weight");
                String chronicDiseases = resultSet.getString("chronic_diseases");
                Pacient pacient = new Pacient(cnp, firstName, lastName, phoneNumber, birthDate, address, bloodType, height, weight, chronicDiseases);
                pacients.add(pacient);
            }
            Logger.addLog("Pacients read: " + preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacients;
    }
}

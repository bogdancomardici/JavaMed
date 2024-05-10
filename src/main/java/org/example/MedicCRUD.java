package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicCRUD implements CRUDInterface<Medic>{

    private static MedicCRUD instance = null;

    private MedicCRUD() {
    }

    public static MedicCRUD getInstance() {
        if (instance == null) {
            instance = new MedicCRUD();
        }
        return instance;
    }

    @Override
    public void create(Medic medic) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medic VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, medic.getCnp());
            preparedStatement.setString(2, medic.getFirstName());
            preparedStatement.setString(3, medic.getLastName());
            preparedStatement.setString(4, medic.getPhoneNumber());
            preparedStatement.setDate(5, new java.sql.Date(medic.getBirthDate().getTime()));
            preparedStatement.setString(6, medic.getAddress());
            preparedStatement.setString(7, medic.getSpeciality());
            preparedStatement.setInt(8, medic.getYearsOfExperience());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            Logger.addLog("Medic created: " + preparedStatement);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Medic medic) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medic SET first_name = ?, last_name = ?, phone_number = ?, birth_date = ?, address = ?, speciality = ?, years_experience = ? WHERE cnp = ?")) {
            preparedStatement.setString(1, medic.getFirstName());
            preparedStatement.setString(2, medic.getLastName());
            preparedStatement.setString(3, medic.getPhoneNumber());
            preparedStatement.setDate(4,new java.sql.Date(medic.getBirthDate().getTime()));
            preparedStatement.setString(5, medic.getAddress());
            preparedStatement.setString(6, medic.getSpeciality());
            preparedStatement.setInt(7, medic.getYearsOfExperience());
            preparedStatement.setString(8, medic.getCnp());
            preparedStatement.executeUpdate();
            Logger.addLog("Medic updated: " + preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Medic> read(String cnp) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medic WHERE cnp = ?")) {
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                Date birthDate = resultSet.getDate("birth_date");
                String address = resultSet.getString("address");
                String speciality = resultSet.getString("speciality");
                int yearsOfExperience = resultSet.getInt("years_experience");
                Logger.addLog("Medic read: " + preparedStatement);
                return Optional.of(new Medic(cnp, firstName, lastName, phoneNumber, birthDate, address, speciality, yearsOfExperience));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void delete(String cnp) {
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medic WHERE cnp = ?")) {
            preparedStatement.setString(1, cnp);
            preparedStatement.executeUpdate();
            Logger.addLog("Medic deleted: " + preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medic> readAll() {
        List<Medic> medics = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBConnection.URL, DBConnection.USER, DBConnection.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medic")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cnp = resultSet.getString("cnp");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                Date birthDate = resultSet.getDate("birth_date");
                String address = resultSet.getString("address");
                String speciality = resultSet.getString("speciality");
                int yearsOfExperience = resultSet.getInt("years_experience");
                medics.add(new Medic(cnp, firstName, lastName, phoneNumber, birthDate, address, speciality, yearsOfExperience));
            }
            Logger.addLog("Medics read: " + preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medics;
    }
}

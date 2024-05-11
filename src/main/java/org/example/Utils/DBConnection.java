package org.example.Utils;

public class DBConnection {
    public static final String URL = "jdbc:postgresql://localhost:5432/JavaMed";
    public static final String USER="bogdan";
    public static final String PASSWORD="tw2023pa55";
    public static DBConnection instance;

    private DBConnection() {

    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

}

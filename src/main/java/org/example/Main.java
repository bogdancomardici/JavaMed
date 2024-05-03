package org.example;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

    InterventionCRUD interventionCRUD = InterventionCRUD.getInstance();
    interventionCRUD.delete("1");
    }
}
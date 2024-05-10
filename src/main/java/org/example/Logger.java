package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Logger {

    private static String path = "src/main/java/org/example/logs.txt";
    public static void addLog(String log) {
        // create timestamp
        String timestamp = java.time.LocalDateTime.now().toString();
        // create log message
        String logMessage = timestamp + " - " + log;

        // write log message to file
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(logMessage);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

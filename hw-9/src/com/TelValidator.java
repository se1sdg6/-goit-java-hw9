package com;

import java.io.*;

public class TelValidator {
    public void validate(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.matches("\\d{3}-\\d{3}-\\d{4}") ||
                        line.matches("\\(\\d{3}\\)\\s\\d{3}-\\d{4}")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong :(");

            e.printStackTrace();
        }
    }
}

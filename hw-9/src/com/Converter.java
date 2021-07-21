package com;

import java.io.*;

public class Converter {
    public void toJSON(String fileName) {
        String destinationFileName = fileName.substring(0, fileName.lastIndexOf("\\") + 1).concat("user.json");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName, true))) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[" + "\n");

            String[] fields = reader.readLine().split(" ");
            while (reader.ready()) {
                String[] data = reader.readLine().split(" ");

                stringBuilder.append(
                "{" + "\n"
                + "\"" + fields[0] + "\":" + "\"" + data[0] + "\"," + "\n"
                + "\"" + fields[1] + "\":" + data[1] + "\n"
                + "},\n");
            }

            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
            stringBuilder.append("]");

            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong :(");

            e.printStackTrace();
        }
    }
}

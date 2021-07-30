package com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class JsonParser {
    public void toJSON(String fileName) {
        String destinationFileName = fileName.substring(0, fileName.lastIndexOf("\\") + 1).concat("user.json");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName, true))) {
            List<User> users = new ArrayList<>();

            reader.readLine();
            while (reader.ready()) {
                String[] data = reader.readLine().split(" ");

                users.add(new User(data[0], Integer.parseInt(data[1])));
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringBuilder sb = new StringBuilder();
             for (User u : users) {
                sb.append(gson.toJson(u) + ",\n");
            }

             writer.write(sb.substring(0, sb.length() - 2));
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong :(");

            e.printStackTrace();
        }
    }
}

package com;

import java.io.*;
import java.util.*;

public class WordsCounter {

    public void countWords(String fileName) {
        HashMap<String, Integer> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {
                String[] words = reader.readLine().split(" ");

                for (String word : words) {
                    if (data.containsKey(word)) {
                        data.put(word, (data.get(word) + 1));
                    } else {
                        data.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TreeSet<Integer> keys = new TreeSet<>(data.values());

        while (keys.size() > 0) {
            int key = keys.pollLast();

            for (HashMap.Entry<String, Integer> pair : data.entrySet()) {
                if (pair.getValue().equals(key)) {
                    System.out.println(pair.getKey() + " " + pair.getValue());
                }
            }
        }
    }
}

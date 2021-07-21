package com;

import java.io.*;

public class ClassTester {

    public static void main(String[] args) throws IOException {
        ClassTester tester = new ClassTester();

        try {
            System.out.println("Validator");
            tester.testTelValidator();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        try {
            System.out.println("Converter");
            tester.testConverter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        try {
            System.out.println("Words Counter");
            tester.testWordsCounter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void testTelValidator() throws IOException {
        File tmpFile = File.createTempFile("data", null);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile.getAbsolutePath(), true))) {
            writer.write("987-123-4567" + "\n");
            writer.write("123 456 7890" + "\n");
            writer.write("(123) 456-7890" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        TelValidator telValidator = new TelValidator();
        telValidator.validate(tmpFile.getAbsolutePath());

        tmpFile.deleteOnExit();
    }

    void testConverter() throws IOException {
        File tmpFile = File.createTempFile("data", null);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile.getAbsolutePath(), true))) {
            writer.write("name age" + "\n");
            writer.write("alice 21" + "\n");
            writer.write("ryan 30" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Converter converter = new Converter();
        converter.toJSON(tmpFile.getAbsolutePath());

        String tmpFileName = tmpFile.getAbsolutePath();
        String destinationFileName = tmpFileName.substring(0, tmpFileName.lastIndexOf("\\") + 1).concat("user.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(destinationFileName))) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(destinationFileName);
        if (file.delete()) {
            System.out.println("File user.json deleted");
        }

        tmpFile.deleteOnExit();
    }

    void testWordsCounter() throws IOException {
        File tmpFile = File.createTempFile("data", null);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile.getAbsolutePath(), true))) {
            writer.write("the day is sunny the the\n" +
                    "the sunny is is");
        } catch (IOException e) {
            e.printStackTrace();
        }

        WordsCounter counter = new WordsCounter();
        counter.countWords(tmpFile.getAbsolutePath());

        tmpFile.deleteOnExit();
    }
}

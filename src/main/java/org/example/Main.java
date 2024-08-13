package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        System.out.println(Arrays.toString(list.toArray()));
        String json = listToJson(list);
        System.out.println(json);
        writeString(json);
    }

    private static void writeString(String json) {
        try (FileWriter writer = new FileWriter("person.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        List<Employee> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                Employee employee = new Employee(
                        Long.valueOf(nextLine[0]),
                        nextLine[1],
                        nextLine[2],
                        nextLine[3],
                        Integer.valueOf(nextLine[4])
                );
                list.add(employee);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return list;

    }

    private static String listToJson(List<Employee> list) {
        return new Gson().toJson(list);
    }


}
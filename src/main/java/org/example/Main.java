package org.example;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        System.out.println(Arrays.toString(list.toArray()));


    }

    private static  List<Employee> parseCSV(String[] columnMapping, String fileName) {
        List<Employee>list =new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
            // Массив считанных строк
            String[] nextLine;

            // Читаем CSV построчно
            while ((nextLine = reader.readNext()) != null) {
                // Работаем с прочитанными данными.
                Employee employee=new Employee(
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


}
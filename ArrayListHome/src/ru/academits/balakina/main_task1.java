package ru.academits.balakina;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main_task1 {
    public static void main(String[] args) {
        ArrayList<String> rows = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                rows.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Считаны строки:");
        for (String s : rows) {
            System.out.println(s);
        }
    }
}

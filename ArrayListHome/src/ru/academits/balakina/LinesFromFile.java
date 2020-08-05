package ru.academits.balakina;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LinesFromFile {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл для считывания строк не найден");
        }

        System.out.println("Считаны строки:");
        for (String s : lines) {
            System.out.println(s);
        }
    }
}

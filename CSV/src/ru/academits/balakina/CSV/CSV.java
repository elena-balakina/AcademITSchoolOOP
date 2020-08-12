package ru.academits.balakina.CSV;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {

    public CSV() {
    }

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл для считывания строк не найден");
        }

        return lines;
    }

    public void writeHtmlToFile(ArrayList<String> lines, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл для записи не создан");
        }
    }

    public ArrayList<String> convertLineFromCSVToHtml(ArrayList<String> linesCSV) {
        ArrayList<String> lines = new ArrayList<>();

        lines.add("<table>");

        for (String s : linesCSV) {
            lines.add("<tr>");

            String[] items = s.split(",");
            for (String item : items) {
                lines.add("<td>" + item + "</td>");
            }

            lines.add("</tr>");
        }

        lines.add("</table>");

        return lines;
    }
}

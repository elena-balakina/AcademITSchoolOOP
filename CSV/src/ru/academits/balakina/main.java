package ru.academits.balakina;

import ru.academits.balakina.CSV.CSV;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        CSV csvFile = new CSV();

        ArrayList<String> linesCSV = csvFile.readFromFile("input.txt");

        System.out.println("Считаны строки:");
        for (String s : linesCSV) {
            System.out.println(s);
        }

        ArrayList<String> linesHTML = csvFile.convertLineFromCSVToHtml(linesCSV);

        System.out.println("Строки конвертированы в HTML:");
        for (String s : linesHTML) {
            System.out.println(s);
        }

        csvFile.writeHtmlToFile(linesHTML, "output.txt");
        System.out.println("HTML строки записаны в файл");
    }
}

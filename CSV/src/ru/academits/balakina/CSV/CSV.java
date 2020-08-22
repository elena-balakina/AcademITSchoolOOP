package ru.academits.balakina.CSV;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSV {
    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try (Scanner scanner = new Scanner(new FileInputStream(inputFilePath));
             PrintWriter writer = new PrintWriter(new FileOutputStream(outputFilePath))) {
            // открывающие теги
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title> Table from CSV </title>");
            writer.println("<meta name = \"Table from CSV\" content = \"HTML,CSS,PHP,JavaScript charset=\"UTF-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table>");

            // вспомогательные переменные
            boolean insideQuotes = false;
            boolean newLine = true;
            boolean trEnd = false;

            // построчное считывание файла
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                if (newLine) {
                    if (currentLine.length() > 0) {
                        writer.print("<tr> <td>");
                    } else {
                        writer.println();
                        continue;
                    }
                }

                // считываем строку посимвольно
                for (int i = 0; i < currentLine.length(); i++) {
                    char currentChar = currentLine.charAt(i);
                    int lastIndex = currentLine.length() - 1;

                    if (i == lastIndex) {
                        trEnd = true;
                    }

                    if (currentChar == '"' && !insideQuotes) {
                        insideQuotes = true;
                        continue;
                    }

                    if (insideQuotes && !trEnd && currentChar == '"' && currentLine.charAt(i + 1) == '"') {
                        writer.print(currentChar);
                        i++;
                        continue;
                    }

                    if (insideQuotes && trEnd && currentChar == '"') {
                        insideQuotes = false;
                        continue;
                    }

                    if (insideQuotes && !trEnd && currentChar == '"' && currentLine.charAt(i + 1) == ',') {
                        insideQuotes = false;
                        newLine = true;
                        continue;
                    }

                    if (insideQuotes && trEnd) {
                        writer.print("<br/>");
                        newLine = false;
                        continue;
                    }

                    if (currentLine.charAt(i) == ',') {
                        if (insideQuotes) {
                            writer.print(currentChar);
                        } else {
                            writer.print("</td> <td>");
                        }
                    } else {
                        writer.print(currentChar);
                    }
                }

                if (newLine) {
                    writer.println("</td> </tr>");
                }

                trEnd = false;
            }

            // закрывающие теги
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");

        } catch (FileNotFoundException e) {
            System.out.println("Файл для считывания строк не найден");
        }
    }
}
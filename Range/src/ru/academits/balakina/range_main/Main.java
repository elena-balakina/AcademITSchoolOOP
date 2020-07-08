package ru.academits.balakina.range_main;

import ru.academits.balakina.range.Range;

import java.util.Scanner;

import static ru.academits.balakina.range.Range.printArrayOfRange;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите from первого интервала:");
        double range1From = scanner.nextDouble();

        System.out.println("Введите to первого интервала:");
        double range1To = scanner.nextDouble();

        Range range1 = new Range(range1From, range1To);

        System.out.printf("Длина первого интервала = %.2f%n", range1.getLength());

        System.out.println("Введите значение для проверки принадлежности интервалу:");
        double numberToCheck = scanner.nextDouble();

        if (range1.isInside(numberToCheck)) {
            System.out.println("Введенное значение принадлежит интервалу");
        } else {
            System.out.println("Введенное значение не принадлежит интервалу");
        }

        System.out.println("Введите from второго интервала:");
        double range2From = scanner.nextDouble();

        System.out.println("Введите to второго интервала:");
        double range2To = scanner.nextDouble();

        Range range2 = new Range(range2From, range2To);

        System.out.printf("Длина второго интервала = %.2f%n", range2.getLength());

        Range intersection = range1.getIntersectionWith(range2);

        if (intersection == null) {
            System.out.println("Интервалы не пересекаются");
        } else {
            System.out.printf("Пересечение интервалов: [%.2f; %.2f]%n", intersection.getFrom(), intersection.getTo());
        }

        System.out.println("Объединение интервалов: ");

        printArrayOfRange(range1.getUnionWith(range2));

        System.out.println("Разность интервалов: ");

        if (range2.getDifferenceFrom(range1).length == 0) {
            System.out.println("Разность интервалов является пустым множеством");
        } else {
            printArrayOfRange(range2.getDifferenceFrom(range1));
        }
    }
}

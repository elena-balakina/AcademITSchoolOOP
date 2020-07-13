package ru.academits.balakina.range_main;

import ru.academits.balakina.range.Range;

import java.util.Scanner;

import static ru.academits.balakina.range.Range.printRangesArray;

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

        System.out.println("Пересечение интервалов: ");

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Интервалы не пересекаются");
        } else {
            System.out.println(intersection.toString());
        }

        System.out.println("Объединение интервалов: ");

        printRangesArray(range1.getUnion(range2));

        System.out.println("Разность интервалов: ");

        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.println("Разность интервалов является пустым множеством");
        } else {
            printRangesArray(difference);
        }
    }
}

package ru.academits.range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
        System.out.printf("Длина интервала = %.2f%n", range1.getLength());

        System.out.println("Введите значение для проверки принадлежности интервалу:");
        double numberToCheck = scanner.nextDouble();

        if (range1.isInside(numberToCheck)) {
            System.out.println("Введенное значение принадлежит интервалу");
        } else {
            System.out.println("Введенное значение не принадлежит интервалу");
        }*/

        System.out.println("Введите from первого интервала:");
        double firstRangeFrom = scanner.nextDouble();

        System.out.println("Введите to первого интервала:");
        double firstRangeTo = scanner.nextDouble();

        Range firstRange = new Range(firstRangeFrom, firstRangeTo);

        System.out.printf("Длина первого интервала = %.2f%n", firstRange.getLength());

        System.out.println("Введите значение для проверки принадлежности интервалу:");
        double numberToCheck = scanner.nextDouble();

        if (firstRange.isInside(numberToCheck)) {
            System.out.println("Введенное значение принадлежит интервалу");
        } else {
            System.out.println("Введенное значение не принадлежит интервалу");
        }

        System.out.println("Введите from второго интервала:");
        double secondRangeFrom = scanner.nextDouble();

        System.out.println("Введите to второго интервала:");
        double secondRangeTo = scanner.nextDouble();

        Range secondRange = new Range(secondRangeFrom, secondRangeTo);

        System.out.printf("Длина второго интервала = %.2f%n", secondRange.getLength());

        Range intersection = firstRange.getIntersectionWith(secondRange);

        if (intersection == null) {
            System.out.println("Интервалы не пересекаются");
        } else {
            System.out.printf("Пересечение интервалов: [%.2f; %.2f]%n", intersection.getFrom(), intersection.getTo());
        }

        System.out.println("Объединение интервалов: ");

        for (Range range : firstRange.getUnificationWith(secondRange)) {
            System.out.printf("[%.2f; %.2f]%n", range.getFrom(), range.getTo());
        }

        System.out.println("Разность интервалов: ");

        if (secondRange.getSubtractionFrom(firstRange) == null) {
            System.out.println("Разность интервалов является пустым множеством");
        } else {
            for (Range range : secondRange.getSubtractionFrom(firstRange)) {
                System.out.printf("[%.2f; %.2f]%n", range.getFrom(), range.getTo());
            }
        }
    }
}

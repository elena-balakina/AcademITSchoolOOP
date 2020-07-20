package ru.academits.balakina.vector_main;

import ru.academits.balakina.vector.Vector;

import static ru.academits.balakina.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        try {
            Vector vector = new Vector(-3);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        Vector vector1 = new Vector(3);

        System.out.printf("Размерность вектора 1 = %d%n", vector1.getSize());
        System.out.printf("Компоненты вектора 1: %s%n", vector1);
        System.out.println();

        Vector vector2 = new Vector(new double[]{4.0, -3.0, 5.0, 7.0});

        System.out.printf("Размерность вектора 2 = %d%n", vector2.getSize());
        System.out.printf("Компоненты вектора 2: %s%n", vector2);
        System.out.println();

        Vector vector3 = new Vector(vector2);

        System.out.printf("Размерность вектора 3 = %d%n", vector3.getSize());
        System.out.printf("Компоненты вектора 3: %s%n", vector3);
        System.out.println();

        Vector vector4 = new Vector(10, new double[]{4.0, -3.0, 5.0, 6.0});

        System.out.printf("Размерность вектора 4 = %d%n", vector4.getSize());
        System.out.printf("Компоненты вектора 4: %s%n", vector4);
        System.out.println();

        // СЛОЖЕНИЕ
        vector1.add(vector2);
        System.out.printf("Вектор 1 + Вектор 2 = : %s%n", vector1);

        // ВЫЧИТАНИЕ
        vector1.subtract(vector2);
        System.out.printf("Вектор 1 - Вектор 2 = : %s%n", vector1);

        // УМНОЖЕНИЕ НА СКАЛЯР
        int scalar = 2;
        vector2.multiplyByScalar(scalar);
        System.out.printf("Вектор 2 * %d = : %s%n", scalar, vector2);

        // РАЗВОРОТ ВЕКТОРА (умножение всех компонент на -1)
        vector2.reverseVector();
        System.out.printf("Вектор 2. Разворот = : %s%n", vector2);

        // Получение длины вектора
        System.out.printf("Длина Вектора 2 = : %.2f%n", vector2.vectorLength());

        // Получение компонента вектора по индексу
        System.out.printf("Первый компонент Вектора 2 = : %.2f%n", vector2.getComponentByIndex(0));

        // Установка компонента вектора по индексу
        vector2.setComponentByIndex(0, 0);
        System.out.printf("Первый компонент Вектора 2 после изменения его на 0 = : %.2f%n", vector2.getComponentByIndex(0));

        // Статические методы
        System.out.printf("Вектор 2 + Вектор 3 = : %s%n", add(vector2, vector3));
        System.out.printf("Вектор 2 - Вектор 3 = : %s%n", subtract(vector2, vector3));
        System.out.printf("Вектор 4 * Вектор 2 = : %.2f%n", scalarMultiplication(vector4, vector2));
    }
}

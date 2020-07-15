package ru.academits.balakina.vector_main;

import ru.academits.balakina.vector.Vector;

import static ru.academits.balakina.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3);

        System.out.printf("Размерность вектора 1 = %d%n", vector1.getSize());
        System.out.printf("Компоненты вектора 1: %s%n", vector1.toString());
        System.out.println();

        Vector vector2 = new Vector(new double[]{4.0, -3.0, 5.0});

        System.out.printf("Размерность вектора 2 = %d%n", vector2.getSize());
        System.out.printf("Компоненты вектора 2: %s%n", vector2.toString());
        System.out.println();

        Vector vector3 = new Vector(vector2);

        System.out.printf("Размерность вектора 3 = %d%n", vector3.getSize());
        System.out.printf("Компоненты вектора 3: %s%n", vector3.toString());
        System.out.println();

        Vector vector4 = new Vector(10, new double[]{4.0, -3.0, 5.0, 6.0});

        System.out.printf("Размерность вектора 4 = %d%n", vector4.getSize());
        System.out.printf("Компоненты вектора 4: %s%n", vector4.toString());
        System.out.println();
/*
        // СЛОЖЕНИЕ
        vector1.add(vector2);
        System.out.printf("Вектор 1 + Вектор 2 = : %s%n", vector1.toString());

        // ВЫЧИТАНИЕ
        vector1.subtract(vector2);
        System.out.printf("Вектор 1 - Вектор 2 = : %s%n", vector1.toString());

        // УМНОЖЕНИЕ НА СКАЛЯР
        int scalar = 2;
        vector2.multiplyByScalar(scalar);
        System.out.printf("Вектор 2 * %d = : %s%n", scalar, vector2.toString());

        // РАЗВОРОТ ВЕКТОРА (умножение всех компонент на -1)
        vector2.reverseVector();
        System.out.printf("Вектор 2. Разворот = : %s%n", vector2.toString());

        System.out.printf("Длина Вектора 2 = : %.2f%n", vector2.vectorLength());
        System.out.printf("Первый компонент Вектора 2 = : %.2f%n", vector2.getComponent(0));

        vector2.setComponent(0, 0);
        System.out.printf("Первый компонент Вектора 2 стал = : %.2f%n", vector2.getComponent(0)); */

        System.out.printf("Вектор 2 + Вектор 3 = : %s%n", (add(vector2, vector3)).toString());
        System.out.printf("Вектор 2 - Вектор 3 = : %s%n", (subtract(vector2, vector3)).toString());
        System.out.printf("Вектор 2 * Вектор 3 = : %.2f%n", scalarMultiplication(vector2, vector3));
    }
}

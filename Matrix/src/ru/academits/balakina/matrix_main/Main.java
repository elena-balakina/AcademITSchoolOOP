package ru.academits.balakina.matrix_main;

import ru.academits.balakina.matrix.Matrix;
import ru.academits.balakina.vector.Vector;

import static ru.academits.balakina.matrix.Matrix.add;
import static ru.academits.balakina.matrix.Matrix.subtract;

public class Main {
    public static void main(String[] args) {
        // Проверяем конструктор а. Matrix(n, m) – матрица нулей размера nxm
        Matrix matrixA = new Matrix(3, 3);

        System.out.println(matrixA);
        System.out.printf("Размеры матрицы A: %s%n", matrixA.getMatrixSizes());
        System.out.println();

        // Проверяем конструктор b.	Matrix(Matrix) – конструктор копирования
        Matrix matrixB = new Matrix(matrixA);

        System.out.println(matrixB);
        System.out.printf("Размеры матрицы B: %s%n", matrixB.getMatrixSizes());
        System.out.println();

        // Проверяем конструктор c.	Matrix(double[][]) – из двумерного массива
        double[][] array = {{1.0, 2.0}, {1.0, 2.0}, {1.0, 2.0}};
        Matrix matrixC = new Matrix(array);

        System.out.println(matrixC);
        System.out.printf("Размеры матрицы C: %s%n", matrixC.getMatrixSizes());
        System.out.println();


        // Проверяем конструктор d. Matrix(Vector[]) – из массива векторов-строк
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0});
        Vector vector3 = new Vector(3);

        Vector[] vectorsArray = new Vector[]{vector1, vector2, vector3};
        Matrix matrixD = new Matrix(vectorsArray);

        System.out.println(matrixD);
        System.out.printf("Размеры матрицы D: %s%n", matrixD.getMatrixSizes());
        System.out.println();

        // Проверка сложения матриц
        System.out.printf("Матрица А + Матрица В: %s%n", matrixA.add(matrixB));
        System.out.printf("Матрица А + Матрица C: %s%n", matrixA.add(matrixC));
        System.out.printf("Матрица C + Матрица D: %s%n", matrixC.add(matrixD));

        // Проверка вычитания матриц
        System.out.printf("Матрица C - Матрица D: %s%n", matrixC.subtract(matrixD));
        System.out.printf("Матрица C - {-10, -20, -30}: %s%n", matrixC.subtract(
                new Matrix(new double[][]{{-10.0, -20.0, -30.0}, {-10.0, -20.0, -30.0}, {-10.0, -20.0, -30.0}})));
        System.out.println();

        // Проверка умножения на скаляр
        System.out.println("Матрица С: " + matrixC);
        System.out.println("Матрица С * 5: " + matrixC.multiplyByScalar(5));
        System.out.println();

        // Получение вектора-столбца по индексу
        System.out.println("Первый столбец матрицы С: " + matrixC.getColumnByIndex(0));
        System.out.println();

        // Получение вектора-строки по индексу
        System.out.println("Первая строка матрицы С: " + matrixC.getRowByIndex(0));
        System.out.println();

        // Задание вектора-строки по индексу
        System.out.println("Зададим первую строку матрицы С - {4.0, -3.0, 5.0}");
        matrixC.setRowByIndex(new Vector(new double[]{4.0, -3.0, 5.0}), 0);
        System.out.println("Теперь первая строка матрицы С: " + matrixC.getRowByIndex(0));
        System.out.println();

        System.out.println("Зададим первую строку матрицы С - {10.0, 10.0}");
        matrixC.setRowByIndex(new Vector(new double[]{10.0, 10.0}), 0);
        System.out.println("Теперь первая строка матрицы С: " + matrixC.getRowByIndex(0));
        System.out.println();

        // Статические методы - сложение матриц
        System.out.println("Матрица A: " + matrixA);
        System.out.println("Матрица С: " + matrixC);
        System.out.println("Матрица A + Матрица С: " + add(matrixA, matrixC));
        System.out.println();

        // Статические методы - вычитание матриц
        System.out.println("Матрица A: " + matrixA);
        System.out.println("Матрица С: " + matrixC);
        System.out.println("Матрица A - Матрица С: " + subtract(matrixA, matrixC));
    }
}

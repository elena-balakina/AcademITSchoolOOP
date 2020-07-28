package ru.academits.balakina.matrix;

import ru.academits.balakina.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    // a.	Matrix(n, m) – матрица нулей размера nxm
    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть больше нуля");
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    // b.	Matrix(Matrix) – конструктор копирования
    public Matrix(Matrix matrix) {
        rows = Arrays.copyOf(matrix.rows, matrix.getRowsCount());

        int n = matrix.rows.length;
        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }

    }

    // c.	Matrix(double[][]) – из двумерного массива
    public Matrix(double[][] array) {
        int n = array.length;
        int maxLength = 0;

        for (double[] element : array) {
            maxLength = Math.max(maxLength, element.length);
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    // d.	Matrix(Vector[]) – из массива векторов-строк
    public Matrix(Vector[] vectorsArray) {
        int n = vectorsArray.length;
        int maxLength = 0;

        for (Vector vector : vectorsArray) {
            maxLength = Math.max(maxLength, vector.getSize());
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(maxLength);
            rows[i].add(vectorsArray[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public String getMatrixSizes() {
        return String.format("%d x %d", this.getRowsCount(), this.getColumnsCount());
    }

    // Сложение матриц
    public Matrix getSum(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("При сложении размерности матриц должны быть одинаковыми");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    // Вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("При вычитании размерности матриц должны быть одинаковыми");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }

        return this;
    }

    // Умножение на скаляр
    public Matrix multiplyByScalar(int scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScalar(scalar);
        }

        return this;
    }

    // Получение вектора-столбца по индексу
    public Vector getColumnByIndex(int index) {
        Vector column = new Vector(this.getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            column.setComponentByIndex(i, rows[i].getComponentByIndex(index));
        }

        return column;
    }

    // Получение вектора-строки по индексу
    public Vector getRowByIndex(int index) {
        return rows[index];
    }

    // Задание вектора-строки по индексу
    public void setRowByIndex(int index, Vector vector) {
        if (vector.getSize() > rows[index].getSize()) {
            throw new IllegalArgumentException("Размер вектора превышает размер строки матрицы");
        }

        if (vector.getSize() < rows[index].getSize()) {

            Vector vectorCopy = new Vector(rows[index].getSize());
            for (int i = 0; i < vector.getSize(); i++) {
                vectorCopy.setComponentByIndex(i, vector.getComponentByIndex(i));
            }

            rows[index] = vectorCopy;
            return;
        }

        rows[index] = vector;
    }

    // Умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество компонентов вектора должно быть равно количеству столбцов матрицы");
        }

        Vector result = new Vector(this.getRowsCount());

        for (int i = 0; i < this.getRowsCount(); i++) {
            double value = 0;

            for (int j = 0; j < rows[i].getSize(); j++) {
                value = rows[i].getComponentByIndex(j) * vector.getComponentByIndex(j);
            }

            result.setComponentByIndex(i, value);
        }

        return result;
    }

    // TODO: Транспонирование матрицы

    // TODO: Вычисление определителя матрицы


    // Статические методы - сложение матриц
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("При сложении размерности матриц должны быть одинаковыми");
        }

        Matrix matrixResult = new Matrix(matrix1);

        matrixResult.getSum(matrix2);

        return matrixResult;
    }

    // Статические методы - вычитание матриц
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("При вычитании размерности матриц должны быть одинаковыми");
        }

        Matrix matrixResult = new Matrix(matrix1);

        matrixResult.getDifference(matrix2);

        return matrixResult;
    }

    // Статические методы - умножение матриц
    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("При перемножении матриц число столбцов первой матрицы должно быть равно числу строк второй");
        }

        Matrix matrixResult = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            Vector vector = new Vector(matrix1.getRowsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {

                vector.setComponentByIndex(j, Vector.getScalarMultiplication(matrix1.rows[i], matrix2.getColumnByIndex(j)));
            }

            matrixResult.setRowByIndex(i, vector);
        }

        return matrixResult;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");

        for (int i = 0; i < this.getRowsCount(); i++) {
            stringBuilder.append(this.rows[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(" }");

        return stringBuilder.toString();
    }
}

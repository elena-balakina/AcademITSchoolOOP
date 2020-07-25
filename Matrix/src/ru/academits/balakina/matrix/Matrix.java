package ru.academits.balakina.matrix;


import ru.academits.balakina.vector.Vector;

import java.util.Arrays;

import static ru.academits.balakina.vector.Vector.scalarMultiplication;

public class Matrix {
    private Vector[] rows;

    // a.	Matrix(n, m) – матрица нулей размера nxm
    public Matrix(int n, int m) {
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
    public Matrix add(Matrix matrix) {

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    // Вычитание матриц
    public Matrix subtract(Matrix matrix) {

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
            column.setComponentByIndex(rows[i].getComponentByIndex(index), i);
        }

        return column;
    }

    // Получение вектора-строки по индексу
    public Vector getRowByIndex(int index) {
        return rows[index];
    }

    // Задание вектора-строки по индексу
    public void setRowByIndex(Vector vector, int index) {
        if (vector.getSize() < rows[index].getSize()) {

            Vector vectorCopy = new Vector(rows[index].getSize());
            for (int i = 0; i < vector.getSize(); i++) {
                vectorCopy.setComponentByIndex(vector.getComponentByIndex(i), i);
            }

            rows[index] = vectorCopy;
            return;
        }

        rows[index] = vector;
    }

    // Статические методы - сложение матриц
    public static Matrix add(Matrix matrix1, Matrix matrix2) {

        Matrix matrixResult = new Matrix(matrix1);

        matrixResult.add(matrix2);

        return matrixResult;
    }

    // Статические методы - вычитание матриц
    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {

        Matrix matrixResult = new Matrix(matrix1);

        matrixResult.subtract(matrix2);

        return matrixResult;
    }

    // TODO: Статические методы - умножение матриц
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        return null;
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

package ru.academits.balakina.matrix;

import ru.academits.balakina.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    // a.	Matrix(n, m) – матрица нулей размера nxm
    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Передана некорректная размерность матрицы: " + rowsCount + " x " + columnsCount + ". Размерность матрицы должна быть больше нуля");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    // b.	Matrix(Matrix) – конструктор копирования
    public Matrix(Matrix matrix) {
        int rowsCount = matrix.rows.length;
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    // c.	Matrix(double[][]) – из двумерного массива
    public Matrix(double[][] array) {
        int rowsCount = array.length;
        int maxLength = 0;

        for (double[] element : array) {
            maxLength = Math.max(maxLength, element.length);
        }

        if (maxLength == 0) {
            throw new IllegalArgumentException("Передан некорректный массив. Размерность матрицы должна быть больше нуля");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    // d.	Matrix(Vector[]) – из массива векторов-строк
    public Matrix(Vector[] vectorsArray) {
        int rowsCount = vectorsArray.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("Передан некорректный массив размерностью " + rowsCount + ". Размерность матрицы должна быть больше нуля");
        }

        int maxLength = 0;

        for (Vector vector : vectorsArray) {
            maxLength = Math.max(maxLength, vector.getSize());
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
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
            throw new IllegalArgumentException("При сложении размерности матриц должны быть одинаковыми: " +
                    getRowsCount() + " x " + getColumnsCount() + " не равно " + matrix.getRowsCount() + " x " + matrix.getColumnsCount());
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    // Вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("При вычитании размерности матриц должны быть одинаковыми " +
                    getRowsCount() + " x " + getColumnsCount() + " не равно " + matrix.getRowsCount() + " x " + matrix.getColumnsCount());
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }

        return this;
    }

    // Умножение на скаляр
    public Matrix multiplyByScalar(double scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScalar(scalar);
        }

        return this;
    }

    // Получение вектора-столбца по индексу
    public Vector getColumnByIndex(int index) {
        if (index >= getColumnsCount()) {
            throw new IllegalArgumentException("Переданный индекс превышает размерность матрицы по столбцам : " + index + " >= " + getColumnsCount());
        }

        if (index < 0) {
            throw new IllegalArgumentException("Переданный индекс = : " + index + ". Индекс должен быть больше нуля");
        }

        Vector column = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            column.setComponentByIndex(i, rows[i].getComponentByIndex(index));
        }

        return column;
    }

    // Получение вектора-строки по индексу
    public Vector getRowByIndex(int index) {
        if (index >= getRowsCount()) {
            throw new IllegalArgumentException("Переданный индекс превышает размерность матрицы по строкам : " + index + " >= " + getRowsCount());
        }

        if (index < 0) {
            throw new IllegalArgumentException("Переданный индекс = : " + index + ". Индекс должен быть больше нуля");
        }

        return new Vector(rows[index]);
    }

    // Задание вектора-строки по индексу
    public void setRowByIndex(int index, Vector vector) {
        // Проверка индекса
        if (index >= getRowsCount()) {
            throw new IllegalArgumentException("Переданный индекс превышает размерность матрицы по строкам : " + index + " >= " + getRowsCount());
        }

        if (index < 0) {
            throw new IllegalArgumentException("Переданный индекс = : " + index + ". Индекс должен быть больше нуля");
        }

        // Проверка размерности вектора
        if (vector.getSize() != rows[index].getSize()) {
            throw new IllegalArgumentException("Размер вектора должен быть равен числу столбцов матрицы: " + vector.getSize() + " не равен " + rows[index].getSize());
        }

        rows[index] = new Vector(vector);
    }

    // Умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество компонентов вектора должно быть равно количеству столбцов матрицы: " + vector.getSize() + " не равно " + getColumnsCount());
        }

        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setComponentByIndex(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return result;
    }

    // Транспонирование матрицы
    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            newRows[i] = getColumnByIndex(i);
        }

        rows = newRows;
    }

    // Вычисление определителя матрицы
    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Определитель может быть рассчитан только для квадратной матрицы. Размеры текущей матрицы: " + getRowsCount() + " x " + getColumnsCount());
        }

        if (getRowsCount() == 1) {
            return rows[0].getComponentByIndex(0);
        }

        if (getRowsCount() == 2) {
            return rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(1) -
                    rows[1].getComponentByIndex(0) * rows[0].getComponentByIndex(1);
        }

        double determinant = 0;

        if (getRowsCount() > 2) {
            for (int i = 0; i < getColumnsCount(); i++) {
                Matrix submatrix = this.getSubmatrix(i);
                determinant += Math.pow(-1.0, i + 2) * rows[0].getComponentByIndex(i) * submatrix.getDeterminant();
            }
        }

        return determinant;
    }

    public Matrix getSubmatrix(int columnIndex) {
        int rowIndex = 0;

        double[][] submatrix = new double[getRowsCount() - 1][getColumnsCount() - 1];
        int mainMatrixRowsIndex = 0;
        int mainMatrixColumnIndex = 0;

        for (int i = 0; i < submatrix.length; i++) {
            if (i == rowIndex) {
                mainMatrixRowsIndex++;
            }

            for (int j = 0; j < submatrix.length; j++) {
                if (j == columnIndex) {
                    mainMatrixColumnIndex++;
                }

                submatrix[i][j] = rows[mainMatrixRowsIndex].getComponentByIndex(mainMatrixColumnIndex);
                mainMatrixColumnIndex++;
            }

            mainMatrixColumnIndex = 0;
            mainMatrixRowsIndex++;
        }

        return new Matrix(submatrix);

    }

    // Статические методы - сложение матриц
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("При сложении размерности матриц должны быть одинаковыми " +
                    matrix1.getRowsCount() + " x " + matrix1.getColumnsCount() + " не равно " + matrix2.getRowsCount() + " x " + matrix2.getColumnsCount());
        }

        Matrix matrixResult = new Matrix(matrix1);

        matrixResult.getSum(matrix2);

        return matrixResult;
    }

    // Статические методы - вычитание матриц
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("При вычитании размерности матриц должны быть одинаковыми " +
                    matrix1.getRowsCount() + " x " + matrix1.getColumnsCount() + " не равно " + matrix2.getRowsCount() + " x " + matrix2.getColumnsCount());
        }

        Matrix matrixResult = new Matrix(matrix1);

        matrixResult.getDifference(matrix2);

        return matrixResult;
    }

    // Статические методы - умножение матриц
    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("При перемножении матриц число столбцов первой матрицы должно быть равно числу строк второй: " +
                    matrix1.getColumnsCount() + " не равно " + matrix2.getRowsCount());
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

package ru.academits.balakina.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private double[] components;

    // a.	Vector(n) – размерность n, все компоненты равны 0
    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Передана некорректная размерность " + dimension + ". Размерность вектора должна быть больше нуля");
        }

        components = new double[dimension];
    }

    // b.	Vector(Vector) – конструктор копирования
    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    // c.	Vector(double[]) – заполнение вектора значениями из массива
    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Передан массив длинной 0. Размерность вектора должна быть больше нуля");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    // d.	Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(int dimension, double[] components) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Передана некорректная размерность " + dimension + ". Размерность вектора должна быть больше нуля");
        }

        this.components = Arrays.copyOf(components, dimension);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{ ");

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(" }");

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vectorResult = new Vector(vector1);

        vectorResult.add(vector2);

        return vectorResult;
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vectorResult = new Vector(vector1);

        vectorResult.subtract(vector2);

        return vectorResult;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        int minDimension = Math.min(vector1.components.length, vector2.components.length);
        double result = 0;

        for (int i = 0; i < minDimension; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double result = 0;

        for (double component : components) {
            result += Math.pow(component, 2.0);
        }

        return Math.sqrt(result);
    }

    public double getComponentByIndex(int index) {
        return components[index];
    }

    public void setComponentByIndex(int index, double value) {
        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }
}

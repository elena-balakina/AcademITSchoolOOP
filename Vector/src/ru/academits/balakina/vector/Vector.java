package ru.academits.balakina.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    // a.	Vector(n) – размерность n, все компоненты равны 0
    public Vector(int dimension) {
        this.components = new double[dimension];
    }

    // b.	Vector(Vector) – конструктор копирования
    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.getSize());
    }

    // c.	Vector(double[]) – заполнение вектора значениями из массива
    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    // d.	Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(int dimension, double[] components) {
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
        int maxDimension = Math.max(this.getSize(), vector.getSize());

        // TODO: заполнить нулями остаток меньшего вектора

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] += vector.components[i];
        }
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorCopy = new Vector(vector1);

        vectorCopy.add(vector2);

        return vectorCopy;

        /*int maxDimension = Math.max(vector1.getSize(), vector2.getSize());
        Vector result = new Vector(maxDimension);


        if (vector1.getSize() > vector2.getSize()) {
            Vector vectorCopy = new Vector(Arrays.copyOf(vector2.components, maxDimension));
            result.add(vector1);
            result.add(vectorCopy);
        } else if (vector1.getSize() < vector2.getSize()) {
            Vector vectorCopy = new Vector(Arrays.copyOf(vector1.components, maxDimension));
            result.add(vectorCopy);
            result.add(vector2);
        }

        result.add(vector1);
        result.add(vector2);

        return result;*/
    }

    public void subtract(Vector vector) {
        int maxDimension = Math.max(this.getSize(), vector.getSize());

        // TODO: заполнить нулями остаток меньшего вектора

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] -= vector.components[i];
        }
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorCopy = new Vector(vector1);

        vectorCopy.subtract(vector2);

        return vectorCopy;
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < this.getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        int minDimension = Math.min(vector1.getSize(), vector2.getSize());
        double result = 0;

        for (int i = 0; i < minDimension; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }

    public void reverseVector() {
        this.multiplyByScalar(-1);
    }

    public double vectorLength() {
        double result = 0;

        for (int i = 0; i < this.getSize(); i++) {
            result += Math.pow(components[i], 2.0);
        }

        return Math.sqrt(result);
    }

    public double getComponent(int index) {
        return this.components[index];
    }

    public void setComponent(double value, int index) {
        this.components[index] = value;
    }
}

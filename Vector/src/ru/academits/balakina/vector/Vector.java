package ru.academits.balakina.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    // a.	Vector(n) – размерность n, все компоненты равны 0
    public Vector(int dimension) {
        this.components = new double[dimension];
    }

    // b.	Vector(Vector) – конструктор копирования

    // c.	Vector(double[]) – заполнение вектора значениями из массива
    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    // d.	Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{ ");

        for (double component : components) {
            stringBuilder.append(component + ", ");
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

    public void subtract(Vector vector) {
        int maxDimension = Math.max(this.getSize(), vector.getSize());

        // TODO: заполнить нулями остаток меньшего вектора

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < this.getSize(); i++) {
            components[i] *= scalar;
        }
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

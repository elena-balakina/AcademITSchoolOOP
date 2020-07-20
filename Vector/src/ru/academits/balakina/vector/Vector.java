package ru.academits.balakina.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private double[] components;

    // a.	Vector(n) – размерность n, все компоненты равны 0
    public Vector(int dimension) {
        if(dimension <= 0){
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        }

        this.components = new double[dimension];
    }

    // b.	Vector(Vector) – конструктор копирования
    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.getSize());
    }

    // c.	Vector(double[]) – заполнение вектора значениями из массива
    public Vector(double[] components) {
        if(components.length == 0){
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    // d.	Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
    public Vector(int dimension, double[] components) {
        if(dimension <= 0){
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
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
        if (vector.components.length > components.length){
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] += vector.components[i];
        }
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorResult = new Vector(vector1);

        vectorResult.add(vector2);

        return vectorResult;
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length){
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] -= vector.components[i];
        }
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorResult = new Vector(vector1);

        vectorResult.subtract(vector2);

        return vectorResult;
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

    public double getComponentByIndex(int index) {
        return this.components[index];
    }

    public void setComponentByIndex(double value, int index) {
        this.components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Arrays.equals(components, vector.components) && (this.components.length == vector.components.length);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(components, components.length);

        result = 37 * result + Arrays.hashCode(components);

        return result;
    }
}

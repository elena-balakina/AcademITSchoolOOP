package ru.academits.balakina.shapes_main;

import ru.academits.balakina.shapes.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(5);

        System.out.printf("Ширина квадрата 1 = %.2f%n", square1.getWidth());
        System.out.printf("Высота квадрата 1 = %.2f%n", square1.getHeight());
        System.out.printf("Площадь квадрата 1 = %.2f%n", square1.getArea());
        System.out.printf("Периметр квадрата 1 = %.2f%n", square1.getPerimeter());
        System.out.println();

        Square square2 = new Square(15);

        System.out.printf("Ширина квадрата 2 = %.2f%n", square2.getWidth());
        System.out.printf("Высота квадрата 2 = %.2f%n", square2.getHeight());
        System.out.printf("Площадь квадрата 2 = %.2f%n", square2.getArea());
        System.out.printf("Периметр квадрата 2 = %.2f%n", square2.getPerimeter());
        System.out.println();

        Triangle triangle = new Triangle(-1, -3, 3, 4, 5, -5);

        System.out.printf("Ширина треугольника = %.2f%n", triangle.getWidth());
        System.out.printf("Высота треугольника = %.2f%n", triangle.getHeight());
        System.out.printf("Площадь треугольника = %.2f%n", triangle.getArea());
        System.out.printf("Периметр треугольника = %.2f%n", triangle.getPerimeter());
        System.out.println();

        Rectangle rectangle = new Rectangle(10, 5);

        System.out.printf("Ширина прямоугольника = %.2f%n", rectangle.getWidth());
        System.out.printf("Высота прямоугольника = %.2f%n", rectangle.getHeight());
        System.out.printf("Площадь прямоугольника = %.2f%n", rectangle.getArea());
        System.out.printf("Периметр прямоугольника = %.2f%n", rectangle.getPerimeter());
        System.out.println();

        Circle circle = new Circle(5);

        System.out.printf("Ширина круга = %.2f%n", circle.getWidth());
        System.out.printf("Высота круга = %.2f%n", circle.getHeight());
        System.out.printf("Площадь круга = %.2f%n", circle.getArea());
        System.out.printf("Периметр круга = %.2f%n", circle.getPerimeter());
        System.out.println();

        Shape[] shapes = new Shape[]{
                new Square(5),
                new Square(15),
                new Triangle(-1, -3, 3, 4, 5, -5),
                new Rectangle(10, 5),
                new Circle(5)};

        Shape shapeWithMaxArea = getMaxArea(shapes);

        System.out.println("Фигура с максимальной площадью:");
        System.out.printf("Ширина фигуры = %.2f%n", shapeWithMaxArea.getWidth());
        System.out.printf("Высота фигуры = %.2f%n", shapeWithMaxArea.getHeight());
        System.out.printf("Площадь фигуры = %.2f%n", shapeWithMaxArea.getArea());
        System.out.printf("Периметр фигуры = %.2f%n", shapeWithMaxArea.getPerimeter());
        System.out.println();

        Shape shapeWithSecondMaxPerimeter = getSecondMaxPerimeter(shapes);

        System.out.println("Фигура со вторым по величине периметром:");
        System.out.printf("Ширина фигуры = %.2f%n", shapeWithSecondMaxPerimeter.getWidth());
        System.out.printf("Высота фигуры = %.2f%n", shapeWithSecondMaxPerimeter.getHeight());
        System.out.printf("Площадь фигуры = %.2f%n", shapeWithSecondMaxPerimeter.getArea());
        System.out.printf("Периметр фигуры = %.2f%n", shapeWithSecondMaxPerimeter.getPerimeter());
        System.out.println();
    }

    public static Shape getMaxArea(Shape[] shapes) {
        Comparator<Shape> areaComparator = new AreaComparator();
        Arrays.sort(shapes, areaComparator);

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondMaxPerimeter(Shape[] shapes) {
        Comparator<Shape> perimeterComparator = new PerimeterComparator();
        Arrays.sort(shapes, perimeterComparator);

        return shapes[shapes.length - 2];
    }
}

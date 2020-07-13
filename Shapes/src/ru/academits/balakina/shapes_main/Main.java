package ru.academits.balakina.shapes_main;

import ru.academits.balakina.shapes.Circle;
import ru.academits.balakina.shapes.Rectangle;
import ru.academits.balakina.shapes.Square;
import ru.academits.balakina.shapes.Triangle;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(5);

        System.out.printf("Ширина квадрата = %.2f%n", square.getWidth());
        System.out.printf("Высота квадрата = %.2f%n", square.getHeight());
        System.out.printf("Площадь квадрата = %.2f%n", square.getArea());
        System.out.printf("Периметр квадрата = %.2f%n", square.getPerimeter());
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
    }
}

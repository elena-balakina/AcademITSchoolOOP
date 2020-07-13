package ru.academits.balakina.shapes_main;

import ru.academits.balakina.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return (int) (s1.getPerimeter() - s2.getPerimeter());
    }
}

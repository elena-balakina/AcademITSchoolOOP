package ru.academits.balakina.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersectionWith(Range range) {
        if (this.to <= range.getFrom() || range.getTo() <= this.getFrom()) {
            return null;
        }

        return new Range(Math.max(this.from, range.getFrom()), Math.min(this.to, range.getTo()));
    }

    public Range[] getUnionWith(Range range) {
        double from1 = from;
        double to1 = to;
        double from2 = range.from;
        double to2 = range.to;
        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        if (to1 == from2) {
            return new Range[]{new Range(from1, to2)};
        }

        if (range1.getIntersectionWith(range2) == null) {
            return new Range[]{range1, range2};
        }

        return new Range[]{new Range(Math.min(from1, from2), Math.max(to1, to2))};
    }

    public Range[] getDifferenceFrom(Range range) {

        double from1 = range.from;
        double to1 = range.to;
        double from2 = from;
        double to2 = to;
        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        if (range1.getIntersectionWith(range2) == null) {
            return new Range[]{range1};
        }

        if (from2 > from1 && to2 > to1) {
            return new Range[]{new Range(from1, from2)};
        }

        if (from2 < from1 && to2 < to1) {
            return new Range[]{new Range(to2, to1)};
        }

        if (from2 > from1 && to2 < to1) {
            return new Range[]{new Range(from1, from2), new Range(to2, to1)};
        }

        return new Range[]{};
    }

    public static void printArrayOfRange(Range[] range) {
        for (Range r : range) {
            System.out.printf("[%.2f; %.2f]%n", r.from, r.to);
        }
    }
}

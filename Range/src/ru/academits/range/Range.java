package ru.academits.range;

class Range {
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
        } else {
            return new Range(Math.max(this.from, range.getFrom()), Math.min(this.to, range.getTo()));
        }
    }

    public Range[] getUnificationWith(Range range) {
        if (this.getFrom() <= range.getFrom() && this.getTo() >= range.getFrom() && this.getTo() <= range.getTo()) {
            Range resultRange = new Range(this.getFrom(), range.getTo());
            return new Range[]{resultRange};
        }

        return new Range[]{this, range};
    }

    public Range[] getSubtractionFrom(Range range) {
        if (range.getFrom() == this.getFrom() && range.getTo() == this.getTo()) {
            return null;
        } else if (this.getFrom() >= range.getFrom() && this.getTo() <= range.getTo()) {
            Range resultRange1 = new Range(range.getFrom(), this.getFrom());
            Range resultRange2 = new Range(this.getTo(), range.getTo());
            return new Range[]{resultRange1, resultRange2};
        } else if (range.getTo() < this.getFrom() || range.getFrom() > this.getTo()) {
            return new Range[]{range};
        }

        Range resultRange = new Range(range.getFrom(), this.getFrom());
        return new Range[]{resultRange};
    }
}

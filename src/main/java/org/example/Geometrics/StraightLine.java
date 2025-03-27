package org.example.Geometrics;

public class StraightLine {
    private double a, b, c;

    public StraightLine() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public StraightLine(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public StraightLine(Segment s) {
        this.a = s.getY2() - s.getY1();
        this.b = s.getX1() - s.getX2();
        this.c = s.getY1()*s.getX2() - s.getX1()*s.getY2();
    }

    public double getA() { return a; }

    public double getB() { return b; }

    public double getC() { return c; }
}

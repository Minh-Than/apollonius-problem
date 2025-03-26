package org.example;

import java.awt.geom.Line2D;

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

    public StraightLine(Line2D.Double l) {
        this.a = l.y2 - l.y1;
        this.b = l.x1 - l.x2;
        this.c = l.y1*l.x2 - l.x1*l.y2;
    }

    public double getA() { return a; }

    public double getB() { return b; }

    public double getC() { return c; }

    public static StraightLine getAsStraightLine(Line2D.Double l) {
        return new StraightLine(l);
    }
}

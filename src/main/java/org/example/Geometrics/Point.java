package org.example.Geometrics;

public class Point {
    private double x, y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public double distance(Point p) {
        double x = p.getX() - this.x;
        double y = p.getY() - this.y;
        return Math.sqrt(x * x + y * y);
    }

    public Point clone() {
        return new Point(x, y);
    }
}

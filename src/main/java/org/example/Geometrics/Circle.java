package org.example.Geometrics;

public class Circle {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center.clone();
        this.radius = radius;
    }

    public Point getCenter() { return center; }

    public double getX() { return center.getX(); }

    public double getY() { return center.getY(); }

    public double getR() { return radius; }

    public void setR(double radius) { this.radius = radius; }

    public void setCenter(Point p) { center = p.clone(); }
}

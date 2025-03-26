package org.example;

import java.awt.geom.Point2D;

public class Circle {
    private Point2D.Double center;
    private double radius;

    public Circle(Point2D.Double center, double radius) {
        this.center = (Point2D.Double) center.clone();
        this.radius = radius;
    }

    public Point2D.Double getCenter() { return center; }

    public double getX() { return center.getX(); }

    public double getY() { return center.getY(); }

    public double getR() { return radius; }

    public void setR(double radius) { this.radius = radius; }

    public void setCenter(Point2D.Double p) {
        center = (Point2D.Double) p.clone();
    }
}

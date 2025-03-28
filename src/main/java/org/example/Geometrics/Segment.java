package org.example.Geometrics;

public class Segment {
    private Point p1, p2;

    public Segment(Point p1, Point p2) {
        this.p1 = p1.clone();
        this.p2 = p2.clone();
    }

    public Segment(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    public Point getP1() { return p1; }

    public Point getP2() { return p2; }

    public double getX1() { return p1.getX(); }

    public double getY1() { return p1.getY(); }

    public double getX2() { return p2.getX(); }

    public double getY2() { return p2.getY(); }

    public StraightLine asStraightLine() {
        return new StraightLine(new Segment(p1, p2));
    }
}

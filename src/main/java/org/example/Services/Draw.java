package org.example.Services;

import org.example.Geometrics.Circle;
import org.example.Geometrics.Point;
import org.example.Geometrics.Segment;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Draw {
    public static void drawPoint(Graphics2D g2, Point p, double size) {
        if (p == null) return;
        g2.fill(new Ellipse2D.Double(p.getX() - size/2, p.getY() - size/2, size, size));
    }

    public static void drawLine(Graphics2D g2, Segment s) {
        if (s == null) return;
        g2.draw(new Line2D.Double(s.getX1(), s.getY1(), s.getX2(), s.getY2()));
    }

    public static void drawCircle(Graphics2D g2, Circle c) {
        if (c == null) return;
        g2.draw(new Ellipse2D.Double(c.getX() - c.getR(),
                c.getY() - c.getR(),
                c.getR() * 2,
                c.getR() * 2));
    }

    public static void drawCircleFill(Graphics2D g2, Circle c) {
        if (c == null) return;
        g2.fill(new Ellipse2D.Double(c.getX() - c.getR(),
                c.getY() - c.getR(),
                c.getR() * 2,
                c.getR() * 2));
    }
}

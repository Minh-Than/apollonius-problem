package org.example;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DrawService {
    public static void drawPoint(Graphics2D g2, Point2D.Double p, double size) {
        if (p == null) return;
        g2.fill(new Ellipse2D.Double(p.x - size/2, p.y - size/2, size, size));
    }

    public static void drawLine(Graphics2D g2, Line2D.Double s) {
        if (s == null) return;
        g2.draw(s);
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

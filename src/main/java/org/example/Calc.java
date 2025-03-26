package org.example;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Calc {
    public static Point2D.Double getExternalHomotheticCenter(Circle c1, Circle c2) {
        double x = (c2.getR() * c1.getX() - c1.getR() * c2.getX())
                / (c2.getR() - c1.getR());
        double y = (c2.getR() * c1.getY() - c1.getR() * c2.getY())
                / (c2.getR() - c1.getR());
        return new Point2D.Double(x, y);
    }

    public static Point2D.Double getInternalHomotheticCenter(Circle c1, Circle c2) {
        double x = (c2.getR() * c1.getX() + c1.getR() * c2.getX())
                / (c2.getR() + c1.getR());
        double y = (c2.getR() * c1.getY() + c1.getR() * c2.getY())
                / (c2.getR() + c1.getR());
        return new Point2D.Double(x, y);
    }

    public static Point2D.Double getInversePole(Line2D.Double l, Circle c) {
        Line2D.Double polarProjectionLine = new Line2D.Double(
                c.getCenter(),
                Calc.findProjection(StraightLine.getAsStraightLine(l), c.getCenter()));

        Point2D.Double projectionMidPoint = Calc.midPoint(polarProjectionLine);
        Circle compassCircle = new Circle(
                projectionMidPoint,
                Calc.distance(c.getCenter(), projectionMidPoint));

        Line2D.Double intersection = Calc.getCirclesIntersection(compassCircle, c);
        return findIntersection(
                StraightLine.getAsStraightLine(polarProjectionLine),
                StraightLine.getAsStraightLine(intersection));
    }

    public static StraightLine getRadicalAxis(Circle c1, Circle c2) {
        double a = 2 * (c2.getX() - c1.getX());
        double b = 2 * (c2.getY() - c1.getY());
        double c = (c1.getX() * c1.getX() + c1.getY() * c1.getY() - c1.getR() * c1.getR())
                - (c2.getX() * c2.getX() + c2.getY() * c2.getY() - c2.getR() * c2.getR());
        return new StraightLine(a, b, c);
    }

    public static Circle getCircle3Points(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        Line2D.Double sen1 = new Line2D.Double(p1, p2);
        Line2D.Double sen2 = new Line2D.Double(p2, p3);
        Line2D.Double sen3 = new Line2D.Double(p3, p1);

        if (checkIfFlatAngle(sen1, sen2)) { return null; }
        if (checkIfFlatAngle(sen2, sen3)) { return null; }
        if (checkIfFlatAngle(sen3, sen1)) { return null; }

        StraightLine t1 = orthogonalize(
                            StraightLine.getAsStraightLine(sen1),
                            internalDivisionRatio(
                                new Point2D.Double(sen1.x1, sen1.y1),
                                new Point2D.Double(sen1.x2, sen1.y2),
                            1.0, 1.0));
        StraightLine t2 = orthogonalize(
                            StraightLine.getAsStraightLine(sen2),
                            internalDivisionRatio(
                                new Point2D.Double(sen2.x1, sen2.y1),
                                new Point2D.Double(sen2.x2, sen2.y2),
                            1.0, 1.0));
        Point2D.Double intersectionPoint = findIntersection(t1, t2);
        return new Circle(intersectionPoint, distance(p1, intersectionPoint));
    }

    public static Circle getCircle3Points(Point2D p1, Point2D p2, Point2D p3) {
        return getCircle3Points((Point2D.Double) p1, (Point2D.Double) p2, (Point2D.Double) p3);
    }

    private static boolean checkIfFlatAngle(Line2D.Double s1, Line2D.Double s2) {
        if (Math.abs(angle(s1, s2) - 0.0) < 1E-6) return true;
        if (Math.abs(angle(s1, s2) - 180.0) < 1E-6) return true;
        if (Math.abs(angle(s1, s2) - 360.0) < 1E-6) return true;
        return false;
    }

    public static double angle(Line2D.Double s1, Line2D.Double s2) {
        Point2D.Double a = new Point2D.Double(s1.x1, s1.y1);
        Point2D.Double b = new Point2D.Double(s1.x2, s1.y2);
        Point2D.Double c = new Point2D.Double(s2.x1, s2.y1);
        Point2D.Double d = new Point2D.Double(s2.x2, s2.y2);

        return angle_between_0_360(angle(c, d) - angle(a, b));
    }

    public static double angle(Point2D.Double a, Point2D.Double b) {
        double ax, ay, bx, by, x, y, L, c, ret;
        ax = a.getX();
        ay = a.getY();
        bx = b.getX();
        by = b.getY();
        x = bx - ax;
        y = by - ay;
        L = Math.sqrt(x * x + y * y);
        if (L <= 0.0) {
            return -10000.0;
        }
        c = x / L;
        if (c > 1.0) {
            c = 1.0;
        }

        ret = Math.acos(c);
        if (y < 0.0) {
            ret = -ret;
        }
        ret = 180.0 * ret / Math.PI;
        if (ret < 0) {
            ret = ret + 360.0;
        }
        return ret;
    }

    public static double angle_between_0_360(double angle) {
        while (angle < 0.0) { angle = angle + 360.0; }
        while (angle >= 360.0) { angle = angle - 360.0; }
        return angle;
    }

    public static Point2D.Double internalDivisionRatio(Point2D.Double a, Point2D.Double b, double d_internalDivisionRatio_s, double d_internalDivisionRatio_t) {
        Point2D.Double r_point = new Point2D.Double(-10000.0, -10000.0);
        if (distance(a, b) < 1E-6)  return r_point;

        if (d_internalDivisionRatio_s == 0.0) {
            if (d_internalDivisionRatio_t == 0.0) return r_point;
            return a;
        } else {
            if (d_internalDivisionRatio_t == 0.0) return b;
            Line2D.Double s_ab = new Line2D.Double(a, b);
            double nx = (d_internalDivisionRatio_t * s_ab.getX1() + d_internalDivisionRatio_s * s_ab.getX2()) / (d_internalDivisionRatio_s + d_internalDivisionRatio_t);
            double ny = (d_internalDivisionRatio_t * s_ab.getY1() + d_internalDivisionRatio_s * s_ab.getY2()) / (d_internalDivisionRatio_s + d_internalDivisionRatio_t);
            return new Point2D.Double(nx, ny);

        }
    }

    public static StraightLine getCirclesIntersectionSL(Circle c1, Circle c2) {
        double x1 = c1.getX();
        double y1 = c1.getY();
        double r1 = c1.getR();
        double x2 = c2.getX();
        double y2 = c2.getY();
        double r2 = c2.getR();

        double a = 2.0 * x1 - 2.0 * x2;
        double b = 2.0 * y1 - 2.0 * y2;
        double c = x2 * x2 - x1 * x1 + y2 * y2 - y1 * y1 + r1 * r1 - r2 * r2;

        return new StraightLine(a, b, c);
    }

    public static Line2D.Double getCirclesIntersection(Circle c1, Circle c2) {
        StraightLine t0 = getCirclesIntersectionSL(c1, c2);
        StraightLine t1 = new StraightLine(
                c2.getY() - c1.getY(), c1.getX() - c2.getX(),
                c1.getY()*c2.getX() - c1.getX()*c2.getY());
        Point2D.Double intersection_t0t1 = findIntersection(t0, t1);

        double length_a = calculateDistance(t0, c1.getCenter());
        double length_b = Math.sqrt(c1.getR() * c1.getR() - length_a * length_a);

        return new Line2D.Double(
                intersection_t0t1.getX() + t0.getB() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA()),
                intersection_t0t1.getY() - t0.getA() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA()),
                intersection_t0t1.getX() - t0.getB() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA()),
                intersection_t0t1.getY() + t0.getA() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA())
        );
    }

    public static Line2D.Double getCircleStraightlineIntersection(Circle c, StraightLine l) {
        Point2D.Double kouten_t0t1 = findProjection(l, c.getCenter());
        double length_a = calculateDistance(l, c.getCenter());
        double length_b = Math.sqrt(c.getR() * c.getR() - length_a * length_a);

        return new Line2D.Double(
                kouten_t0t1.getX() + l.getB() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA()),
                kouten_t0t1.getY() - l.getA() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA()),
                kouten_t0t1.getX() - l.getB() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA()),
                kouten_t0t1.getY() + l.getA() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA())
        );
    }

    public static Line2D.Double getCircleStraightlineIntersection(Circle c, Line2D.Double l) {
        return getCircleStraightlineIntersection(c, StraightLine.getAsStraightLine(l));
    }

    public static double calculateDistance(StraightLine s, Point2D.Double p) {
        double x = p.getX();
        double y = p.getY();
        return Math.abs((s.getA() * x + s.getB() * y + s.getC())
                / Math.sqrt(s.getA() * s.getA() + s.getB() * s.getB()));
    }

    public static double distance(Point2D.Double p1, Point2D.Double p2) {
        double x1 = p2.getX() - p1.getX(), y1 = p2.getY() - p1.getY();
        return Math.sqrt(x1 * x1 + y1 * y1);
    }

    public static Point2D.Double findProjection(StraightLine l, Point2D.Double p) {
        StraightLine t1 = orthogonalize(new StraightLine(l.getA(), l.getB(), l.getC()), p);
        return findIntersection(l, t1);
    }

    public static Point2D.Double findIntersection(StraightLine l1, StraightLine l2) {
        double a1 = l1.getA(), b1 = l1.getB(), c1 = l1.getC();
        double a2 = l2.getA(), b2 = l2.getB(), c2 = l2.getC();

        return new Point2D.Double((b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1), (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1));
    }

    public static StraightLine orthogonalize(StraightLine l, Point2D.Double p) {
        double e;
        double x = p.getX();
        double y = p.getY();
        double c = -l.getB() * x + l.getA() * y;
        e = l.getA();
        double a = l.getB();
        double b = -e;
        return new StraightLine(a, b, c);
    }

    public static Point2D.Double midPoint (Line2D.Double s) {
        return new Point2D.Double((s.x2 + s.x1)/2.0, (s.y2 + s.y1)/2.0);
    }
}

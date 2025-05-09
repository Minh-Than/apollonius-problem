package org.example.Services;

import org.example.Geometrics.Circle;
import org.example.Geometrics.Point;
import org.example.Geometrics.Segment;
import org.example.Geometrics.StraightLine;

public class Calc {
    public static Point getExternalHomotheticCenter(Circle c1, Circle c2) {
        double x = (c2.getR() * c1.getX() - c1.getR() * c2.getX())
                / (c2.getR() - c1.getR());
        double y = (c2.getR() * c1.getY() - c1.getR() * c2.getY())
                / (c2.getR() - c1.getR());
        return new Point(x, y);
    }

    public static Point getInternalHomotheticCenter(Circle c1, Circle c2) {
        double x = (c2.getR() * c1.getX() + c1.getR() * c2.getX())
                / (c2.getR() + c1.getR());
        double y = (c2.getR() * c1.getY() + c1.getR() * c2.getY())
                / (c2.getR() + c1.getR());
        return new Point(x, y);
    }

    public static Point getInversePole(Segment s, Circle c) {
        if(isSegmentIntersectingCircle(c, s)) {
            // Case 1: polar segment intersecting inversion circle
            Segment intersectingSegment = getCircleStraightlineIntersection(c, s);
            double dx = intersectingSegment.getX1() - c.getX();
            double dy = intersectingSegment.getY1() - c.getY();

            double tangentX = -dy;
            double tangentY = dx;

            Point tangentP1 = new Point(intersectingSegment.getX1() - tangentX, intersectingSegment.getY1() - tangentY);
            Point tangentP2 = new Point(intersectingSegment.getX1() + tangentX, intersectingSegment.getY1() + tangentY);
            Segment tangent = new Segment(tangentP1, tangentP2);

            Segment projectionSegment = new Segment(c.getCenter(), findProjection(s.asStraightLine(), c.getCenter()));
            return findIntersection(tangent.asStraightLine(), projectionSegment.asStraightLine());
        } else {
            // Case 2: polar segment outside of inversion circle
            Segment polarProjectionLine = new Segment(
                    c.getCenter(),
                    Calc.findProjection(s.asStraightLine(), c.getCenter()));

            Point projectionMidPoint = Calc.midPoint(polarProjectionLine);
            Circle compassCircle = new Circle(
                    projectionMidPoint,
                    projectionMidPoint.distance(c.getCenter()));

            Segment intersection = Calc.getCirclesIntersection(compassCircle, c);
            return findIntersection(
                    polarProjectionLine.asStraightLine(),
                    intersection.asStraightLine());
        }
    }

    public static StraightLine getRadicalAxis(Circle c1, Circle c2) {
        double a = 2 * (c2.getX() - c1.getX());
        double b = 2 * (c2.getY() - c1.getY());
        double c = (c1.getX() * c1.getX() + c1.getY() * c1.getY() - c1.getR() * c1.getR())
                - (c2.getX() * c2.getX() + c2.getY() * c2.getY() - c2.getR() * c2.getR());
        return new StraightLine(a, b, c);
    }

    public static Circle getCircle3Points(Point p1, Point p2, Point p3) {
        Segment sen1 = new Segment(p1, p2);
        Segment sen2 = new Segment(p2, p3);
        Segment sen3 = new Segment(p3, p1);

        if (checkIfFlatAngle(sen1, sen2)) { return null; }
        if (checkIfFlatAngle(sen2, sen3)) { return null; }
        if (checkIfFlatAngle(sen3, sen1)) { return null; }

        StraightLine t1 = orthogonalize(
                            sen1.asStraightLine(),
                            internalDivisionRatio(
                                new Point(sen1.getX1(), sen1.getY1()),
                                new Point(sen1.getX2(), sen1.getY2()),
                            1.0, 1.0));
        StraightLine t2 = orthogonalize(
                            sen2.asStraightLine(),
                            internalDivisionRatio(
                                new Point(sen2.getX1(), sen2.getY1()),
                                new Point(sen2.getX2(), sen2.getY2()),
                            1.0, 1.0));
        Point intersectionPoint = findIntersection(t1, t2);
        return new Circle(intersectionPoint, p1.distance(intersectionPoint));
    }

    private static boolean checkIfFlatAngle(Segment s1, Segment s2) {
        return (Math.abs(angle(s1, s2) - 0.0) < 1E-6)
                || (Math.abs(angle(s1, s2) - 180.0) < 1E-6)
                || (Math.abs(angle(s1, s2) - 360.0) < 1E-6);
    }

    public static double angle(Segment s1, Segment s2) {
        Point a = new Point(s1.getX1(), s1.getY1());
        Point b = new Point(s1.getX2(), s1.getY2());
        Point c = new Point(s2.getX1(), s2.getY1());
        Point d = new Point(s2.getX2(), s2.getY2());

        return angle_between_0_360(angle(c, d) - angle(a, b));
    }

    public static double angle(Point a, Point b) {
        double ax, ay, bx, by, x, y, L, c, ret;
        ax = a.getX();
        ay = a.getY();
        bx = b.getX();
        by = b.getY();
        x = bx - ax;
        y = by - ay;

        L = Math.sqrt(x * x + y * y);
        if (L <= 0.0) return -10000.0;
        c = x / L;
        if (c > 1.0) c = 1.0;

        ret = Math.acos(c);
        if (y < 0.0) ret = -ret;
        ret = 180.0 * ret / Math.PI;
        if (ret < 0) ret = ret + 360.0;

        return ret;
    }

    public static double angle_between_0_360(double angle) {
        while (angle < 0.0) { angle = angle + 360.0; }
        while (angle >= 360.0) { angle = angle - 360.0; }
        return angle;
    }

    public static Point internalDivisionRatio(Point a, Point b, double d_internalDivisionRatio_s, double d_internalDivisionRatio_t) {
        Point r_point = new Point(-10000.0, -10000.0);
        if (a.distance(b) < 1E-6)  return r_point;

        if (d_internalDivisionRatio_s == 0.0) {
            if (d_internalDivisionRatio_t == 0.0) return r_point;
            return a;
        } else {
            if (d_internalDivisionRatio_t == 0.0) return b;
            Segment s_ab = new Segment(a, b);
            double nx = (d_internalDivisionRatio_t * s_ab.getX1() + d_internalDivisionRatio_s * s_ab.getX2()) / (d_internalDivisionRatio_s + d_internalDivisionRatio_t);
            double ny = (d_internalDivisionRatio_t * s_ab.getY1() + d_internalDivisionRatio_s * s_ab.getY2()) / (d_internalDivisionRatio_s + d_internalDivisionRatio_t);
            return new Point(nx, ny);

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

    public static Segment getCirclesIntersection(Circle c1, Circle c2) {
        StraightLine t0 = getCirclesIntersectionSL(c1, c2);
        StraightLine t1 = new StraightLine(
                c2.getY() - c1.getY(), c1.getX() - c2.getX(),
                c1.getY()*c2.getX() - c1.getX()*c2.getY());
        Point intersection_t0t1 = findIntersection(t0, t1);

        double length_a = calculateDistance(t0, c1.getCenter());
        double length_b = Math.sqrt(c1.getR() * c1.getR() - length_a * length_a);

        return new Segment(
                intersection_t0t1.getX() + t0.getB() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA()),
                intersection_t0t1.getY() - t0.getA() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA()),
                intersection_t0t1.getX() - t0.getB() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA()),
                intersection_t0t1.getY() + t0.getA() * length_b / Math.sqrt(t0.getB() * t0.getB() + t0.getA() * t0.getA())
        );
    }

    public static Segment getCircleStraightlineIntersection(Circle c, StraightLine l) {
        Point kouten_t0t1 = findProjection(l, c.getCenter());
        double length_a = calculateDistance(l, c.getCenter());
        double length_b = Math.sqrt(c.getR() * c.getR() - length_a * length_a);

        return new Segment(
                kouten_t0t1.getX() + l.getB() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA()),
                kouten_t0t1.getY() - l.getA() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA()),
                kouten_t0t1.getX() - l.getB() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA()),
                kouten_t0t1.getY() + l.getA() * length_b / Math.sqrt(l.getB() * l.getB() + l.getA() * l.getA())
        );
    }

    public static Segment getCircleStraightlineIntersection(Circle c, Segment s) {
        return getCircleStraightlineIntersection(c, s.asStraightLine());
    }

    public static boolean isSegmentIntersectingCircle(Circle c, Segment s) {
        return c.getR() >= calculateDistance(s.asStraightLine(), c.getCenter());
    }

    public static double calculateDistance(StraightLine s, Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.abs((s.getA() * x + s.getB() * y + s.getC())
                / Math.sqrt(s.getA() * s.getA() + s.getB() * s.getB()));
    }

    public static Point findProjection(StraightLine l, Point p) {
        StraightLine t1 = orthogonalize(new StraightLine(l.getA(), l.getB(), l.getC()), p);
        return findIntersection(l, t1);
    }

    public static Point findIntersection(StraightLine l1, StraightLine l2) {
        double a1 = l1.getA(), b1 = l1.getB(), c1 = l1.getC();
        double a2 = l2.getA(), b2 = l2.getB(), c2 = l2.getC();

        return new Point((b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1), (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1));
    }

    public static StraightLine orthogonalize(StraightLine l, Point p) {
        double e;
        double x = p.getX();
        double y = p.getY();
        double c = -l.getB() * x + l.getA() * y;
        e = l.getA();
        double a = l.getB();
        double b = -e;
        return new StraightLine(a, b, c);
    }

    public static Point midPoint (Segment s) {
        return new Point((s.getX2() + s.getX1())/2.0, (s.getY2() + s.getY1())/2.0);
    }
}

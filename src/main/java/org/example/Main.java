package org.example;

import org.example.Geometrics.Circle;
import org.example.Geometrics.Point;
import org.example.Geometrics.Segment;
import org.example.Geometrics.StraightLine;
import org.example.Services.Calc;
import org.example.Services.Draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class Main extends JPanel {
    static Point cursor = new Point();

    static Circle circle1, circle2, circle3;

    static Point exHMCenter1, inHMCenter1, exHMCenter2, inHMCenter2, exHMCenter3, inHMCenter3;
    static Segment HMLine1, HMLine2, HMLine3, HMLine4;
    static boolean showHomothetic;

    static StraightLine radicalAxis1, radicalAxis2;
    static Point radicalCenter;
    static boolean showRadical;

    static Point inversePoleC1_1, inversePoleC2_1, inversePoleC3_1;
    static Point inversePoleC1_2, inversePoleC2_2, inversePoleC3_2;
    static Point inversePoleC1_3, inversePoleC2_3, inversePoleC3_3;
    static Point inversePoleC1_4, inversePoleC2_4, inversePoleC3_4;
    static boolean showInversePoles;

    static Segment lineToRadical1_1, lineToRadical2_1, lineToRadical3_1;
    static Segment lineToRadical1_2, lineToRadical2_2, lineToRadical3_2;
    static Segment lineToRadical1_3, lineToRadical2_3, lineToRadical3_3;
    static Segment lineToRadical1_4, lineToRadical2_4, lineToRadical3_4;
    static boolean showConnectors;

    static Circle apolloniusC1_1, apolloniusC1_2;
    static Circle apolloniusC2_1, apolloniusC2_2;
    static Circle apolloniusC3_1, apolloniusC3_2;
    static Circle apolloniusC4_1, apolloniusC4_2;
    static boolean showApolloniusCircles1;
    static boolean showApolloniusCircles2;
    static boolean showApolloniusCircles3;
    static boolean showApolloniusCircles4;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        draw3Circles(g2);
        drawHomotheticCenters(g2);
//        drawHomotheticLines(g2);
        drawRadicalCenter(g2);
        drawInversePoles(g2);
        drawConnectors(g2);
        drawApolloniusCircles(g2);
    }

    public void draw3Circles(Graphics2D g2) {
        g2.setColor(new Color(0.0f,0.0f,0.0f,0.1f));
        Draw.drawCircleFill(g2, circle1);
        Draw.drawCircleFill(g2, circle2);
        Draw.drawCircleFill(g2, circle3);
    }

    public void drawHomotheticCenters(Graphics2D g2) {
        if (!showHomothetic) return;
        g2.setColor(Color.LIGHT_GRAY);
        Draw.drawPoint(g2, exHMCenter1, 6);
        Draw.drawPoint(g2, inHMCenter1, 6);

        Draw.drawPoint(g2, exHMCenter2, 6);
        Draw.drawPoint(g2, inHMCenter2, 6);

        Draw.drawPoint(g2, exHMCenter3, 6);
        Draw.drawPoint(g2, inHMCenter3, 6);
    }

    public void drawHomotheticLines(Graphics2D g2) {
        if (!showHomothetic) return;
        g2.setColor(Color.LIGHT_GRAY);
        Draw.drawLine(g2, HMLine1);
        Draw.drawLine(g2, HMLine2);
        Draw.drawLine(g2, HMLine3);
        Draw.drawLine(g2, HMLine4);
    }

    public void drawRadicalCenter(Graphics2D g2) {
        if (!showRadical) return;
        g2.setColor(Color.BLACK);
        Draw.drawPoint(g2, radicalCenter, 10);
    }

    public void drawInversePoles(Graphics2D g2) {
        if (!showInversePoles) return;
        g2.setColor(new Color(195, 146, 9));
        Draw.drawPoint(g2, inversePoleC1_1, 4);
        Draw.drawPoint(g2, inversePoleC2_1, 4);
        Draw.drawPoint(g2, inversePoleC3_1, 4);

        g2.setColor(new Color(189, 15, 216));
        Draw.drawPoint(g2, inversePoleC1_2, 4);
        Draw.drawPoint(g2, inversePoleC2_2, 4);
        Draw.drawPoint(g2, inversePoleC3_2, 4);

        g2.setColor(new Color(61, 168, 38));
        Draw.drawPoint(g2, inversePoleC1_3, 4);
        Draw.drawPoint(g2, inversePoleC2_3, 4);
        Draw.drawPoint(g2, inversePoleC3_3, 4);

        g2.setColor(new Color(6, 126, 202));
        Draw.drawPoint(g2, inversePoleC1_4, 4);
        Draw.drawPoint(g2, inversePoleC2_4, 4);
        Draw.drawPoint(g2, inversePoleC3_4, 4);
    }

    public void drawConnectors(Graphics2D g2) {
        if (!showConnectors) return;
        g2.setColor(new Color(195, 146, 9));
        Draw.drawLine(g2, lineToRadical1_1);
        Draw.drawLine(g2, lineToRadical2_1);
        Draw.drawLine(g2, lineToRadical3_1);

        g2.setColor(new Color(189, 15, 216));
        Draw.drawLine(g2, lineToRadical1_2);
        Draw.drawLine(g2, lineToRadical2_2);
        Draw.drawLine(g2, lineToRadical3_2);

        g2.setColor(new Color(61, 168, 38));
        Draw.drawLine(g2, lineToRadical1_3);
        Draw.drawLine(g2, lineToRadical2_3);
        Draw.drawLine(g2, lineToRadical3_3);

        g2.setColor(new Color(6, 126, 202));
        Draw.drawLine(g2, lineToRadical1_4);
        Draw.drawLine(g2, lineToRadical2_4);
        Draw.drawLine(g2, lineToRadical3_4);
    }

    public void drawApolloniusCircles(Graphics2D g2) {
        if(showApolloniusCircles1) {
            g2.setColor(new Color(195, 146, 9));
            Draw.drawCircle(g2, apolloniusC1_1);
            Draw.drawCircle(g2, apolloniusC1_2);
        }

        if(showApolloniusCircles2) {
            g2.setColor(new Color(189, 15, 216));
            Draw.drawCircle(g2, apolloniusC2_1);
            Draw.drawCircle(g2, apolloniusC2_2);
        }

        if(showApolloniusCircles3) {
            g2.setColor(new Color(61, 168, 38));
            Draw.drawCircle(g2, apolloniusC3_1);
            Draw.drawCircle(g2, apolloniusC3_2);
        }

        if(showApolloniusCircles4) {
            g2.setColor(new Color(6, 126, 202));
            Draw.drawCircle(g2, apolloniusC4_1);
            Draw.drawCircle(g2, apolloniusC4_2);
        }
    }

    public static void main(String[] args) {
        reset();
        setValues();

        JFrame frame = new JFrame("Apollonius' Problem");
        frame.setSize(900, 800);
        frame.setResizable(false);

        JPanel mainPanel = new Main();
        setListeners(mainPanel);
        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        setupButtons(mainPanel, topPanel);
        frame.add(topPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public static void setListeners(JPanel mainPanel) {
        mainPanel.addMouseWheelListener(e -> {
            if (e.getWheelRotation() == 0) return;
            int unitWheelRotation = e.getWheelRotation() / Math.abs(e.getWheelRotation());
            double scale = Math.abs(0.1 - unitWheelRotation);

            double distanceC1 = cursor.distance(circle1.getCenter());
            double distanceC2 = cursor.distance(circle2.getCenter());
            double distanceC3 = cursor.distance(circle3.getCenter());

            if (distanceC1 < distanceC2 && distanceC1 < distanceC3 && distanceC1 < circle1.getR()) {
                circle1.setR(scale * circle1.getR());
                setValues();
                mainPanel.repaint();
                return;
            }
            if (distanceC2 < distanceC1 && distanceC2 < distanceC3 && distanceC2 < circle2.getR()) {
                circle2.setR(scale * circle2.getR());
                setValues();
                mainPanel.repaint();
                return;
            }
            if (distanceC3 < distanceC2 && distanceC3 < distanceC1 && distanceC3 < circle3.getR()) {
                circle3.setR(scale * circle3.getR());
                setValues();
                mainPanel.repaint();
            }
        });
        mainPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                cursor = e != null ? new Point(e.getX(), e.getY()) : cursor;
                double distanceC1 = cursor.distance(circle1.getCenter());
                double distanceC2 = cursor.distance(circle2.getCenter());
                double distanceC3 = cursor.distance(circle3.getCenter());

                if (distanceC1 < distanceC2 && distanceC1 < distanceC3 && distanceC1 < circle1.getR()) {
                    circle1.setCenter(cursor);
                    setValues();
                    mainPanel.repaint();
                    return;
                }
                if (distanceC2 < distanceC1 && distanceC2 < distanceC3 && distanceC2 < circle2.getR()) {
                    circle2.setCenter(cursor);
                    setValues();
                    mainPanel.repaint();
                    return;
                }
                if (distanceC3 < distanceC2 && distanceC3 < distanceC1 && distanceC3 < circle3.getR()) {
                    circle3.setCenter(cursor);
                    setValues();
                    mainPanel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                cursor = new Point(e.getX(), e.getY());
                if (cursor.distance(circle1.getCenter()) < circle1.getR()
                        || cursor.distance(circle2.getCenter()) < circle2.getR()
                        || cursor.distance(circle3.getCenter()) < circle3.getR()) {
                    mainPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else mainPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                mainPanel.repaint();
            }
        });
    }

    public static void setupButtons(JPanel mainPanel, JPanel topPanel){
        JCheckBox hideHomotheticsCB = new JCheckBox("Homothetic centers & lines", showHomothetic);
        hideHomotheticsCB.addItemListener(e -> {
            showHomothetic = !showHomothetic;
            mainPanel.repaint();
        });
        topPanel.add(hideHomotheticsCB);

        JCheckBox radicalCB = new JCheckBox("Radical center", showRadical);
        radicalCB.addItemListener(e -> {
            showRadical = !showRadical;
            mainPanel.repaint();
        });
        topPanel.add(radicalCB);

        JCheckBox inversePolesCB = new JCheckBox("Inverse poles", showInversePoles);
        inversePolesCB.addItemListener(e -> {
            showInversePoles = !showInversePoles;
            mainPanel.repaint();
        });
        topPanel.add(inversePolesCB);

        JCheckBox connectorsCB = new JCheckBox("Connectors", showConnectors);
        connectorsCB.addItemListener(e -> {
            showConnectors = !showConnectors;
            mainPanel.repaint();
        });
        topPanel.add(connectorsCB);

        JCheckBox apollonius1CB = new JCheckBox("A1", showApolloniusCircles1);
        apollonius1CB.addItemListener(e -> {
            showApolloniusCircles1 = !showApolloniusCircles1;
            mainPanel.repaint();
        });
        topPanel.add(apollonius1CB);

        JCheckBox apollonius2CB = new JCheckBox("A2", showApolloniusCircles2);
        apollonius2CB.addItemListener(e -> {
            showApolloniusCircles2 = !showApolloniusCircles2;
            mainPanel.repaint();
        });
        topPanel.add(apollonius2CB);

        JCheckBox apollonius3CB = new JCheckBox("A3", showApolloniusCircles3);
        apollonius3CB.addItemListener(e -> {
            showApolloniusCircles3 = !showApolloniusCircles3;
            mainPanel.repaint();
        });
        topPanel.add(apollonius3CB);

        JCheckBox apollonius4CB = new JCheckBox("A4", showApolloniusCircles4);
        apollonius4CB.addItemListener(e -> {
            showApolloniusCircles4 = !showApolloniusCircles4;
            mainPanel.repaint();
        });
        topPanel.add(apollonius4CB);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            reset();
            setValues();
            mainPanel.repaint();
        });
        topPanel.add(resetButton);
    }

    public static void setValues() {
        homothetic_centers: {
            exHMCenter1 = Calc.getExternalHomotheticCenter(circle1, circle2);
            inHMCenter1 = Calc.getInternalHomotheticCenter(circle1, circle2);
            exHMCenter2 = Calc.getExternalHomotheticCenter(circle2, circle3);
            inHMCenter2 = Calc.getInternalHomotheticCenter(circle2, circle3);
            exHMCenter3 = Calc.getExternalHomotheticCenter(circle3, circle1);
            inHMCenter3 = Calc.getInternalHomotheticCenter(circle3, circle1);
        }

        homothetic_lines: {
            HMLine1 = new Segment(exHMCenter1, exHMCenter3);
            HMLine2 = new Segment(exHMCenter1, inHMCenter2);
            HMLine3 = new Segment(exHMCenter3, inHMCenter1);
            HMLine4 = new Segment(exHMCenter2, inHMCenter1);
        }

        radical_axes_and_center: {
            radicalAxis1 = Calc.getRadicalAxis(circle1, circle2);
            radicalAxis2 = Calc.getRadicalAxis(circle2, circle3);
            radicalCenter = Calc.findIntersection(radicalAxis1, radicalAxis2);
        }

        inverse_poles_1: {
            inversePoleC1_1 = Calc.getInversePole(HMLine1, circle1);
            inversePoleC2_1 = Calc.getInversePole(HMLine1, circle2);
            inversePoleC3_1 = Calc.getInversePole(HMLine1, circle3);
            lineToRadical1_1 = Calc.getCircleStraightlineIntersection(circle1, new Segment(inversePoleC1_1, radicalCenter));
            lineToRadical2_1 = Calc.getCircleStraightlineIntersection(circle2, new Segment(inversePoleC2_1, radicalCenter));
            lineToRadical3_1 = Calc.getCircleStraightlineIntersection(circle3, new Segment(inversePoleC3_1, radicalCenter));
        }

        inverse_poles_2: {
            inversePoleC1_2 = Calc.getInversePole(HMLine2, circle1);
            inversePoleC2_2 = Calc.getInversePole(HMLine2, circle2);
            inversePoleC3_2 = Calc.getInversePole(HMLine2, circle3);
            lineToRadical1_2 = Calc.getCircleStraightlineIntersection(circle1, new Segment(inversePoleC1_2, radicalCenter));
            lineToRadical2_2 = Calc.getCircleStraightlineIntersection(circle2, new Segment(inversePoleC2_2, radicalCenter));
            lineToRadical3_2 = Calc.getCircleStraightlineIntersection(circle3, new Segment(inversePoleC3_2, radicalCenter));
        }

        inverse_poles_3: {
            inversePoleC1_3 = Calc.getInversePole(HMLine3, circle1);
            inversePoleC2_3 = Calc.getInversePole(HMLine3, circle2);
            inversePoleC3_3 = Calc.getInversePole(HMLine3, circle3);
            lineToRadical1_3 = Calc.getCircleStraightlineIntersection(circle1, new Segment(inversePoleC1_3, radicalCenter));
            lineToRadical2_3 = Calc.getCircleStraightlineIntersection(circle2, new Segment(inversePoleC2_3, radicalCenter));
            lineToRadical3_3 = Calc.getCircleStraightlineIntersection(circle3, new Segment(inversePoleC3_3, radicalCenter));
        }

        inverse_poles_4: {
            inversePoleC1_4 = Calc.getInversePole(HMLine4, circle1);
            inversePoleC2_4 = Calc.getInversePole(HMLine4, circle2);
            inversePoleC3_4 = Calc.getInversePole(HMLine4, circle3);
            lineToRadical1_4 = Calc.getCircleStraightlineIntersection(circle1, new Segment(inversePoleC1_4, radicalCenter));
            lineToRadical2_4 = Calc.getCircleStraightlineIntersection(circle2, new Segment(inversePoleC2_4, radicalCenter));
            lineToRadical3_4 = Calc.getCircleStraightlineIntersection(circle3, new Segment(inversePoleC3_4, radicalCenter));
        }

        apollonius_circles: {
            apolloniusC1_1 = Calc.getCircle3Points(lineToRadical1_1.getP1(), lineToRadical2_1.getP1(), lineToRadical3_1.getP1());
            apolloniusC1_2 = Calc.getCircle3Points(lineToRadical1_1.getP2(), lineToRadical2_1.getP2(), lineToRadical3_1.getP2());

            apolloniusC2_1 = Calc.getCircle3Points(lineToRadical1_2.getP2(), lineToRadical2_2.getP2(), lineToRadical3_2.getP1());
            apolloniusC2_2 = Calc.getCircle3Points(lineToRadical1_2.getP1(), lineToRadical2_2.getP1(), lineToRadical3_2.getP2());

            apolloniusC3_1 = Calc.getCircle3Points(lineToRadical1_3.getP2(),lineToRadical2_3.getP1(), lineToRadical3_3.getP2());
            apolloniusC3_2 = Calc.getCircle3Points(lineToRadical1_3.getP1(),lineToRadical2_3.getP2(), lineToRadical3_3.getP1());

            apolloniusC4_1 = Calc.getCircle3Points(lineToRadical1_4.getP1(),lineToRadical2_4.getP2(),lineToRadical3_4.getP2());
            apolloniusC4_2 = Calc.getCircle3Points(lineToRadical1_4.getP2(),lineToRadical2_4.getP1(),lineToRadical3_4.getP1());
        }
    }

    public static void setupHomotheticLines() {
        Map<Circle, List<Point>> map = new HashMap<>();
        map.put(circle1, new ArrayList<>());
        map.get(circle1).add(exHMCenter1);
        map.get(circle1).add(inHMCenter1);

        map.put(circle2, new ArrayList<>());
        map.get(circle2).add(exHMCenter2);
        map.get(circle2).add(inHMCenter2);

        map.put(circle3, new ArrayList<>());
        map.get(circle3).add(exHMCenter3);
        map.get(circle3).add(inHMCenter3);

        List<Map.Entry<Circle, List<Point>>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByKey(Comparator.comparingDouble(Circle::getR)));

        HMLine1 = new Segment(entryList.get(0).getValue().get(0), entryList.get(1).getValue().get(0));
        HMLine2 = new Segment(entryList.get(0).getValue().get(0), entryList.get(1).getValue().get(1));
        HMLine3 = new Segment(entryList.get(1).getValue().get(0),  entryList.get(2).getValue().get(1));
        HMLine4 = new Segment(entryList.get(1).getValue().get(1),  entryList.get(2).getValue().get(0));
    }

    public static void reset() {
        circle1 = new Circle(new Point(440, 440), 38);
        circle2 = new Circle(new Point(300, 395), 58);
        circle3 = new Circle(new Point(448, 320), 22);
        radicalAxis1 = new StraightLine();
        radicalAxis2 = new StraightLine();

        showHomothetic = false;
        showRadical = true;
        showInversePoles = false;
        showConnectors = false;
        showApolloniusCircles1 = true;
        showApolloniusCircles2 = true;
        showApolloniusCircles3 = true;
        showApolloniusCircles4 = true;
    }
}
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Main extends JPanel {
    static Point2D.Double cursor = new Point2D.Double();

    static Circle circle1 = new Circle(new Point2D.Double(440, 440), 38);
    static Circle circle2 = new Circle(new Point2D.Double(300, 395), 58);
    static Circle circle3 = new Circle(new Point2D.Double(448, 320), 22);

    static Point2D.Double exHMCenter1, inHMCenter1, exHMCenter2, inHMCenter2, exHMCenter3, inHMCenter3;
    static Line2D.Double HMLine1, HMLine2, HMLine3, HMLine4;
    static boolean showHomothetic = false;

    static StraightLine radicalAxis1 = new StraightLine();
    static StraightLine radicalAxis2 = new StraightLine();
    static Point2D.Double radicalCenter;
    static boolean showRadical = true;

    static Point2D.Double inversePoleC1_1, inversePoleC2_1, inversePoleC3_1;
    static Point2D.Double inversePoleC1_2, inversePoleC2_2, inversePoleC3_2;
    static Point2D.Double inversePoleC1_3, inversePoleC2_3, inversePoleC3_3;
    static Point2D.Double inversePoleC1_4, inversePoleC2_4, inversePoleC3_4;
    static boolean showInversePoles = false;

    static Line2D.Double lineToRadical1_1, lineToRadical2_1, lineToRadical3_1;
    static Line2D.Double lineToRadical1_2, lineToRadical2_2, lineToRadical3_2;
    static Line2D.Double lineToRadical1_3, lineToRadical2_3, lineToRadical3_3;
    static Line2D.Double lineToRadical1_4, lineToRadical2_4, lineToRadical3_4;
    static boolean showConnectors = false;

    static Circle apolloniusC1_1, apolloniusC1_2;
    static Circle apolloniusC2_1, apolloniusC2_2;
    static Circle apolloniusC3_1, apolloniusC3_2;
    static Circle apolloniusC4_1, apolloniusC4_2;
    static boolean showApolloniusCircles1 = true;
    static boolean showApolloniusCircles2 = true;
    static boolean showApolloniusCircles3 = true;
    static boolean showApolloniusCircles4 = true;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        draw3Circles(g2);
        drawHomotheticCenters(g2);
        drawHomotheticLines(g2);
        drawRadicalCenter(g2);
        drawInversePoles(g2);
        drawConnectors(g2);
        drawApolloniusCircles(g2);
    }

    public void draw3Circles(Graphics2D g2) {
        g2.setColor(new Color(0.0f,0.0f,0.0f,0.1f));
        DrawService.drawCircleFill(g2, circle1);
        DrawService.drawCircleFill(g2, circle2);
        DrawService.drawCircleFill(g2, circle3);
    }

    public void drawHomotheticCenters(Graphics2D g2) {
        if (!showHomothetic) return;
        g2.setColor(Color.LIGHT_GRAY);
        DrawService.drawPoint(g2, exHMCenter1, 4);
        DrawService.drawPoint(g2, inHMCenter1, 4);

        DrawService.drawPoint(g2, exHMCenter2, 4);
        DrawService.drawPoint(g2, inHMCenter2, 4);

        DrawService.drawPoint(g2, exHMCenter3, 4);
        DrawService.drawPoint(g2, inHMCenter3, 4);
    }

    public void drawHomotheticLines(Graphics2D g2) {
        if (!showHomothetic) return;
        g2.setColor(Color.LIGHT_GRAY);
        DrawService.drawLine(g2, HMLine1);
        DrawService.drawLine(g2, HMLine2);
        DrawService.drawLine(g2, HMLine3);
        DrawService.drawLine(g2, HMLine4);
    }

    public void drawRadicalCenter(Graphics2D g2) {
        if (!showRadical) return;
        g2.setColor(Color.BLACK);
        DrawService.drawPoint(g2, radicalCenter, 10);
    }

    public void drawInversePoles(Graphics2D g2) {
        if (!showInversePoles) return;
        g2.setColor(new Color(195, 146, 9));
        DrawService.drawPoint(g2, inversePoleC1_1, 4);
        DrawService.drawPoint(g2, inversePoleC2_1, 4);
        DrawService.drawPoint(g2, inversePoleC3_1, 4);

        g2.setColor(new Color(189, 15, 216));
        DrawService.drawPoint(g2, inversePoleC1_2, 4);
        DrawService.drawPoint(g2, inversePoleC2_2, 4);
        DrawService.drawPoint(g2, inversePoleC3_2, 4);

        g2.setColor(new Color(61, 168, 38));
        DrawService.drawPoint(g2, inversePoleC1_3, 4);
        DrawService.drawPoint(g2, inversePoleC2_3, 4);
        DrawService.drawPoint(g2, inversePoleC3_3, 4);

        g2.setColor(new Color(6, 126, 202));
        DrawService.drawPoint(g2, inversePoleC1_4, 4);
        DrawService.drawPoint(g2, inversePoleC2_4, 4);
        DrawService.drawPoint(g2, inversePoleC3_4, 4);
    }

    public void drawConnectors(Graphics2D g2) {
        if (!showConnectors) return;
        g2.setColor(new Color(195, 146, 9));
        DrawService.drawLine(g2, lineToRadical1_1);
        DrawService.drawLine(g2, lineToRadical2_1);
        DrawService.drawLine(g2, lineToRadical3_1);

        g2.setColor(new Color(189, 15, 216));
        DrawService.drawLine(g2, lineToRadical1_2);
        DrawService.drawLine(g2, lineToRadical2_2);
        DrawService.drawLine(g2, lineToRadical3_2);

        g2.setColor(new Color(61, 168, 38));
        DrawService.drawLine(g2, lineToRadical1_3);
        DrawService.drawLine(g2, lineToRadical2_3);
        DrawService.drawLine(g2, lineToRadical3_3);

        g2.setColor(new Color(6, 126, 202));
        DrawService.drawLine(g2, lineToRadical1_4);
        DrawService.drawLine(g2, lineToRadical2_4);
        DrawService.drawLine(g2, lineToRadical3_4);
    }

    public void drawApolloniusCircles(Graphics2D g2) {
        if(showApolloniusCircles1) {
            g2.setColor(new Color(195, 146, 9));
            DrawService.drawCircle(g2, apolloniusC1_1);
            DrawService.drawCircle(g2, apolloniusC1_2);
        }

        if(showApolloniusCircles2) {
            g2.setColor(new Color(189, 15, 216));
            DrawService.drawCircle(g2, apolloniusC2_1);
            DrawService.drawCircle(g2, apolloniusC2_2);
        }

        if(showApolloniusCircles3) {
            g2.setColor(new Color(61, 168, 38));
            DrawService.drawCircle(g2, apolloniusC3_1);
            DrawService.drawCircle(g2, apolloniusC3_2);
        }

        if(showApolloniusCircles4) {
            g2.setColor(new Color(6, 126, 202));
            DrawService.drawCircle(g2, apolloniusC4_1);
            DrawService.drawCircle(g2, apolloniusC4_2);
        }
    }

    public static void main(String[] args) {
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

        setValues();
    }

    public static void setListeners(JPanel mainPanel) {
        mainPanel.addMouseWheelListener(e -> {
            int unitWheelRotation = e.getWheelRotation() / Math.abs(e.getWheelRotation());
            double scale = Math.abs(0.1 - unitWheelRotation);

            double distanceC1 = Calc.distance(cursor, circle1.getCenter());
            double distanceC2 = Calc.distance(cursor, circle2.getCenter());
            double distanceC3 = Calc.distance(cursor, circle3.getCenter());

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
                cursor = e != null ? new Point2D.Double(e.getX(), e.getY()) : cursor;
                double distanceC1 = Calc.distance(cursor, circle1.getCenter());
                double distanceC2 = Calc.distance(cursor, circle2.getCenter());
                double distanceC3 = Calc.distance(cursor, circle3.getCenter());

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
                cursor = new Point2D.Double(e.getX(), e.getY());
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
            HMLine1 = new Line2D.Double(exHMCenter1.x, exHMCenter1.y, exHMCenter3.x, exHMCenter3.y);
            HMLine2 = new Line2D.Double(exHMCenter1.x, exHMCenter1.y, inHMCenter2.x, inHMCenter2.y);
            HMLine3 = new Line2D.Double(exHMCenter3.x, exHMCenter3.y, inHMCenter1.x, inHMCenter1.y);
            HMLine4 = new Line2D.Double(exHMCenter2.x, exHMCenter2.y, inHMCenter1.x, inHMCenter1.y);
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
            lineToRadical1_1 = Calc.getCircleStraightlineIntersection(circle1, new Line2D.Double(inversePoleC1_1, radicalCenter));
            lineToRadical2_1 = Calc.getCircleStraightlineIntersection(circle2, new Line2D.Double(inversePoleC2_1, radicalCenter));
            lineToRadical3_1 = Calc.getCircleStraightlineIntersection(circle3, new Line2D.Double(inversePoleC3_1, radicalCenter));
        }

        inverse_poles_2: {
            inversePoleC1_2 = Calc.getInversePole(HMLine2, circle1);
            inversePoleC2_2 = Calc.getInversePole(HMLine2, circle2);
            inversePoleC3_2 = Calc.getInversePole(HMLine2, circle3);
            lineToRadical1_2 = new Line2D.Double(inversePoleC1_2, radicalCenter);
            lineToRadical1_2 = Calc.getCircleStraightlineIntersection(circle1, lineToRadical1_2);
            lineToRadical2_2 = new Line2D.Double(inversePoleC2_2, radicalCenter);
            lineToRadical2_2 = Calc.getCircleStraightlineIntersection(circle2, lineToRadical2_2);
            lineToRadical3_2 = new Line2D.Double(inversePoleC3_2, radicalCenter);
            lineToRadical3_2 = Calc.getCircleStraightlineIntersection(circle3, lineToRadical3_2);
        }

        inverse_poles_3: {
            inversePoleC1_3 = Calc.getInversePole(HMLine3, circle1);
            inversePoleC2_3 = Calc.getInversePole(HMLine3, circle2);
            inversePoleC3_3 = Calc.getInversePole(HMLine3, circle3);
            lineToRadical1_3 = Calc.getCircleStraightlineIntersection(circle1, new Line2D.Double(inversePoleC1_3, radicalCenter));
            lineToRadical2_3 = Calc.getCircleStraightlineIntersection(circle2, new Line2D.Double(inversePoleC2_3, radicalCenter));
            lineToRadical3_3 = Calc.getCircleStraightlineIntersection(circle3, new Line2D.Double(inversePoleC3_3, radicalCenter));
        }

        inverse_poles_4: {
            inversePoleC1_4 = Calc.getInversePole(HMLine4, circle1);
            inversePoleC2_4 = Calc.getInversePole(HMLine4, circle2);
            inversePoleC3_4 = Calc.getInversePole(HMLine4, circle3);
            lineToRadical1_4 = Calc.getCircleStraightlineIntersection(circle1, new Line2D.Double(inversePoleC1_4, radicalCenter));
            lineToRadical2_4 = Calc.getCircleStraightlineIntersection(circle2, new Line2D.Double(inversePoleC2_4, radicalCenter));
            lineToRadical3_4 = Calc.getCircleStraightlineIntersection(circle3, new Line2D.Double(inversePoleC3_4, radicalCenter));
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

    public static void reset() {
        circle1 = new Circle(new Point2D.Double(440, 440), 38);
        circle2 = new Circle(new Point2D.Double(300, 395), 58);
        circle3 = new Circle(new Point2D.Double(448, 320), 22);
    }
}
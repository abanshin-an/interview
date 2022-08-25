package ru.interview.lesson1.exercise3;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Triangle extends Figure {
    @Override
    public void draw(Graphics g) {
        TriangleShape triangleShape = new TriangleShape(new Point2D.Double(170, 180),
                new Point2D.Double(70, 180), new Point2D.Double(180, 70));
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.draw(triangleShape);
    }

}

class TriangleShape extends Path2D.Double {
    public TriangleShape(Point2D... points) {
        moveTo(points[0].getX(), points[0].getY());
        lineTo(points[1].getX(), points[1].getY());
        lineTo(points[2].getX(), points[2].getY());
        closePath();
    }
}
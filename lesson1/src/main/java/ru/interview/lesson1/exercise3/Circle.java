package ru.interview.lesson1.exercise3;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape circleShape = new Ellipse2D.Double(100, 100, 100, 100);

        g2d.draw(circleShape);
    }
}

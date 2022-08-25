package ru.interview.lesson1.exercise3;

import java.awt.*;
import java.awt.geom.Point2D;

public class Squire extends Figure {
    private static final Point2D.Double start= new Point2D.Double(30,50);
    private static final double edge=50.0;
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect((int)start.getX(), (int)start.getY(), (int)(start.getX()+edge), (int)(start.getY()+edge));
    }
}

package ru.interview.lesson1.exercise3;

import javax.swing.*;
import java.awt.*;

public class Painter extends JFrame {
    private final Figure[] figures;

    public Painter() {
        setTitle("Drawing a Circle");
        setSize(250, 250);
        figures = new Figure[]{new Circle(),
                new Squire(),
                new Triangle()
        };
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Painter();
    }

    @Override
    public void paint(Graphics g) {
        if (figures != null) for (Figure f : figures) {
            f.draw(g);
        }
    }
}

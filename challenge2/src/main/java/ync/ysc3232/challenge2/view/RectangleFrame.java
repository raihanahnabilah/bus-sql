package ync.ysc3232.challenge2.view;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JFrame;

import ync.ysc3232.challenge2.model.ColoredRectangle;

/**
 * This Frame is basically one canvas dedicated to draw colored tiles (ColoredRectangle objects).
 */
public class RectangleFrame extends JFrame {

    public RectangleFrame(String title, Collection<ColoredRectangle> list) {
        super(title);
        Canvas canvas = new RectangleCanvas(list);
        getContentPane().add(canvas);
        getContentPane().setLayout(new GridLayout(0, 1));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

package ync.ysc3232.challenge2.view;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collection;

import ync.ysc3232.challenge2.model.ColoredRectangle;
import ync.ysc3232.challenge2.model.Rectangle;

public class RectangleCanvas extends Canvas {
    private final Collection<ColoredRectangle> list;

    public RectangleCanvas (Collection<ColoredRectangle> list) {
        setBackground (Color.WHITE);

        Integer maxBottom = 0;
        Integer maxRight = 0;
        for (Rectangle r : list) {
            maxBottom = Math.max(r.getBottom(),maxBottom);
            maxRight = Math.max(r.getRight(),maxRight);
        }

        setSize(maxRight,maxBottom);
        this.list = list;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        Color color = Color.white;
        for (ColoredRectangle r : list) {
            // adding specifications
            color = r.getColor(); // color.darker();
            g2d.setColor(color);
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.25f));
            g2d.fillRect(r.getLeft(), r.getTop(),  r.getWidth(), r.getHeight());
        }
        g2d.dispose();
    }
}

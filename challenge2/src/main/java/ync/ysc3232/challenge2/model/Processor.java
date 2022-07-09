package ync.ysc3232.challenge2.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * The processor class is given a list of rectangle when initialize.
 * Then it can produce two kinds of colored list:
 *  - random colors
 *  - clusters colors
 */
public class Processor {

    private final Rectangle[] list;

    public Processor(Rectangle[] list) {
        this.list = list;
    }

    /**
     * Returns a random color
     * @return random color
     */
    private Color getRandomColor() {
        int b = (int) (Math.random() * 255);
        int s = (int) (Math.random() * 255);
        int h = (int) (Math.random() * 255);
        return Color.getHSBColor(h,s,b);
    }

    /**
     * return a list of randomly colored tiles
     * @return Colored tiles
     */
    public Collection<ColoredRectangle> getRandomlyColoredRectangles() {
        Collection<ColoredRectangle> result = new ArrayList<>();
        // color the roots N
        int i = 0;
        for (Rectangle r : this.list) {
            i = i + 1;
            float s = 0.5f;
            float b = 0.5f;
            float h = (float) i / result.size();
            result.add(new ColoredRectangle(r, Color.getHSBColor(h,s,b)));
        }
        return result;
    }


    /**
     * returns a colored version of the tiles.
     * Every tiles that intersect are colored the same.
     * @return Colored tiles
     */
    public Collection<ColoredRectangle> getClusteredColoredRectangles() {

        // Build the set of elements
        Set<UnionElement<ColoredRectangle>> elements = new HashSet<>();
        for (Rectangle r : list) {
            elements.add(new UnionElement<>(new ColoredRectangle(r)));
        }

        // for every pair of elements join them
        // N^2
        for (UnionElement<ColoredRectangle> e1 : elements) {
            for (UnionElement<ColoredRectangle> e2 : elements) {
                if (e1.getValue().intersects(e2.getValue())) {
                    UnionElement.union(e1, e2);
                }
            }
        }

        // find the roots NLogN
        Set<UnionElement<ColoredRectangle>> roots = new HashSet<>();
        for (UnionElement<ColoredRectangle> e1 : elements) {
            if (e1.getRoot() == e1) {
                roots.add(e1);
            }
        }

        // color the roots N
        int i = 0;
        for (UnionElement<ColoredRectangle> e1 : roots) {
            i = i + 1;
            float s = 0.5f;
            float b = 0.5f;
            float h = (float) i / roots.size();
            e1.getValue().setColor(Color.getHSBColor(h,s,b));
        }

        // color rectangles N
        ArrayList<ColoredRectangle> res = new ArrayList<>();
        for (UnionElement<ColoredRectangle> e1 : elements) {
            e1.getValue().setColor(e1.getRoot().getValue().getColor());
            res.add(e1.getValue());
        }

        return res;
    }

}

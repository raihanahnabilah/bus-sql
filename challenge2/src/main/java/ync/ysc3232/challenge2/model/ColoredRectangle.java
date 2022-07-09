package ync.ysc3232.challenge2.model;

import java.awt.Color;

// Task 2 (5 points) - Using JavaDoc, write the documentation for the `ColoredRectangle` class.

/**
 * ColoredRectangle object is used to color the Rectangle.
 * This is called mainly in the Processor object.
 */
public class ColoredRectangle extends Rectangle {

    /**
     * The Color of the rectangle
     */
    Color color;

    /**
     * The ColoredRectangle constructor which takes
     * the rectangle and the randomColor set by the user/processor
     * @param rectangle the Rectangle that we want to color
     * @param randomColor the color of the Rectangle
     */
    public ColoredRectangle(Rectangle rectangle, Color randomColor) {
        super(rectangle);
        this.color = randomColor;
    }

    /**
     * The ColoredRectangle constructor in which
     * when only a rectangle gets passed on to this constructor,
     * the default color will be black
     * @param r the Rectangle that we want to color
     */
    public ColoredRectangle(Rectangle r) {
        super(r);
        this.color = Color.black;
    }

    /**
     * The setter function to set the
     * color of the rectangle
     * @param color the color of the Rectangle
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The getter function to get the color of the rectangle
     * @return the color of the Rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * toString function to print our coloredRectangle,
     * which is the coordinates of the rectangle (left, right, top, bottom),
     * and the color of the rectangle.
     * Main purpose of this is usually for debugging.
     * @return
     */
    @Override
    public String toString() {
        return "ColoredRectangle{" +
                "left=" + getLeft() +
                ", right=" + getRight() +
                ", top=" + getTop() +
                ", bottom=" + getBottom() +
                ", color=" + color +
                '}';
    }

}

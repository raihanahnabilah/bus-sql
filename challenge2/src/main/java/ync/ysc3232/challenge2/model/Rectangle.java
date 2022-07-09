package ync.ysc3232.challenge2.model;

/**
 * The Rectangle object is used to store a tile location.
 *  - left,right,bottom,top are used to describe this location.
 */
public class Rectangle {

    private int left;
    private int right;
    private int top;
    private int bottom;


    public Rectangle(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public Rectangle(Rectangle rectangle) {
        this.left = rectangle.left;
        this.right = rectangle.right;
        this.top = rectangle.top;
        this.bottom = rectangle.bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "left=" + left +
                ", right=" + right +
                ", top=" + top +
                ", bottom=" + bottom +
                '}';
    }

    public int getWidth() {
        return Math.abs(right - left);
    }

    public int getHeight() {
        return Math.abs(top - bottom);
    }

    /**
     * Given two rectangles (this one, and another), we can check if they intersect.
     * If they do, the intersect function returns True.
     * @param other The other Rectangle to check the intersection with.
     * @return True if the two rectangle intersect, False otherwise.
     */
    public boolean intersects(Rectangle other) {

        // Task 1 (5 points) - Write a unit-test for the `intersect()` function. If necessary, fix any bug inside the `intersect()` function.


        /* SOLUTION BEGIN */
        return (other.getLeft() < getRight() && getLeft() < other.getRight()
                && getTop() < other.getBottom() && other.getTop() < getBottom()) ;
        /* SOLUTION END */

    }

}

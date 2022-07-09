import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.css.Rect;

import ync.ysc3232.challenge2.model.Rectangle;

public class TestRectangle {

    @Test
    public void test_get_width() {
        Rectangle r = new Rectangle(0,1,2,3);
        Assert.assertEquals(1, r.getWidth());
    }

    @Test
    public void test_intersect() {

        // Task 1 (5 points) - Write a unit-test for the `intersect()` function. If necessary, fix any bug inside the `intersect()` function.

        Rectangle r1 = new Rectangle(300, 400, 000, 200);
        Rectangle r2 = new Rectangle(300, 600, 000, 100);
        Rectangle r3 = new Rectangle(500, 800, 000, 200);
        Assert.assertEquals(true, r1.intersects(r2));
        Assert.assertEquals(true, r2.intersects(r3));
        Assert.assertEquals(false, r1.intersects(r3));


        // TO BE IMPLEMENTED


    }

}

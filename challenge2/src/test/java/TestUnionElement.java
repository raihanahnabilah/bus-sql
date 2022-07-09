import org.junit.Assert;
import org.junit.Test;

import ync.ysc3232.challenge2.model.UnionElement;

public class TestUnionElement {
    @Test
    public void test_contructor() {
        Integer expected = 1;
        UnionElement<Integer> e = new UnionElement<Integer>(expected);
        Assert.assertEquals(expected, e.getValue());
    }

    @Test
    public void test_parent() {

        UnionElement<Integer> root = new UnionElement<Integer>(1);
        UnionElement<Integer> child1 = new UnionElement<Integer>(2);
        UnionElement<Integer> child2 = new UnionElement<Integer>(3);
        child1.setParent(root);
        child2.setParent(child2);
        Assert.assertEquals(root, root.getParent());
        Assert.assertEquals(root, child1.getParent());
        Assert.assertEquals(child1, child2.getParent());

    }

    @Test
    public void test_get_root() {
        UnionElement<Integer> root = new UnionElement<Integer>(1);
        UnionElement<Integer> child1 = new UnionElement<Integer>(2);
        UnionElement<Integer> child2 = new UnionElement<Integer>(3);
        child1.setParent(root);
        child2.setParent(child1);
        Assert.assertEquals(root, root.getRoot());
        Assert.assertEquals(root, child1.getRoot());
        Assert.assertEquals(root, child2.getRoot());
    }

    @Test
    public void test_union() {

        // Task 4 (5 points) - Write a unit-test for the `union()` function of `UnionElement`. If necessary, fix the union function.

        // TO BE IMPLEMENTED
        UnionElement<Integer> root = new UnionElement<Integer>(1);
        UnionElement<Integer> child1 = new UnionElement<Integer>(2);
        UnionElement<Integer> child2 = new UnionElement<Integer>(3);
        child1.setParent(root);
        child2.setParent(child1);
        UnionElement.union(child1, child1);
        Assert.assertEquals(child1.getRoot(), child2.getRoot());

    }
}

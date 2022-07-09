package ync.ysc3232.challenge2.model;

import java.util.Collection;

/**
 * This class is used to solve the clustering problem.
 * A UnionElement is a element associated with a value, that also has a parent.
 * By default the element's parent is itself.
 *
 */
public class UnionElement<T> {
    private T value;
    UnionElement parent;

    /**
     * Construct an element with associated value.
     * Parent is itself.
     * @param value
     */
    public UnionElement(T value) {
        this.value = value;
        this.parent = this;
    }

    /**
     * Every element have a parent, and the parent has a parent too etc...
     * until the parent's parent is itself. This is a root.
     * The find function search for the root of an Element.
     * @return The root of the element
     */
    public UnionElement<T> getRoot() {
        if (this.parent == this) {
            return this;
        } else {
            this.parent = this.parent.getRoot();
            return this.parent;
        }
    }


    /**
     * The union of two elements e1 and e2 ensures that both element have the same root after union.
     * @param e1
     * @param e2
     */
    public static void union(UnionElement e1, UnionElement e2) {


        // Task 4 (5 points) - Write a unit-test for the `union()` function of `UnionElement`. If necessary, fix the union function.


        UnionElement x = e1.getRoot();
        UnionElement y = e2.getRoot();
        if (x != y) {
            y.parent = x;
        }
    }



    public T getValue() {
        return value;
    }

    public UnionElement<T> getParent() {
        return this.parent;
    }

    public void setParent(UnionElement<T> root) {
        this.parent = root;
    }
}

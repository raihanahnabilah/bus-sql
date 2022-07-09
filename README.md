# Welcome to the challenge 2 of YSC3232.

This challenge will evaluate your level across different aspects of software engineering.

This aims to assess your ability to

 - **read** the source code of a program,
 - **write** unit-tests,
 - **fix** bugs,
 - **document** a software, and
 - **refactor** and **design** software.

The maximal number of point is 20pt, but the sum of the exercises is 25pt.
You should already be able to run the project on your computer (as a sample was available three days ago).

## Description

This program accepts a list of tiles/rectangles and draw them given their coordinate. 
The program opens two different windows for two different coloring methodologies.

 1) Random coloring: any tile could have any color
 2) Cluster coloring: any tile in contact must have the same exact color.

Two windows are opened and show these two coloring methods.

### The Main object

from the `Main` class of the project, you can see:

  - It initializes a `Processor` with a `Demo` dataset (a list of tiles).
  - The `Processor` is used to color tiles in a list of tiles.
  - Then two windows (`JFrame`) are open to visualize the two ways to color tiles.

### The Rectangle object 

The `Rectangle` object is used to store a tile location. the variable left, right, bottom, and top are used to describe this location.
For example, for a rectangle `r`, its Top-Left corner becomes `(r.left,r.top)`. 
The `intersect` function is used to test if two tiles overlap.

For example,
```
r1: Rectangle(300, 400, 000, 200)
r2: Rectangle(300, 600, 000, 100)
r3: Rectangle(500, 800, 000, 200)
```
Here, `r1` intersects with `r2` (`r1.intersect(r2) returns True`), but not with `r3` (`r1.intersect(r3) returns False`).
By definition `r.intersect(rp) should always be equal to rp.intersect(r)`.


### The ColoredRectangle object 

The `ColoredRectangle` object is a `Rectangle` with a `Color` property. 


### The Processor object

The `Processor` is given a list of rectangles when initialized.
Then it can produce two kinds of ColoredRectangle's list:
  - Random colors
  - Clusters colors
    

### The UnionElement object

This object is used by the `Processor` to assign the Clusters colors. it is based on disjoint-set data structure. 

In disjoint-set data structure, every stored element can be part of a particular `Set`. 
At first each element are in different `Set`, 
but using a `union()` function, the different `Set` can be merged together.
More information: [wikipedia](https://en.wikipedia.org/wiki/Disjoint-set_data_structure)

When tiles overlap, they must be part of the same `Set`, thus they will have the same color.


# Exercises

 - Task 1 (5 points) - Write a unit-test for the `intersect()` function (place holder in the `TestRectangle.java` file). If necessary, fix any bug inside the `intersect()` function.
 - Task 2 (5 points) - Using JavaDoc, write the documentation for the `ColoredRectangle` class.
 - Task 3 (10 points) - The current user interface opens two different frames to display two different coloring methods.
      Our team is looking for merging the content of both Frames into a single one, next to each other. 
      Without implementing it, describe how you could refactor the software to facilitate this transformation.
      Include any relevant information regarding problems of the current design, and what will be improved.
      Include your solution as a `TODO.md` file.
 - Task 4 (5 points) - Write a unit-test for the `union()` function of `UnionElement` (place holder in the `TestUnionElement.java` file). If necessary, fix the union function.
package ync.ysc3232.challenge2;

import java.util.Collection;
import java.util.EventListener;

import javax.swing.JFrame;

import ync.ysc3232.challenge2.model.ColoredRectangle;
import ync.ysc3232.challenge2.model.Demo;
import ync.ysc3232.challenge2.model.Processor;
import ync.ysc3232.challenge2.view.RectangleFrame;

/**
 *
 * This program accepts a list of tiles/rectangles and draw them given their coordinate with colors.
 * The program opens two different windows for two different coloring methodologies.
 *
 *  1) Random coloring: any tile could have any color
 *  2) Cluster coloring: any tile in contact must have the same exact color.
 *
 * Two windows are opened and show these two coloring methods.
 *
 * from the main class of the project, you can see:
 *
 *   - It initializes a `Processor` with a demo dataset (a list of tiles).
 *   - The processor is used to color tiles in a list of tiles.
 *   - Then two windows (`JFrame`) are open to visualize the two ways to color tiles.
 *
 */

// Task 3 (10 points) - The current user interface opens two different frames to display two different coloring methods.
//      Our team is looking for merging the content of both Frames into a single one, next to each other.
//      Without implementing it, describe how you could refactor the software to facilitate this transformation.
//      Include any relevant information regarding problems of the current design, and what will be improved.


public class Main {

    public static void main(String[] args) {

        // This is the processor, it stores the dataset and can color the tiles.
        Processor processor = new Processor(Demo.test1);

        // These are two different color results
        Collection<ColoredRectangle> rcolored = processor.getRandomlyColoredRectangles();
        Collection<ColoredRectangle> ccolored = processor.getClusteredColoredRectangles();


        // Open two windows
        RectangleFrame frame1 = new RectangleFrame("Random colors", rcolored);
        RectangleFrame frame2 = new RectangleFrame("Cluster colors", ccolored);
    }


}
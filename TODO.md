# Task 3

For the RectangleCanvas, instead of taking only one 
Collection of Colored Rectangles
and its colored specification (cluster/random)
in its parameter,
it will be able to take a List of Collections of Rectangles.
This way, we can pass on more than one (in this case, two)
Collection of Colored Rectangles.

Then, in the Rectangle Canvas, the RectangleCanvas will
traverse through all the Collection of Colored Rectangles
in the List. Assume that there are only
two Collections of Colored Rectangles.
Then, first, it will traverse through
the FIRST Collection of the Colored Rectangles.
In this case, the maxBottom and maxRight fields will be set
according to the FIRST collection's rectangles with highest
Bottom and Right value. 
Then, it will traverse through the SECOND Collection
of the Colored Rectangles. When it does so, it will
set the new left and right position of all the Rectangles
in the SECOND Collection by incrementing the left and right 
position with the maxRight value that was previously set by 
the rectangles in the FIRST collection.
All in all, the main purpose is this is so that the position of all the Rectangles in the SECOND collection
will start after the right most position of the
Rectangles in the FIRST collection.
Perhaps, we can create
a new field called "currMaxRight", so that we can keep
track on the current rightmost value to be incremented to the left and 
right position of the SECOND collection of the Rectangles. This way, we can still use the 
field "maxRight" to find the rightMost value of all Rectangles traversed.
The LAST Collection of Colored Rectangles (in this case, the SECOND Collection) 
will then set the new maxRight values.

Then, on the RectangleFrame, instead of taking the title and the Collection, it will instead take the List of Collections of ColoredRectangles.
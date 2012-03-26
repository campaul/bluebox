# bluebox

**bluebox** is a set of Java utilities for rapid prototyping and 2D rendering.

## Creating a Sketch

The Sketch class provides basic 2D rendering facilities. This example will
allow the user to draw freehand lines by clicking and dragging the mouse.
The built in SketchFrame class provides an easy way to present a Sketch.

```java
import bluebox.core.Sketch;
import bluebox.graphics.GraphicsContext;
import bluebox.ui.SketchFrame;

public class Drawing extends Sketch {

    private int oldX = 0;
    private int oldY = 0;

    public Draw() {
        this.setTitle("Draw");
        this.setSize(500, 500);
        this.setFramerate(60);
    }

    @Override
    public void draw(GraphicsContext g) {
        if(mouse.buttonDown(1))
            g.drawLine(oldX, oldY, mouse.getX(), mouse.getY());
        g.dispose();

        oldX = mouse.getX();
        oldY = mouse.getY();
    }

    public static void main(String ... args) {
    	new SketchFrame(new Drawing()).present();
    }

}
```

# bluebox

**bluebox** is a set of Java utilities for rapid prototyping and 2D rendering.

## Creating a Sketch

The Sketch class provides basic 2D rendering facilities. This example will
allow the user to draw freehand lines by clicking and dragging the mouse.
```java
public class Draw extends Sketch {

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

}
```

## Displaying a Sketch

There are several ways to present a Sketch, but the easiest is to use the
prebuilt SketchFrame class.
```java
new SketchFrame(new Draw()).present();
```

This will create and display a window containing your Sketch. For a more
flexible option, you can create a SketchPanel. A SketchPanel is a Swing
comonent that can be easily added to any Swing application.
```java
new SketchPanel(new Draw());
```

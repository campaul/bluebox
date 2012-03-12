package bluebox.example;

import java.awt.BasicStroke;

import javax.swing.JColorChooser;

import bluebox.core.Sketch;
import bluebox.graphics.GraphicsContext;
import bluebox.ui.SketchFrame;

public class Painter extends Sketch {

	private static final int MAX_STROKE = 20;
	private static final int MIN_STROKE = 1;
	
	private JColorChooser chooser;
	
	private int stroke = 5;
	private int lastX = 0;
	private int lastY = 0;
	
	public Painter() {
		this.setTitle("Painter");
		this.setSize(500, 500);
		this.setFramerate(60);
		
		this.chooser = new JColorChooser();
		
		this.setComponent("SOUTH", chooser.getChooserPanels()[0]);
	}
	
	@Override
	public void draw(GraphicsContext g) {
		g.setAntialiasing(true);
		g.setColor(chooser.getColor());
		g.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		if(mouse.buttonDown(1))
			g.drawLine(lastX, lastY, mouse.getX(), mouse.getY());
		
		if(keyboard.keyDown('+'))
			stroke = Math.min(MAX_STROKE, stroke + 1);
		if(keyboard.keyDown('-'))
			stroke = Math.max(MIN_STROKE, stroke - 1);
		
		g.dispose();
		
		lastX = mouse.getX();
		lastY = mouse.getY();
	}
	
	public static void main(String ... args) {
		new SketchFrame(new Painter()).present();
	}
	
}

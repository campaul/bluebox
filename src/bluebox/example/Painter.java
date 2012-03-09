package bluebox.example;

import java.awt.BasicStroke;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;

import bluebox.core.Sketch;
import bluebox.graphics.GraphicsContext;

public class Painter extends Sketch {

	private static final int MAX_STROKE = 20;
	private static final int MIN_STROKE = 1;
	
	private JColorChooser chooser;
	
	private int lastX = 0;
	private int lastY = 0;
	private int stroke = 5;
	
	public static void main(String ... args) {
		new Painter().render();
	}
	
	public Painter() {
		this.setTitle("Painter");
		this.setSize(500, 500);
		this.setFramerate(60);
		
		this.chooser = new JColorChooser();
		this.add(this.chooser.getChooserPanels()[0], Sketch.SOUTH);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == '+')
			this.stroke = Math.min(this.stroke + 1, MAX_STROKE);
		if(e.getKeyChar() == '-')
			this.stroke = Math.max(this.stroke - 1, MIN_STROKE);
	}
	
	@Override
	public void draw(GraphicsContext g) {
		g.setAntialiasing(true);
		g.setColor(chooser.getColor());
		g.setStroke(new BasicStroke(this.stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		if(mouse.buttonDown(1))
			g.drawLine(lastX, lastY, mouse.getX(), mouse.getY());
		
		g.dispose();
		
		lastX = mouse.getX();
		lastY = mouse.getY();
	}
	
}

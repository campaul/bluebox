package bluebox.example;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JColorChooser;

import bluebox.core.Sketch;
import bluebox.graphics.Context;
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
	}
	
	public Component getColorChooser() {
		return chooser.getChooserPanels()[0];
	}
	
	@Override
	public void draw(Context context) {
		context.setAntialiasing(true);
		context.setColor(chooser.getColor());
		context.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		if(mouse.buttonDown(1))
			context.drawLine(lastX, lastY, mouse.getX(), mouse.getY());
		
		if(keyboard.keyDown('+'))
			stroke = Math.min(MAX_STROKE, stroke + 1);
		if(keyboard.keyDown('-'))
			stroke = Math.max(MIN_STROKE, stroke - 1);
		
		context.dispose();
		
		lastX = mouse.getX();
		lastY = mouse.getY();
	}
	
	public static void main(String ... args) {
		Painter painter = new Painter();
		SketchFrame frame = new SketchFrame(painter);
		frame.add(painter.getColorChooser(), BorderLayout.SOUTH);
		frame.present();
	}
	
}

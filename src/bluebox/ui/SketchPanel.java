package bluebox.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import bluebox.core.Sketch;
import bluebox.hardware.Keyboard;
import bluebox.hardware.Mouse;

@SuppressWarnings("serial")
public class SketchPanel extends JComponent {

	private Sketch sketch;
	
	private Mouse mouse;
	
	public SketchPanel(final Sketch sketch) {
		this.sketch = sketch;
		
		this.sketch.setMouse(this.mouse = new Mouse(this));
		this.sketch.setKeyboard(new Keyboard(this));
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int xOffset = (getWidth() - sketch.getWidth()) / 2;
				int yOffset = (getHeight() - sketch.getHeight()) / 2;
				
				mouse.setOffset(xOffset, yOffset);
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocus();
			}
		});
		
		this.setPreferredSize(new Dimension(sketch.getWidth(), sketch.getHeight()));
		this.render();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		sketch.draw(sketch.createGraphicsContext());
		g.drawImage(sketch.getImage(), mouse.getXOffset(), mouse.getYOffset(), null);
	}
	
	public void render() {		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					repaint();
					
					try {
						Thread.sleep((long)(1.0 / sketch.getFramerate() * 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}
	
}

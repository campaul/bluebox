package bluebox.hardware;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class Mouse {

	private int x;
	private int y;
	
	private HashMap<Integer, Boolean> buttons;
	
	public Mouse(Component component) {
		buttons = new HashMap<Integer, Boolean>();
		
		component.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				buttons.put(e.getButton(), true);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				buttons.put(e.getButton(), false);
			}
			
		});
		
		component.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			
		});
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean buttonDown(int button) {
		Boolean down = buttons.get(button);
		
		if(down == null)
			down = false;
		
		return down;
	}
	
}

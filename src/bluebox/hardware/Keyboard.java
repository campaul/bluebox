package bluebox.hardware;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keyboard {

	private HashMap<Integer, Boolean> keys;
	
	public Keyboard(Component component) {
		keys = new HashMap<Integer, Boolean>();
		
		component.addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				keys.put(e.getKeyCode(), true);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keys.put(e.getKeyCode(), false);
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
		});
	}
	
	public boolean keyDown(int key) {
		Boolean down = keys.get(key);
		
		if(down == null)
			down = false;
			
		return down;
	}
	
}

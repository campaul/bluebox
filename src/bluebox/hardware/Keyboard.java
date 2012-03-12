package bluebox.hardware;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keyboard {

	private HashMap<Integer, Boolean> keyCodes;
	private HashMap<Character, Boolean> keyChars;
	
	public Keyboard() {
		keyCodes = new HashMap<Integer, Boolean>();
		keyChars = new HashMap<Character, Boolean>();
	}
	
	public Keyboard(Component component) {
		this();
		
		component.addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				keyCodes.put(e.getKeyCode(), true);
				keyChars.put(e.getKeyChar(), true);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keyCodes.put(e.getKeyCode(), false);
				keyChars.put(e.getKeyChar(), false);
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
		});
	}
	
	public boolean keyDown(int key) {
		Boolean down = keyCodes.get(key);
		
		if(down == null)
			down = false;
			
		return down;
	}
	
	public boolean keyDown(char key) {
		Boolean down = keyChars.get(key);
		
		if(down == null)
			down = false;
			
		return down;
	}
	
}

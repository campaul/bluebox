package bluebox.core;

import bluebox.graphics.GraphicsContext;

public interface Drawable {

	public void draw(GraphicsContext g);
	public int getWidth();
	public int getHeight();
	
}

package bluebox.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import bluebox.graphics.Context;
import bluebox.hardware.Keyboard;
import bluebox.hardware.Mouse;

public abstract class Sketch implements Drawable {
	
	private String title = "Sketch";
	private int framerate = 60;
	
	protected Mouse mouse = new Mouse();
	protected Keyboard keyboard = new Keyboard();
	
	private BufferedImage image =
			new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
	
	private HashMap<String, Component> components =
			new HashMap<String, Component>();
		
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setSize(int width, int height) {
		BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		image.getGraphics().drawImage(this.image, 0, 0, null);
		
		this.load(image);
	}
	
	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}
	
	public String setTitle() {
		return this.title;
	}
	
	public int getWidth() {
		return this.image.getWidth();
	}
	
	public int getHeight() {
		return this.image.getHeight();
	}
	
	public int getFramerate() {
		return this.framerate;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	public void setComponent(String id, Component component) {
		this.components.put(id, component);
	}
	
	public Component getComponent(String id) {
		return this.components.get(id);
	}
	
	public void load(File file) {
		try {
			this.load(ImageIO.read(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(BufferedImage image) {
		this.image = image;
	}
	
	public void save(File file, String type) {
		try {
			ImageIO.write(this.image, type, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Color getPixel(int x, int y) {
		return new Color(this.image.getRGB(x, y));
	}
	
	public int getRed(int x, int y) {
		return this.getPixel(x, y).getRed();
	}
	
	public int getGreen(int x, int y) {
		return this.getPixel(x, y).getGreen();
	}
	
	public int getBlue(int x, int y) {
		return this.getPixel(x, y).getBlue();
	}
	
	public void setPixel(int x, int y, Color c) {
		this.image.setRGB(x, y, c.getRGB());
	}
	
	public void setRed(int x, int y, int red) {
		this.setPixel(x, y, new Color(red, this.getGreen(x, y), this.getBlue(x, y)));
	}
	
	public void setGreen(int x, int y, int green) {
		this.setPixel(x, y, new Color(this.getRed(x, y), green, this.getBlue(x, y)));
	}
	
	public void setBlue(int x, int y, int blue) {
		this.setPixel(x, y, new Color(this.getRed(x, y), this.getGreen(x, y), blue));
	}
	
	public Context createGraphicsContext() {
		return new Context(this.image.createGraphics());
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public void draw(Context context) {};
	
}

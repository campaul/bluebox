package bluebox.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bluebox.hardware.Keyboard;
import bluebox.hardware.Mouse;

public abstract class Sketch implements	Drawable,
										MouseListener,
										MouseMotionListener,
										MouseWheelListener,
										KeyListener,
										FocusListener {
	
	private static final String DEFAULT_TITLE = "Sketch";
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 450;
	private static final int DEFAULT_FRAMERATE = 60;
	
	public static final String NORTH = BorderLayout.NORTH;
	public static final String SOUTH = BorderLayout.SOUTH;
	public static final String EAST = BorderLayout.EAST;
	public static final String WEST = BorderLayout.WEST;
	
	private JFrame frame;
	private JPanel panel;
	
	private int framerate;
	private boolean antialiasing;
	
	public Mouse mouse;
	public Keyboard keyboard;
	
	private BufferedImage canvas;
	
	public Sketch(String title, int width, int height, int framerate) {
		this.frame = new JFrame(title);
		this.panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D canvasg = (Graphics2D)canvas.getGraphics();
				if(antialiasing) {
					canvasg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				} else {
					canvasg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
				}
				
				draw(canvasg);
				g.drawImage(canvas, 0, 0, null);
			}
		};
		
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
		this.panel.addMouseWheelListener(this);
		this.frame.addKeyListener(this);
		
		this.mouse = new Mouse(panel);
		this.keyboard = new Keyboard(frame);
		
		this.frame.setLayout(new BorderLayout());
		this.frame.add(panel, BorderLayout.CENTER);
		this.frame.setResizable(false);
		
		this.setCanvasSize(width, height);
		this.setFramerate(framerate);
	}
	
	public Sketch() {
		this(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FRAMERATE);
	}
	
	public void add(Component component, String location) {
		this.frame.add(component, location);
	}
	
	public void setTitle(String title) {
		this.frame.setTitle(title);
	}
	
	public void setCanvasSize(int width, int height) {
		BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		image.getGraphics().drawImage(this.canvas, 0, 0, null);
		
		this.load(image);
	}
	
	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}
	
	public String getTitle() {
		return this.frame.getTitle();
	}
	
	public int getWidth() {
		return this.canvas.getWidth();
	}
	
	public int getHeight() {
		return this.canvas.getHeight();
	}
	
	public int getFramerate() {
		return this.framerate;
	}
	
	public void render() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					frame.repaint();
					
					try {
						Thread.sleep((long)(1.0/framerate * 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}
	
	public void load(File file) {
		try {
			this.load(ImageIO.read(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(BufferedImage image) {
		this.canvas = image;
		this.panel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
		this.frame.pack();
	}
	
	public void save(File file, String type) {
		try {
			ImageIO.write(this.canvas, type, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Color getPixel(int x, int y) {
		return new Color(this.canvas.getRGB(x, y));
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
		this.canvas.setRGB(x, y, c.getRGB());
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
	
	public void setAntiAliasing(boolean antialiasing) {
		this.antialiasing = antialiasing;
	}
	
	public void draw(Graphics2D g) {};
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void focusGained(FocusEvent e) {}
	
	@Override
	public void focusLost(FocusEvent e) {}
	
}

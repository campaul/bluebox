package bluebox.graphics;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.LinkedList;
import java.util.Map;

public class Context extends Graphics2D {

	private LinkedList<Graphics2D> stack;
	
	public Context(Graphics2D g) {
		this.stack = new LinkedList<Graphics2D>();
		this.stack.push(g);
	}
	
	public void push() {
		this.stack.push((Graphics2D)this.stack.peek().create());
	}
	
	public void pop() {
		if(stack.size() > 1)
			this.stack.pop().dispose();
	}
	
	public void setAntialiasing(boolean antialiasing) {
		if(antialiasing) {
			stack.peek().setRenderingHint(
					RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		} else {
			stack.peek().setRenderingHint(
					RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
	}
	
	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		stack.peek().addRenderingHints(hints);
	}

	@Override
	public void clip(Shape s) {
		stack.peek().clip(s);
	}

	@Override
	public void draw(Shape s) {
		stack.peek().draw(s);
	}

	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		stack.peek().drawGlyphVector(g, x, y);
	}

	@Override
	public boolean drawImage(Image image, AffineTransform xform,
			ImageObserver obs) {
		return stack.peek().drawImage(image, xform, obs);
	}

	@Override
	public void drawImage(BufferedImage image, BufferedImageOp op, int x,
			int y) {
		stack.peek().drawImage(image, op, x, y);		
	}

	@Override
	public void drawRenderableImage(RenderableImage image, AffineTransform xform) {
		stack.peek().drawRenderableImage(image, xform);
	}

	@Override
	public void drawRenderedImage(RenderedImage image, AffineTransform xform) {
		stack.peek().drawRenderedImage(image, xform);
	}

	@Override
	public void drawString(String str, int x, int y) {
		stack.peek().drawString(str, x, y);
	}

	@Override
	public void drawString(String str, float x, float y) {
		stack.peek().drawString(str, x, y);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		stack.peek().drawString(iterator, x, y);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x,
			float y) {
		stack.peek().drawString(iterator, x, y);
	}

	@Override
	public void fill(Shape s) {
		stack.peek().fill(s);
	}

	@Override
	public Color getBackground() {
		return stack.peek().getBackground();
	}

	@Override
	public Composite getComposite() {
		return stack.peek().getComposite();
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return stack.peek().getDeviceConfiguration();
	}

	@Override
	public FontRenderContext getFontRenderContext() {
		return stack.peek().getFontRenderContext();
	}

	@Override
	public Paint getPaint() {
		return stack.peek().getPaint();
	}

	@Override
	public Object getRenderingHint(Key hintKey) {
		return stack.peek().getRenderingHint(hintKey);
	}

	@Override
	public RenderingHints getRenderingHints() {
		return stack.peek().getRenderingHints();
	}

	@Override
	public Stroke getStroke() {
		return stack.peek().getStroke();
	}

	@Override
	public AffineTransform getTransform() {
		return stack.peek().getTransform();
	}

	@Override
	public boolean hit(Rectangle rectangle, Shape shape, boolean onStroke) {
		return stack.peek().hit(rectangle, shape, onStroke);
	}

	@Override
	public void rotate(double theta) {
		stack.peek().rotate(theta);
	}

	@Override
	public void rotate(double theta, double x, double y) {
		stack.peek().rotate(theta, x, y);
	}

	@Override
	public void scale(double sx, double sy) {
		stack.peek().scale(sx, sy);
	}

	@Override
	public void setBackground(Color color) {
		stack.peek().setBackground(color);
	}

	@Override
	public void setComposite(Composite composite) {
		stack.peek().setComposite(composite);
	}

	@Override
	public void setPaint(Paint paint) {
		stack.peek().setPaint(paint);
	}

	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		stack.peek().setRenderingHint(hintKey, hintValue);
	}

	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		stack.peek().setRenderingHints(hints);
	}

	@Override
	public void setStroke(Stroke stroke) {
		stack.peek().setStroke(stroke);
	}

	@Override
	public void setTransform(AffineTransform transform) {
		stack.peek().setTransform(transform);
	}

	@Override
	public void shear(double shx, double shy) {
		stack.peek().shear(shx, shy);
	}

	@Override
	public void transform(AffineTransform transform) {
		stack.peek().transform(transform);
	}

	@Override
	public void translate(int x, int y) {
		stack.peek().translate(x, y);
	}

	@Override
	public void translate(double tx, double ty) {
		stack.peek().translate(tx, ty);
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
		stack.peek().clearRect(x, y, width, height);
	}

	@Override
	public void clipRect(int x, int y, int width, int height) {
		stack.peek().clipRect(x, y, width, height);
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx,
			int dy) {
		stack.peek().copyArea(x, y, width, height, dx, dy);
	}

	@Override
	public Graphics create() {
		return stack.peek().create();
	}

	@Override
	public void dispose() {
		while(this.stack.size() > 1)
			this.stack.pop().dispose();
		this.stack.peek().dispose();
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		stack.peek().drawArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public boolean drawImage(Image image, int x, int y, ImageObserver observer) {
		return stack.peek().drawImage(image, x, y, observer);
	}

	@Override
	public boolean drawImage(Image image, int x, int y, Color bgcolor,
			ImageObserver observer) {
		return stack.peek().drawImage(image, x, y, bgcolor, observer);
	}

	@Override
	public boolean drawImage(Image image, int x, int y, int width,
			int height, ImageObserver observer) {
		return stack.peek().drawImage(image, x, y, width, height, observer);
	}

	@Override
	public boolean drawImage(Image image, int x, int y, int width,
			int height, Color bgcolor, ImageObserver observer) {
		return stack.peek().drawImage(image, x, y, width, height, bgcolor, observer);
	}

	@Override
	public boolean drawImage(Image image, int dx1, int dy1, int dx2,
			int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		return stack.peek().drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	@Override
	public boolean drawImage(Image image, int dx1, int dy1, int dx2,
			int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor,
			ImageObserver observer) {
		return stack.peek().drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		stack.peek().drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		stack.peek().drawOval(x, y, width, height);
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		stack.peek().drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		stack.peek().drawPolyline(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth,
			int arcHeight) {
		stack.peek().drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		stack.peek().fillArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
		stack.peek().fillOval(x, y, width, height);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		stack.peek().fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		stack.peek().fillRect(x, y, width, height);
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth,
			int arcHeight) {
		stack.peek().fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public Shape getClip() {
		return stack.peek().getClip();
	}

	@Override
	public Rectangle getClipBounds() {
		return stack.peek().getClipBounds();
	}

	@Override
	public Color getColor() {
		return stack.peek().getColor();
	}

	@Override
	public Font getFont() {
		return stack.peek().getFont();
	}

	@Override
	public FontMetrics getFontMetrics(Font font) {
		return stack.peek().getFontMetrics(font);
	}

	@Override
	public void setClip(Shape clip) {
		stack.peek().setClip(clip);
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		stack.peek().setClip(x, y, width, height);
	}

	@Override
	public void setColor(Color color) {
		stack.peek().setColor(color);
	}

	@Override
	public void setFont(Font font) {
		stack.peek().setFont(font);
	}

	@Override
	public void setPaintMode() {
		stack.peek().setPaintMode();
	}

	@Override
	public void setXORMode(Color color) {
		stack.peek().setXORMode(color);
	}

}

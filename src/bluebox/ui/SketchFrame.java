package bluebox.ui;

import javax.swing.JFrame;

import bluebox.core.Sketch;

@SuppressWarnings("serial")
public class SketchFrame extends JFrame {
	
	public SketchFrame(Sketch sketch) {
		super(sketch.getTitle());
		this.add(new SketchPanel(sketch));
	}
	
	public void present() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}

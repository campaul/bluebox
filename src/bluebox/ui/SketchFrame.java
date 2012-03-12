package bluebox.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import bluebox.core.Sketch;

@SuppressWarnings("serial")
public class SketchFrame extends JFrame {
	
	public SketchFrame(Sketch sketch) {
		this.setTitle(sketch.getTitle());
		
		this.add(new SketchPanel(sketch));
		
		if(sketch.getComponent("NORTH") != null)
			this.add(sketch.getComponent("NORTH"), BorderLayout.NORTH);
		if(sketch.getComponent("SOUTH") != null)
			this.add(sketch.getComponent("SOUTH"), BorderLayout.SOUTH);
		if(sketch.getComponent("EAST") != null)
			this.add(sketch.getComponent("EAST"), BorderLayout.EAST);
		if(sketch.getComponent("WEST") != null)
			this.add(sketch.getComponent("WEST"), BorderLayout.WEST);
	}
	
	public void present() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}

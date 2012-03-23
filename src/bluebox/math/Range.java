package bluebox.math;

import java.util.ArrayList;

public class Range extends ArrayList<Integer> {

	private static final long serialVersionUID = 1L;
	
	public Range(int start, int end, int increment) {
		for(int i = start; i < end; i += increment) {
			this.add(i);
		}
	}
	
	public Range(int start, int end) {
		this(start, end, 1);
	}
	
	public Range(int end) {
		this(0, end);
	}
	
}

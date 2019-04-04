/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGHT = 50;
	

	public void run() {
		/* You fill this in. */
		double x1 = getWidth()*0.5 - TILE_SPACE*0.5 - TILE_WIDTH;
		double y1 = getHeight()*0.5 - TILE_SPACE*0.5 - TILE_HEIGHT;
		double x2 = x1 + TILE_SPACE + TILE_WIDTH;
		double y2 = y1 +TILE_SPACE + TILE_HEIGHT;
		add(x1, y1);
		add(x2, y1);
		add(x1, y2);
		add(x2, y2);
		
	}
	private void add(double x, double y) {
		GRect rect = new GRect(TILE_WIDTH, TILE_HEIGHT);
		GLabel label = new GLabel("CS106");
		add(rect, x, y);
		add(label, x + (TILE_WIDTH - label.getWidth())/2, y + (TILE_HEIGHT + label.getHeight())/2);
	}
}


/*
 * File: DrawCenteredRect.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the DrawCenteredRect problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class DrawCenteredRect extends GraphicsProgram {
	
	/** Size of the centered rect */
	private static final int WIDTH = 350;
	private static final int HEIGHT = 270;

	public void run() {
		/* You fill this in. */
		double y = getHeight() / 2;
		double x = getWidth() / 2;
		GRect myRect = new GRect(WIDTH, HEIGHT);
		myRect.setCenterLocation(x, y);
		myRect.setFilled(true);
		myRect.setFillColor(Color.BLUE);
		add(myRect);
		
	}
}


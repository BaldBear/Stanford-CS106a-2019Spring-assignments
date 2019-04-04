/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		double x1 = getWidth()*0.5 - 72/2;
		double y1 = getHeight()*0.5 - 72/2;
		double x2 = getWidth()*0.5 - 72*0.65/2;
		double y2 = getHeight()*0.5 - 72*0.65/2;
		double x3 = getWidth()*0.5 - 72*0.3/2;
		double y3 = getHeight()*0.5 - 72*0.3/2;
		GOval oval1 = new GOval(72,72);
		GOval oval2 = new GOval(72*0.65,72*0.65);
		GOval oval3 = new GOval(72*0.3,72*0.3);
		oval1.setFilled(true);
		oval1.setFillColor(Color.RED);
		oval2.setFilled(true);
		oval2.setFillColor(Color.white);
		oval3.setFilled(true);
		oval3.setFillColor(Color.red);
		add(oval1, x1, y1);
		add(oval2, x2, y2);
		add(oval3, x3, y3);
	}
}

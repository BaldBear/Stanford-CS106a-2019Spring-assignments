/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		/* You fill this in. */
		int x = (int)((getWidth() - BRICKS_IN_BASE*BRICK_WIDTH)/2);
		int y = getHeight() - BRICK_HEIGHT;		
		

		//内循环要实现递减需要使用外循环的索引变量来作为判断条件
		for(int i = BRICKS_IN_BASE; i > 0; i--){
			for(int j = 0; j < i; j++) {
				GRect rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				add(rect, x + j*BRICK_WIDTH, y);
			}
			x = (int)(x + 0.5*BRICK_WIDTH);
			y = y - BRICK_HEIGHT;
		}
	}
}


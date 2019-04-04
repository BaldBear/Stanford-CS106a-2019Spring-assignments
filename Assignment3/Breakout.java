/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;

	// Number of turns 
	public static final int NTURNS = 3;

	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		/* You fill this in, along with any subsidiary methods */
		setUpTheBricks();
		setUpPaddle();
		//Initiate the paddle
		addMouseListeners();
		//Add the ball
		addTheBall();
		ballMove();
	}
	
	private void setUpTheBricks() {
		double x = (CANVAS_WIDTH - NBRICK_COLUMNS*BRICK_WIDTH - (NBRICK_COLUMNS-1)*BRICK_SEP) / 2;
		double y = BRICK_HEIGHT + BRICK_Y_OFFSET;
		for(int i =0; i < NBRICK_ROWS; i++) {
			for(int j = 0; j < NBRICK_COLUMNS; j++) {
				GRect brick =new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				switch(i){
				case 0:
				case 1:
					brick.setFillColor(Color.RED);break;
				case 2:
				case 3:
					brick.setFillColor(Color.ORANGE);break;
				case 4:
				case 5:
					brick.setFillColor(Color.YELLOW);break;
				case 6:
				case 7:
					brick.setFillColor(Color.GREEN);break;
				case 8:
				case 9:
					brick.setFillColor(Color.CYAN);break;
				}
				add(brick, x + j*(BRICK_WIDTH + BRICK_SEP), y);
			}
			y += BRICK_HEIGHT; 		
		}
	}	

	private void setUpPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}
	
	public void mouseMoved(MouseEvent e) {
		xPaddle = e.getX();
		if(xPaddle < CANVAS_WIDTH - PADDLE_WIDTH) {
			add(paddle, xPaddle, CANVAS_HEIGHT - PADDLE_Y_OFFSET);
		}
		else {
			add(paddle, CANVAS_WIDTH - PADDLE_WIDTH, CANVAS_HEIGHT - PADDLE_Y_OFFSET);
		}
		
	}
	//crater the ball
	private void addTheBall() {
		ball = new GOval(BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		add(ball, 0.5*CANVAS_WIDTH - BALL_RADIUS, 0.5*CANVAS_HEIGHT - BALL_RADIUS);	
	}
	private void ballMove() {
		// Initiating the initial velocity of the ball
		vy = 3.0;
		vx = rgen.nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
		if(rgen.nextBoolean(4))
			vx = -vx;
		waitForClick();
		//ÅÐ¶ÏÊÇ·ñ×²Ç½
		while(true) {
			if(hitLeftWall() || hitRightWall()) {
				vx = -vx;	
			}
			if(hitTheTop()) {
				vy = -vy;
			}
			ball.move(vx, vy);
			pause(DELAY);
			GObject collider = getCollidingObject();
			if(collider == paddle) {
				vy = -vy;
			}
			else if(collider != null) {
				count++;
				remove(collider);
				vy = -vy;
			}
			//check whether the ball hit the bottom wall
			if(ball.getY() + 2*BALL_RADIUS >= CANVAS_HEIGHT && deadtimes < NTURNS) {
				deadtimes++;
				remove(ball);
				addTheBall();
				}
			if(count==10) {
				vy++;
				vx++;
				count=0;
			}	
			if(deadtimes == NTURNS) {
				remove(ball);
				add(new GLabel("You lose!!", 0.5*CANVAS_WIDTH, 0.5*CANVAS_HEIGHT));
				break;
			}
					
			
		}
	}
	
	
	//check whether the ball hit the side of the canvas
	private boolean hitLeftWall() {
		return ball.getX() <= 0;
	}
	private boolean hitRightWall() {
		return ball.getX() >= CANVAS_WIDTH - 2*BALL_RADIUS;
	}
	private boolean hitTheTop() {
		return ball.getY() <= 0;
	}
	
	private GObject getCollidingObject() {
		if(getElementAt(ball.getX(), ball.getY()) != null)
			return getElementAt(ball.getX(), ball.getY());
		else if(getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null)
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
			else if(getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null)
				return getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
			else
				return(getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS));
	}
	private int count;
	private int deadtimes = 0;
	private int xPaddle;
	private GRect paddle;
	private double vx, vy;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
}

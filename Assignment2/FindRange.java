/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		/* You fill this in */
		int counter = 0;
		int max = 0;
		int min = 0;
		println("This program finds the largest and smallest bumbers");		
		while(true) {
			int temp = readInt("?");
			if(temp==SENTINEL) break;
			if(max == 0 || temp > max) {
				max = temp;
			}
			if(min == 0 || temp < min) {
				min = temp;
			}
			counter++;
		}		
		if(counter==0) {
			println("No effectiv number!!");
		}
		else if(counter==1) {
			println("Both the largest and samllest value is " + (max+min));
		}
		else {
			println("smallest:" + min);
			println("largest:" + max);
		}
	}
}


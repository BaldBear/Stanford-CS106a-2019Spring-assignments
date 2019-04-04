/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int number = readInt("Enter a number:");
		int counter = 0;
		while(true) {
			if(number == 1) break;
			else if(number%2 ==1 ) {
				number = odd(number);
			}
			else {
				number = even(number);
			}
			counter++;
		}
		println("The process took " + counter + " to reach 1.");
	}
	private int odd(int x) {
		int temp = 3*x + 1;
		println(x + " is odd, so I make 3n + 1: "+temp);
		return temp;
	}
	private int even(int x) {
		int number = x / 2;
		println(x + " is even, so I take half: "+number);
		return number;
	}
}



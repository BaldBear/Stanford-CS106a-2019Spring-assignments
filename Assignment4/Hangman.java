/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.awt.*;
import java.io.*;


public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	private GLine[] line = new GLine[N_GUESSES];
	private GLabel label1;
	private GLabel label2;
	private GImage karel;
	
	ArrayList wordList = new ArrayList<String>();
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	public void run() {
		drawBackground();
		drawKarel();
		drawParachute();
		drawLine();
		getWordList();		
		guess();
		
	}
	
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game. It randomly 
	 * selects from among 10 choices.
	 */
	private void getWordList() {
		try {
			Scanner input = new Scanner(new File("ShorterLexicon.txt"));
			while(input.hasNextLine()) {
				String line = input.nextLine();
				wordList.add(line);
			}
			input.close();
		}
		catch(IOException e) {
			println("Cannot open file.");
		}
	}
	/*
	private String getRandomWord() {
		int index = rg.nextInt(10);
		if(index == 0) return "BUOY";
		if(index == 1) return "COMPUTER";
		if(index == 2) return "CONNOISSEUR";
		if(index == 3) return "DEHYDRATE";
		if(index == 4) return "FUZZY";
		if(index == 5) return "HUBBUB";
		if(index == 6) return "KEYHOLE";
		if(index == 7) return "QUAGMIRE";
		if(index == 8) return "SLITHER";
		if(index == 9) return "ZIRCON";
		throw new ErrorException("getWord: Illegal index");
	}
	*/
	private String getRandomWord() {
		int index = rg.nextInt(wordList.size());
		return (String)(wordList.get(index));
		//throw new ErrorException("getWord: Illegal index");
	}
	
	public void init() {
		add(canvas);
	}
	//增加背景
	private void drawBackground() {
		GImage bg = new GImage("background.jpg");
		bg.setSize(canvas.getWidth(), canvas.getHeight());
		canvas.add(bg, 0, 0);
	}
	//增加Karel
	private void drawKarel() {
		karel = new GImage("karel.png");
		karel.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(karel, 0.5*(canvas.getWidth()-KAREL_SIZE), KAREL_Y);
	}
	private void drawKarelFlipped() {
		GImage karelF = new GImage("karelFlipped.png");
		karelF.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(karelF, 0.5*(canvas.getWidth()-KAREL_SIZE), KAREL_Y);
		pause(500);
		while(karelF.getY()<=canvas.getHeight()) {
			int dy = 5;
			karelF.move(0, dy);
			pause(20);
			dy+=5;
		}
		canvas.remove(karelF);
	}
	//增加降落伞
	private void drawParachute() {
		GImage para = new GImage("parachute.png");
		para.setSize(PARACHUTE_WIDTH, PARACHUTE_HEIGHT);
		canvas.add(para, 0.5*(canvas.getWidth()-PARACHUTE_WIDTH), PARACHUTE_Y);
	}
	//绘制连接线
	private void drawLine() {
		double sep = 50;
		double x = 0.5 * canvas.getWidth();
		double y = PARACHUTE_Y + PARACHUTE_HEIGHT;
		line[0] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH), y);
		line[1] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH)-6*sep, y);
		line[2] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH)-1*sep, y);
		line[3] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH)-5*sep, y);
		line[4] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH)-2*sep, y);
		line[5] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH)-4*sep, y);
		line[6] = new GLine(x, KAREL_Y, 0.5*(canvas.getWidth()+PARACHUTE_WIDTH)-3*sep, y);
		for(int i = 0; i < N_GUESSES; i++) {
			canvas.add(line[i]);
		}
	}
	
	private void addLabel(String str1, String str2) {
		label1 = new GLabel(str1);
		label2 = new GLabel(str2);
		label1.setFont(PARTIALLY_GUESSED_FONT);
		label2.setFont(INCORRECT_GUESSES_FONT);
		canvas.add(label1, 0.5*(canvas.getWidth()-label1.getWidth()), PARTIALLY_GUESSED_Y);
		canvas.add(label2, 0.5*(canvas.getWidth()-label2.getWidth()), INCORRECT_GUESSES_Y);		
	}
	
	
	private void guess() {
		String secretWord = "";
		//初始化被猜单词
		secretWord = getRandomWord();
		int numChar = secretWord.length();
		
		//用于记录单词状态
		String wordState = "";
		for(int i = 0; i < numChar; i++) {
			wordState += '-';
		}
		//记录错误次数
		int numWrong = 0;
		//记录已猜单词
		String letterGuessed = "";
		//记录字母的位置
		int index;	
		println("Wlecom to Hangman");
		addLabel(wordState, letterGuessed);
		//注意循环条件，正确或次数用完结束循环
		while(numWrong < N_GUESSES && wordState.indexOf('-') != -1) {
			println("Your word now looks like this: " + wordState);
		    println("You have "+ (N_GUESSES-numWrong) +"guessed left.");
			//读取猜测字母
			String temp = readLine("Your guess:");
			//判断输入是否是一个字母
			char guess = temp.charAt(0);
			while(temp.length() != 1 || !Character.isLetter(guess) 
					|| letterGuessed.indexOf(guess) != -1) {
				if(temp.length() != 1 || !Character.isLetter(guess) ) {
					temp = readLine("This is not a single letter, please guess again: ");
					guess = temp.charAt(0);
				}
				if(letterGuessed.indexOf(guess) != -1) {
					if(secretWord.indexOf(guess) == -1) {
						//猜过且错误
						numWrong++;
					}
				}
			}
			//没猜过,先将字母转换为大写
			guess = Character.toUpperCase(guess);
			//记录在单词中位置
			index = secretWord.indexOf(guess);
			//字母猜错			
			if(index == -1) {
				println("Threr are no "+guess+"'s in the word.");
				canvas.remove(line[numWrong]);
				numWrong++;	
				if(letterGuessed.indexOf(guess) == -1) {
					letterGuessed += guess;
				}
			}
			//字母猜对,考虑单词中存在多个该字母的情况
			else {
				println("That guess is correct.");
			}
			
			String tempWord = ""; 
			for(int i =0; i < numChar; i++) {					
				if(secretWord.charAt(i) == guess) {
					tempWord += guess;
				}
				else {
					tempWord += wordState.charAt(i);
				}
			}
			wordState = tempWord;
			canvas.remove(label1);
			canvas.remove(label2);
			addLabel(wordState, letterGuessed);
		}
		//判断是否猜对单词
		if(numWrong ==7){
			println("You lose!");
			canvas.remove(karel);
			drawKarelFlipped();
		}
		else {
			println("You win.");
			println("The word was: " + secretWord);
		}
	}
}
	

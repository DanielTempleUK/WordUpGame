package core;
import gui.GUI;
import gui.ScoresDisplay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private static BufferedReader f;
    private static LetterGen letgen = new LetterGen();
    public  static int points = 0;
    private static ArrayList<String> answers = new ArrayList<String>();
    public static GUI gui = new GUI();
    public  static int round = 0;
    public  static String latestWord;
    public  static ScoresDisplay scores = new ScoresDisplay();

    public Game(){}

    public static void main(final String[] args){
	new Game();
    }

    public static int getPoints(){
	return points;
    }

    public static void startRound(){
	round = round + 1;
	GUI.initialiseRound();
	letgen = new LetterGen();
	while(howHardCanItBe() == false)
	{
	    letgen = new LetterGen();
	}
	if(Game.round == 1){
	    GUI.setRoundText("Let's Begin! Round 1");
	}
	else if(Game.round == 5){
	    GUI.setRoundText("Last Round, Final Chance!");
	    GUI.setNextRoundText("Finish Game");
	}
	else {
	    GUI.setRoundText("ROUND " + round);
	}
	GUI.setLetter1("" + letgen.getLetter1());
	GUI.setLetter2("" + letgen.getLetter2());
	GUI.setLetter3("" + letgen.getLetter3());
    }

    public static void endRound()
    {
	GUI.nextRound.setEnabled(true);
	GUI.submit.setEnabled(false);

	if(wordExists(latestWord) == false){
	    GUI.resultText.setText("That Word Doesn't Exist. You Scored 0 Points.");
	    heresWhatYouCouldHaveWon();
	    printCouldves();
	}
	else if(lettersInOrder(latestWord) == false){
	    GUI.resultText.setText("Unfortunately, The Letters Were In The Incorrect Order. You Scored 0 Points.");
	    heresWhatYouCouldHaveWon();
	    printCouldves();
	}
	else if(latestWord.length() > 19){
	    GUI.resultText.setText("Correct! You Gained " + 3*latestWord.length() + " Points.");
	    points = points + 3*latestWord.length();
	}
	else if(latestWord.length() > 9){
	    GUI.resultText.setText("Correct! You Gained " + 2*latestWord.length() + " Points.");
	    points = points + 2*latestWord.length();
	}
	else{
	    GUI.resultText.setText("Correct! You Gained " + latestWord.length() + " Points.");
	    points = points + latestWord.length();
	}
	GUI.pointsText.setText("You Are Currently On " + points + " Points");
    }

    public static void endGame()
    {
	GUI.nextRound.setEnabled(false);
	GUI.submit.setEnabled(false);
	GUI.enterText.setText("");
	GUI.roundText.setText("Game Complete");
	GUI.lettersText.setText("");
	GUI.Letter1.setText("");
	GUI.Letter2.setText("");
	GUI.Letter3.setText("");
	GUI.resultText.setText("");
	GUI.couldveHadText.setText("");
	GUI.pointsText.setText("");
	GUI.wordBox.setText("");

	if(points == 0){
	    GUI.lettersText.setText("I'm Sorry. You Scored 0 points. You Need To Word Up!");
	}else if(points < 10){
	    GUI.lettersText.setText("Not Bad. You've Finished " + round + " Rounds With a Score Of: "+ points +" Points. Try Again So You Can Word Up");
	}else{
	    GUI.lettersText.setText("Congratulations. You've Finished " + round + " Rounds With a Score Of: "+ points +" Points.");
	}

	if(Scores.isHighScore(points)){
	    GUI.enterText.setText("So, You Got A High Score =D");
	    Game.highScores();
	}
	else{
	    GUI.enterText.setText("Unfortunately, This Wasn't Good Enough For A High Score =(");
	}
    }

    public static void highScores()
    {
	scores.newHighScore();
    }

    public static boolean wordExists(final String word){
	try{
	    f = new BufferedReader(new FileReader("MegaDictionary.txt"));
	    new Scanner(f);

	}
	catch(final FileNotFoundException e){
	    System.out.println("waah!");
	}
	try{
	    String current;
	    current = f.readLine();
	    while(current != null)
	    {
		if(word.compareToIgnoreCase(current)==0)
		{
		    f.close();
		    return true;
		}
		current = f.readLine();
	    }
	    f.close();
	    return false;
	}
	catch(final IOException e){
	    return false;
	}
    }

    public static boolean lettersInOrder(String word){
	word = word.toUpperCase();
	final char[] a = word.toCharArray();
	char letter = letgen.getLetter1();
	int i = 0;
	while(i < a.length){
	    if(letter==a[i]){
		i++;
		break;
	    }
	    if(i == a.length-1){
		return false;
	    }
	    i++;
	}

	letter = letgen.getLetter2();
	while(i < a.length){
	    if(letter==a[i]){
		i++;
		break;
	    }
	    if(i == a.length-1){
		return false;
	    }
	    i++;
	}

	letter = letgen.getLetter3();
	while(i < a.length){
	    if(letter==a[i]){
		i++;
		return true;
	    }
	    if(i == a.length-1){
		return false;
	    }
	    i++;
	}
	return false;
    }

    public static boolean howHardCanItBe(){
	String word;

	try{ //sets up filereader
	    f = new BufferedReader(new FileReader("MegaDictionary.txt"));
	    new Scanner(f);
	}
	catch(final FileNotFoundException e){
	    System.out.println("waah!");
	}
	try{
	    word = f.readLine();//gets the next line in the dictionary
	    while(word != null)
	    {
		char letter = letgen.getLetter1(); //gets first letter to test for
		char temp=Character.toLowerCase(letter);
		final char[] a = word.toCharArray(); //converts word to charArray
		final char[] test = new char[a.length];
		for(int i=0; i<a.length; i++){
		    test[i]=Character.toLowerCase(a[i]);
		}
		int i=0; //initiates loop variable
		while(i < test.length){
		    if(temp==test[i]){
			i++;
			break; //if we find letter, break out this loop and into next loop.
		    }
		    i++;
		}

		letter = letgen.getLetter2();
		temp=Character.toLowerCase(letter);
		while(i < test.length){
		    if(temp==test[i]){
			i++;
			break;
		    }
		    i++;
		}

		letter = letgen.getLetter3();
		temp=Character.toLowerCase(letter);
		while(i < test.length){
		    if(temp==test[i]){
			i++;
			f.close();
			return true; //word found with letters in right order, true returned.
		    }
		    i++;
		}
		word = f.readLine();
	    }//if no words found in entire dictionary, exits loop and returns false
	}
	catch(final IOException e){
	    System.out.println("hi");
	    return false;
	}
	return false;
    }

    public static void heresWhatYouCouldHaveWon(){
	String word;
	try{ //sets up filereader
	    f = new BufferedReader(new FileReader("MegaDictionary.txt"));
	    new Scanner(f);
	}
	catch(final FileNotFoundException e){
	    System.out.println("waah!");
	}
	try{
	    word = f.readLine();//gets the next line in the dictionary
	    while(word != null)
	    {
		char letter = letgen.getLetter1(); //gets first letter to test for

		char temp=Character.toLowerCase(letter);

		final char[] a = word.toCharArray(); //converts word to charArray
		final char[] test = new char[a.length];
		for(int i=0; i<a.length; i++){
		    test[i]=Character.toLowerCase(a[i]);
		}
		int i=0; //initiates loop variable
		while(i < a.length){
		    if(temp==test[i]){
			i++;
			break; //if we find letter, break out this loop and into next loop.
		    }
		    i++;
		}

		letter = letgen.getLetter2();
		temp=Character.toLowerCase(letter);
		while(i < a.length){
		    if(temp==test[i]){
			i++;
			break;
		    }
		    i++;
		}

		letter = letgen.getLetter3();
		temp=Character.toLowerCase(letter);
		while(i < a.length){
		    if(temp==test[i]){
			i++;
			answers.add(word);
		    }
		    i++;
		}
		word = f.readLine();
	    }//if no words found in entire dictionary, exits loop and returns false

	    f.close();
	}
	catch(final IOException e){

	}

	for(int l = 0; l < answers.size(); l++){
	    if( lettersInOrder(answers.get(l)) == false){
		answers.remove(answers.get(l));
		l--;
	    }
	}
    }

    private static void printCouldves()
    {
	final int a = answers.size();
	final String[] answersForPrint = new String[5];
	for(int i = 0; i < a && i < 5; i++){
	    final double z = Math.random();
	    answersForPrint[i] = answers.get((int)(answers.size()*z));
	    answers.remove((int)(answers.size()*z));
	}
	if(a == 1){
	    GUI.couldveHadText.setText("You Could've Had: " + answersForPrint[0] );
	}
	else if(a == 2){
	    GUI.couldveHadText.setText("You Could've Had: " + answersForPrint[0] + ", " + answersForPrint[1] );
	}
	else if(a == 3){
	    GUI.couldveHadText.setText("You Could've Had: " + answersForPrint[0] + ", " + answersForPrint[1] + ", " + answersForPrint[2] );
	}
	else if(a == 4){
	    GUI.couldveHadText.setText("You Could've Had: " + answersForPrint[0] + ", " + answersForPrint[1] + ", " + answersForPrint[2] + ", " + answersForPrint[3] );
	}
	else if(a >= 5){
	    GUI.couldveHadText.setText("You Could've Had: " + answersForPrint[0] + ", " + answersForPrint[1] + ", " + answersForPrint[2] + ", " + answersForPrint[3] + ", " + answersForPrint[4]);
	}
    }
}
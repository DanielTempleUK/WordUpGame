package core;
import gui.GUI;
import gui.ScoresDisplay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static LetterGen letGen = new LetterGen();
    public  static int points = 0;
    public  static int round = 0;
    public  static String latestWord;
    public  static ScoresDisplay scores = new ScoresDisplay();

    private static WordValidator wordValidator = new WordValidator();
    private static List<String> words = new ArrayList<String>();
    private static List<String> answers = new ArrayList<String>();
    private static GUI gui;

    public Game(){

    }

    public static void main(final String[] args){
	final Game game = new Game();
	game.initialiseWordsList();

	game.launch();
    }

    private void launch() {
	gui = new GUI();
    }

    public void initialiseWordsList() {
	try{
	    final BufferedReader reader = new BufferedReader(new FileReader("MegaDictionary.txt"));

	    String current = reader.readLine();
	    while(current != null) {
		Game.words.add(current.toLowerCase());
		current = reader.readLine();
	    }

	    reader.close();
	}
	catch(final FileNotFoundException e){
	    System.err.println("Could not find MegaDictionary file.");
	    e.printStackTrace();
	}
	catch(final IOException e){
	    System.err.println("Could not read the MegaDictionary file");
	    e.printStackTrace();
	}
    }

    public static int getPoints(){
	return points;
    }

    public static void startRound(){
	round = round + 1;
	gui.initialiseRound();
	letGen = new LetterGen();
	while(howHardCanItBe() == false)
	{
	    letGen = new LetterGen();
	}
	if(Game.round == 1){
	    gui.setRoundText("Let's Begin! Round 1");
	}
	else if(Game.round == 5){
	    gui.setRoundText("Last Round, Final Chance!");
	    gui.setNextRoundText("Finish Game");
	}
	else {
	    gui.setRoundText("ROUND " + round);
	}
	gui.setLetter1("" + letGen.getLetter1());
	gui.setLetter2("" + letGen.getLetter2());
	gui.setLetter3("" + letGen.getLetter3());
    }

    public static void endRound() {
	GUI.nextRound.setEnabled(true);
	GUI.submit.setEnabled(false);

	if(wordExists(latestWord) == false){
	    GUI.resultText.setText("That Word Doesn't Exist. You Scored 0 Points.");
	    heresWhatYouCouldHaveWon();
	    gui.updatePotentialSolutionsBar(answers);
	}
	else if(lettersInOrder(latestWord) == false){
	    GUI.resultText.setText("Unfortunately, The Letters Were In The Incorrect Order. You Scored 0 Points.");
	    heresWhatYouCouldHaveWon();
	    gui.updatePotentialSolutionsBar(answers);
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

    public static void endGame() {
	gui.endGame(points, round);

	//Handle high score scenario
	if(Scores.isHighScore(points)) {
	    GUI.enterText.setText("So, You Got A High Score =D");
	    Game.highScores();
	}
	else {
	    GUI.enterText.setText("Unfortunately, This Wasn't Good Enough For A High Score =(");
	}
    }

    public static void highScores() {
	scores.newHighScore();
    }

    public static boolean wordExists(final String word){
	return wordValidator.wordExists(word, words);
    }

    public static boolean lettersInOrder(final String word){
	return wordValidator.wordContainsLettersInOrder(letGen, word);
    }

    /**
     * Differs to finding the solutions because we return as soon as we find 1 valid word,
     * not all the valid words.
     */
    public static boolean howHardCanItBe(){
	for (final String word : words) {
	    if( wordValidator.wordContainsLettersInOrder(letGen, word) ) {
		return true;
	    }
	}
	return false;
    }

    public static void heresWhatYouCouldHaveWon(){
	answers.clear();
	answers.addAll(wordValidator.findSolutions(letGen, words));
    }
}
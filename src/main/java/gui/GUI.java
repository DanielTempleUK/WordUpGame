package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Game;

public class GUI
{
    private static JFrame frame;
    private static Container contentPane;
    public  static JLabel enterText, resultText, couldveHadText, pointsText, roundText, lettersText, Letter1, Letter2, Letter3;
    public  static TextField wordBox;
    public  static JButton submit, nextRound, scores;
    private static JPanel buttonsPanel, northernPanel, northernLettersPanel, northernTextPanel, southernTextPanel, centralPanel;

    public GUI()
    {
	makeFrame();
    }

    public void makeFrame()
    {
	frame = new JFrame("Word Up");
	frame.setLocationRelativeTo(null);
	frame.setMinimumSize(new Dimension(400, 200));
	contentPane = frame.getContentPane();

	buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new GridLayout(1,2));

	submit = new JButton("Submit");
	submit.addActionListener(new ActionListener()
	{ public void actionPerformed(final ActionEvent e)
	{ submitWord(); }
	});
	submit.setEnabled(false);

	nextRound = new JButton("Next Round");
	if(Game.round == 5){
	    nextRound.setText("Finish Game");
	}

	nextRound.addActionListener(new ActionListener(){
	    public void actionPerformed(final ActionEvent e)
	    {
		if(Game.round == 5){
		    ScoresDisplay.highScores.loadScores();
		    Game.endGame();
		}else{
		    Game.startRound();
		}
	    }
	});

	nextRound.setEnabled(false);

	buttonsPanel.add(submit);
	buttonsPanel.add(nextRound);

	centralPanel = new JPanel();
	centralPanel.setLayout(new GridLayout(3,1));

	enterText = new JLabel ("Enter Your Word :");
	enterText.setHorizontalAlignment(SwingConstants.CENTER);
	enterText.setVerticalAlignment(SwingConstants.CENTER);
	wordBox = new TextField ("",20);

	centralPanel.add(enterText);
	centralPanel.add(wordBox);
	centralPanel.add(buttonsPanel);

	northernPanel = new JPanel();
	northernPanel.setLayout(new GridLayout(2,1));

	northernLettersPanel = new JPanel();
	northernLettersPanel.setLayout(new GridLayout(1,3));
	northernLettersPanel.add(Letter1 = new JLabel(""));
	Letter1.setHorizontalAlignment(SwingConstants.CENTER);
	Letter1.setVerticalAlignment(SwingConstants.CENTER);
	northernLettersPanel.add(Letter2 = new JLabel(""));
	Letter2.setHorizontalAlignment(SwingConstants.CENTER);
	Letter2.setVerticalAlignment(SwingConstants.CENTER);
	northernLettersPanel.add(Letter3 = new JLabel(""));
	Letter3.setHorizontalAlignment(SwingConstants.CENTER);
	Letter3.setVerticalAlignment(SwingConstants.CENTER);

	northernTextPanel = new JPanel();
	northernTextPanel.setLayout(new GridLayout(2,1));
	northernTextPanel.add(roundText = new JLabel("THERE IS NO GAME RUNNING!!!"));
	roundText.setHorizontalAlignment(SwingConstants.CENTER);
	roundText.setVerticalAlignment(SwingConstants.CENTER);
	northernTextPanel.add(lettersText = new JLabel("Your Letters Are:"));
	lettersText.setHorizontalAlignment(SwingConstants.CENTER);
	lettersText.setVerticalAlignment(SwingConstants.CENTER);

	northernPanel.add(northernTextPanel);
	northernPanel.add(northernLettersPanel);

	southernTextPanel = new JPanel();
	southernTextPanel.setLayout(new GridLayout(3,1));

	couldveHadText = new JLabel ("");
	couldveHadText.setHorizontalAlignment(SwingConstants.CENTER);
	couldveHadText.setVerticalAlignment(SwingConstants.CENTER);
	pointsText = new JLabel ("You Are Currently On " + Game.getPoints() + " Points");
	pointsText.setHorizontalAlignment(SwingConstants.CENTER);
	pointsText.setVerticalAlignment(SwingConstants.CENTER);
	resultText = new JLabel("");
	resultText.setHorizontalAlignment(SwingConstants.CENTER);
	resultText.setVerticalAlignment(SwingConstants.CENTER);

	southernTextPanel.add(resultText);
	southernTextPanel.add(couldveHadText);
	southernTextPanel.add(pointsText);

	contentPane.add(northernPanel, BorderLayout.NORTH);
	contentPane.add(centralPanel, BorderLayout.CENTER);
	contentPane.add(southernTextPanel, BorderLayout.SOUTH);

	makeMenuBar(frame);
	submit.setEnabled(false);

	frame.pack();
	frame.setVisible(true);
    }

    public void makeMenuBar(final JFrame frame)
    {
	final JMenuBar menubar = new JMenuBar();
	frame.setJMenuBar(menubar);

	final JMenu fileName = new JMenu("File");
	menubar.add(fileName);

	final JMenuItem newGameItem = new JMenuItem("Start New Game");
	newGameItem.addActionListener(new ActionListener(){
	    public void actionPerformed(final ActionEvent e){
		Game.round = 0;
		Game.points = 0;
		Game.startRound();
	    }
	});
	fileName.add(newGameItem);

	final JMenuItem aboutItem = new JMenuItem("About");
	aboutItem.addActionListener(new ActionListener()
	{ public void actionPerformed(final ActionEvent e)
	{ new About(); }
	});
	fileName.add(aboutItem);

	final JMenuItem quitItem = new JMenuItem("Quit");
	quitItem.addActionListener(new ActionListener()
	{ public void actionPerformed(final ActionEvent e)
	{ quit(); }
	});
	fileName.add(quitItem);

	final JMenu viewName = new JMenu("View");
	menubar.add(viewName);

	final JMenuItem scoresItem = new JMenuItem("View High Scores");
	scoresItem.addActionListener(new ActionListener(){
	    public void actionPerformed(final ActionEvent e){
		Game.scores.displayScores();
	    }
	});
	viewName.add(scoresItem);
    }

    public void submitWord(){
	Game.latestWord = wordBox.getText();
	Game.endRound();
    }

    private void quit()
    {
	System.exit(0);
    }

    public static void initialiseRound(){
	submit.setEnabled(true);
	nextRound.setEnabled(false);
	nextRound.setText("Next Round");
	resultText.setText("");
	couldveHadText.setText("");
	wordBox.setText("");
	lettersText.setText("Your Letters Are: ");
	enterText.setText("Please Enter Your Word:");
    }

    public static void setRoundText(final String s){
	roundText.setText(s);
    }

    public static void setNextRoundText(final String s){
	nextRound.setText(s);
    }

    public static void setLetrersText(final String s){
	lettersText.setText(s);
    }

    public static void setLetter1(final String s){
	Letter1.setText(s);
    }

    public static void setLetter2(final String s){
	Letter2.setText(s);
    }

    public static void setLetter3(final String s){
	Letter3.setText(s);
    }
}
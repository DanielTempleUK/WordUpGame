package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Game;
import core.Scores;

public class ScoresDisplay
{
    private JFrame frame;
    private Container contentPane;
    public  JLabel title, label1, label2, label3, label4, label5, name1, name2, name3, name4, name5, score1, score2, score3, score4, score5;
    public static TextField wordBox;
    public static JButton submit;
    private JPanel scoresPanel;
    public static Scores highScores= new Scores();;


    public ScoresDisplay()
    {

    }

    public void displayScores()
    {
	this.frame = new JFrame("Word Up");
	this.frame.setLocationRelativeTo(null);
	this.contentPane = this.frame.getContentPane();

	highScores.loadScores();

	this.title = new JLabel("High Scores:");
	this.title.setHorizontalAlignment(SwingConstants.CENTER);

	this.scoresPanel = new JPanel();
	this.scoresPanel.setLayout(new GridLayout(5,3));

	this.label1 = new JLabel("1.");
	this.label1.setHorizontalAlignment(SwingConstants.CENTER);
	this.name1 = new JLabel("" + Scores.topScores[0][0]);
	this.name1.setHorizontalAlignment(SwingConstants.CENTER);
	this.score1 = new JLabel("" + Scores.topScores[0][1]);
	this.score1.setHorizontalAlignment(SwingConstants.CENTER);


	this.label2 = new JLabel("2.");
	this.label2.setHorizontalAlignment(SwingConstants.CENTER);
	this.name2 = new JLabel("" + Scores.topScores[1][0]);
	this.name2.setHorizontalAlignment(SwingConstants.CENTER);
	this.score2 = new JLabel("" + Scores.topScores[1][1]);
	this.score2.setHorizontalAlignment(SwingConstants.CENTER);

	this.label3 = new JLabel("3.");
	this.label3.setHorizontalAlignment(SwingConstants.CENTER);
	this.name3 = new JLabel("" + Scores.topScores[2][0]);
	this.name3.setHorizontalAlignment(SwingConstants.CENTER);
	this.score3 = new JLabel("" + Scores.topScores[2][1]);
	this.score3.setHorizontalAlignment(SwingConstants.CENTER);

	this.label4 = new JLabel("4.");
	this.label4.setHorizontalAlignment(SwingConstants.CENTER);
	this.name4 = new JLabel("" + Scores.topScores[3][0]);
	this.name4.setHorizontalAlignment(SwingConstants.CENTER);
	this.score4 = new JLabel("" + Scores.topScores[3][1]);
	this.score4.setHorizontalAlignment(SwingConstants.CENTER);

	this.label5 = new JLabel("5.");
	this.label5.setHorizontalAlignment(SwingConstants.CENTER);
	this.name5 = new JLabel("" + Scores.topScores[4][0]);
	this.name5.setHorizontalAlignment(SwingConstants.CENTER);
	this.score5 = new JLabel("" + Scores.topScores[4][1]);
	this.score5.setHorizontalAlignment(SwingConstants.CENTER);


	this.scoresPanel.add(this.label1);
	this.scoresPanel.add(this.name1);
	this.scoresPanel.add(this.score1);


	this.scoresPanel.add(this.label2);
	this.scoresPanel.add(this.name2);
	this.scoresPanel.add(this.score2);


	this.scoresPanel.add(this.label3);
	this.scoresPanel.add(this.name3);
	this.scoresPanel.add(this.score3);


	this.scoresPanel.add(this.label4);
	this.scoresPanel.add(this.name4);
	this.scoresPanel.add(this.score4);


	this.scoresPanel.add(this.label5);
	this.scoresPanel.add(this.name5);
	this.scoresPanel.add(this.score5);


	this.contentPane.add(this.title, BorderLayout.NORTH);
	this.contentPane.add(this.scoresPanel, BorderLayout.CENTER);



	this.frame.pack();
	this.frame.setVisible(true);
    }



    public void newHighScore(){
	this.frame = new JFrame("Word Up Scores");
	this.frame.setLocationRelativeTo(null);
	this.contentPane = this.frame.getContentPane();

	this.title = new JLabel("Well Done! You Got A High Score");
	this.title.setHorizontalAlignment(SwingConstants.CENTER);



	this.scoresPanel = new JPanel();
	this.scoresPanel.setLayout(new GridLayout(3,1));

	this.label1 = new JLabel("Please Enter Your Name:");
	this.label1.setHorizontalAlignment(SwingConstants.CENTER);

	wordBox = new TextField("", 20);

	submit = new JButton("Submit");
	submit.addActionListener(new ActionListener()
	{ public void actionPerformed(final ActionEvent e)
	{   final String i = ScoresDisplay.wordBox.getText();
	highScores.saveHighScore(Game.points, i);
	displayScores();
	ScoresDisplay.submit.setEnabled(false);

	}
	});

	this.scoresPanel.add(this.label1);
	this.scoresPanel.add(wordBox);
	this.scoresPanel.add(submit);




	this.contentPane.add(this.title, BorderLayout.NORTH);
	this.contentPane.add(this.scoresPanel, BorderLayout.CENTER);




	this.frame.pack();
	this.frame.setVisible(true);



    }

}
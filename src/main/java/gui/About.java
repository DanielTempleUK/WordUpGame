package gui;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.GuiUtils;

public class About {

    private final JFrame frame = new JFrame("About");;
    private final JPanel panel = GuiUtils.newEmptyBorderedPanel();
    private final JLabel gameName = new JLabel ("Game Name: Word Up");
    private final JLabel author1 = new JLabel ("Created By: Daniel Temple and Aidan Chalk");
    private final JLabel author2 = new JLabel ("Graphics Modelled By: Karl Webster");
    private final JLabel version = new JLabel ("Version: 2.2");

    public About() {
	makeFrame();
    }

    public void makeFrame() {
	initializeCenterPanel();

	this.frame.setLayout(GuiUtils.newPaddedBorderLayout());
	this.frame.add(this.panel, BorderLayout.CENTER);

	this.frame.pack();
	this.frame.setLocationRelativeTo(null);
	this.frame.setVisible(true);
    }

    private void initializeCenterPanel() {
	this.panel.setLayout(GuiUtils.newPaddedGridLayout(4, 1));
	this.panel.add(this.gameName);
	this.panel.add(this.author1);
	this.panel.add(this.author2);
	this.panel.add(this.version);
    }
}
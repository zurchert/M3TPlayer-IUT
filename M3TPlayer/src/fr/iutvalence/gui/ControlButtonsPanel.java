package fr.iutvalence.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlButtonsPanel extends JPanel{
	
	private JButton playButton;
	
	private JButton nextButton;
	
	private JButton previousButton;
	
	private JButton randomButton;

	
	
	public ControlButtonsPanel(ActionListener listener) {
		super();
		this.playButton = new JButton("Play");
		this.nextButton = new JButton("Next");
		this.previousButton = new JButton("Previous");
		this.randomButton = new JButton("Random");
		
		this.add(this.previousButton);
		this.add(this.playButton);
		this.add(this.nextButton);
		this.add(this.randomButton);
		
		this.playButton.addActionListener(listener);
		this.nextButton.addActionListener(listener);
		this.previousButton.addActionListener(listener);
		this.randomButton.addActionListener(listener);
	}


	/**
	 * @return the playButton
	 */
	public JButton getPlayButton() {
		return this.playButton;
	}

	/**
	 * @return the nextButton
	 */
	public JButton getNextButton() {
		return this.nextButton;
	}

	/**
	 * @return the previousButton
	 */
	public JButton getPreviousButton() {
		return this.previousButton;
	}

	/**
	 * @return the randomButton
	 */
	public JButton getRandomButton() {
		return this.randomButton;
	}
	
	
}

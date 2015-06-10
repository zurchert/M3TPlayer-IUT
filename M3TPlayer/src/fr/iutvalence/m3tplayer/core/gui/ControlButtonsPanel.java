package fr.iutvalence.m3tplayer.core.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class contains the controller buttons
 * used to interact with the player
 */
public class ControlButtonsPanel extends JPanel{
	
	/**
	 * button to play the music
	 */
	private JButton playButton;
	
	/**
	 * the button to go to the next music
	 */
	private JButton nextButton;
	
	/**
	 * the button to go to the previous music
	 */
	private JButton previousButton;
	
	/**
	 * the button to get the mode random
	 */
	
	private JButton randomButton;
	
	/**
	 * the button to import some music
	 */

	private JButton importButton;
	
	/**
	 * the button to go to stop the playingmusic
	 */
	
	private JButton stopButton;
	
	
	/**
	 * actionlistener to gives the role of any buttons
	 * @param listener
	 */
	
	public ControlButtonsPanel(ActionListener listener) {
		super();
		this.playButton = new JButton("Play");
		this.nextButton = new JButton("Next");
		this.previousButton = new JButton("Previous");
		this.randomButton = new JButton("Random");
		this.importButton = new JButton("Import");	
		this.stopButton = new JButton("Stop");
		
		this.add(this.importButton);
		this.add(this.previousButton);
		this.add(this.playButton);
		this.add(this.nextButton);
		this.add(this.stopButton);
		this.add(this.randomButton);
		
		this.importButton.addActionListener(listener);
		this.playButton.addActionListener(listener);
		this.nextButton.addActionListener(listener);
		this.previousButton.addActionListener(listener);
		this.randomButton.addActionListener(listener);
		this.stopButton.addActionListener(listener);
	}


	/**
	 * @return the stopButton
	 */
	public JButton getStopButton() {
		return this.stopButton;
	}

	/**
	 * @return the importButton
	 */
	public JButton getImportButton() {
		return this.importButton;
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

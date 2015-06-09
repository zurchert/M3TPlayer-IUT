package fr.iutvalence.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * class to the control of different buttons
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
	 * the button to validate the importation
	 */
	
	private JButton validButton;
	
	/**
	 * the button to go to stop the playingmusic
	 */
	
	private JButton stopButton;
	
	private String nameButtonPlay;
	
	/**
	 * actionlistener to gives the role of any buttons
	 * @param listener
	 */
	
	public ControlButtonsPanel(ActionListener listener) {
		super();
		this.nameButtonPlay = "Play";
		this.playButton = new JButton(this.nameButtonPlay);
		this.nextButton = new JButton("Next");
		this.previousButton = new JButton("Previous");
		this.randomButton = new JButton("Random");
		this.importButton = new JButton("Import");
		this.validButton = new JButton("OK");	
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
		this.validButton.addActionListener(listener);
		this.stopButton.addActionListener(listener);
	}


	/**
	 * @param nameButtonPlay the nameButtonPlay to define
	 */
	public void setNameButtonPlay(String nameButtonPlay) {
		this.nameButtonPlay = nameButtonPlay;
	}


	/**
	 * @return the stopButton
	 */
	public JButton getStopButton() {
		return stopButton;
	}


	/**
	 * @return the validButton
	 */
	public JButton getValidButton() {
		return validButton;
	}


	/**
	 * @return the importButton
	 */
	public JButton getImportButton() {
		return importButton;
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

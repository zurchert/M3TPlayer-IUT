package fr.iutvalence.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlButtonsPanel extends JPanel{
	
	private JButton playButton;
	
	private JButton nextButton;
	
	private JButton previousButton;
	
	private JButton randomButton;

	private JButton importButton;
	
	private JButton validButton;
	
	public ControlButtonsPanel(ActionListener listener) {
		super();
		this.playButton = new JButton("Play");
		this.nextButton = new JButton("Next");
		this.previousButton = new JButton("Previous");
		this.randomButton = new JButton("Random");
		this.importButton = new JButton("Import");
		this.validButton = new JButton("OK");	
		
		this.add(this.importButton);
		this.add(this.previousButton);
		this.add(this.playButton);
		this.add(this.nextButton);
		this.add(this.randomButton);
		
		this.importButton.addActionListener(listener);
		this.playButton.addActionListener(listener);
		this.nextButton.addActionListener(listener);
		this.previousButton.addActionListener(listener);
		this.randomButton.addActionListener(listener);
		this.validButton.addActionListener(listener);
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
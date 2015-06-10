package fr.iutvalence.m3tplayer.core.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * A status bar which can display message during a given delay
 */
public class StatusBar extends JLabel{

	/**
	 * The default message to display
	 */
	private final static String DEFAULT_CLEAR_MESSAGE = "Status: ";
	
	/**
	 * The ready message
	 */
	private final static String MESSAGE_READY = DEFAULT_CLEAR_MESSAGE + "Ready";
	
	/**
	 * The delay to display the message
	 */
	private final static int DELAY = 2000;
	
	public StatusBar(){
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setText(StatusBar.MESSAGE_READY);
	}
	
	private ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			StatusBar.this.setText(MESSAGE_READY);
		}
	};
	
	/**
	 * Display a message during the default delay and set the default text back.
	 * @param msg The message to display
	 */
	public void displayMessage(String msg){
		this.setText(DEFAULT_CLEAR_MESSAGE + msg);
		new Timer(StatusBar.DELAY, this.taskPerformer).start();
	}
	
	/**
	 * Display a message during a given delay and set the default text back.
	 * @param msg The message to display
	 * @param delay The delay to display (in milliseconds)
	 */
	public void displayMessage(String msg, int delay){
		this.setText(DEFAULT_CLEAR_MESSAGE + msg);
		new Timer(DELAY, this.taskPerformer).start();
	}
}

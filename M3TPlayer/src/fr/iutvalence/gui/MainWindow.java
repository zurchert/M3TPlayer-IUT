package fr.iutvalence.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import enumerations.PlayerControl;
import fr.iutvalence.m3tplayer.M3TPlayer;

public class MainWindow extends JFrame implements ActionListener, Runnable{
	
	private static final String APP_TITLE = "M3TPlayer";

	private ControlButtonsPanel controllButtonsPanel;
	
	private M3TPlayer m3tPlayer;
	
	public MainWindow(){
		this.m3tPlayer = new M3TPlayer();
		Thread m3tThread = new Thread(this.m3tPlayer);

		this.setTitle(APP_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.controllButtonsPanel = new ControlButtonsPanel(this);
		
		this.getContentPane().add(this.controllButtonsPanel);
		
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent source = (JComponent) e.getSource();
		Thread m3tThread = new Thread(this.m3tPlayer);
		if(source.equals(this.controllButtonsPanel.getPlayButton())){
			m3tThread.start();
		}
		
		if(source.equals(this.controllButtonsPanel.getNextButton())){

		}
		
		if(source.equals(this.controllButtonsPanel.getPreviousButton())){
			// TODO Previous
		}
		
		if(source.equals(this.controllButtonsPanel.getRandomButton())){
			// TODO Rand
		}
	}

	@Override
	public void run() {
		this.setVisible(true);
	}
	
}

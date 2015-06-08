package fr.iutvalence.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements ActionListener, Runnable{
	
	private static final String APP_TITLE = "M3TPlayer";

	private ControlButtonsPanel controllButtonsPanel;
	
	public MainWindow(){
		
		this.setTitle(APP_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.controllButtonsPanel = new ControlButtonsPanel(this);
		
		this.getContentPane().add(this.controllButtonsPanel);
		
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent source = (JComponent) e.getSource();
		
		if(source.equals(this.controllButtonsPanel.getPlayButton())){
			// TODO PLay
		}
		
		if(source.equals(this.controllButtonsPanel.getNextButton())){
			// TODO Next
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

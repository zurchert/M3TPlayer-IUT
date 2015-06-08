package fr.iutvalence.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import enumerations.PlayerControl;
import fr.iutvalence.m3tplayer.M3TPlayer;
import fr.iutvalence.m3tplayer.Music;

public class MainWindow extends JFrame implements ActionListener, Runnable{
	
	private static final String APP_TITLE = "M3TPlayer";

	private ControlButtonsPanel controllButtonsPanel;
	
	private M3TPlayer m3t = new M3TPlayer();
	
	private JFrame frame = new JFrame();
	
	private JTextArea titleText = new JTextArea();
	
	private JTextArea pathText = new JTextArea();
	
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
		
		if(source.equals(this.controllButtonsPanel.getImportButton())){
			frame.setSize(1100, 100);
			frame.setTitle("Import music");
			frame.setLayout(new GridLayout(1, 9));
			JLabel title = new JLabel("Title :");
			frame.add(title);
			frame.add(this.titleText);
			JLabel path = new JLabel("Path of the media :");
			frame.add(path);
			frame.add(this.pathText);
			JLabel album = new JLabel("Name of the album :");
			frame.add(album);
			JTextPane albumText = new JTextPane();
			frame.add(albumText);
			JLabel artist = new JLabel("Name of the artist :");
			frame.add(artist);
			JTextPane artistText = new JTextPane();
			frame.add(artistText);
			frame.add(this.controllButtonsPanel.getValidButton());
			frame.setVisible(true);
		}
		
		if(source.equals(this.controllButtonsPanel.getValidButton())){
			m3t.getLibrary().importMedia(new Music(this.titleText.getText(), this.pathText.getText(), 10.), true);
		}
		
		if(source.equals(this.controllButtonsPanel.getPlayButton())){
			m3t = new M3TPlayer();
			m3t.playMedia();
		}
		
		if(source.equals(this.controllButtonsPanel.getNextButton())){
			m3t.changeMedia(PlayerControl.NEXT);
		}
		
		if(source.equals(this.controllButtonsPanel.getPreviousButton())){
			m3t.changeMedia(PlayerControl.PREVIOUS);
		}
		
		if(source.equals(this.controllButtonsPanel.getRandomButton())){
			m3t.setRandomPlaying();
		}
	}

	@Override
	public void run() {
		this.setVisible(true);
	}
	
}

package fr.iutvalence.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import enumerations.PlayerControl;
import fr.iutvalence.m3tplayer.M3TPlayer;
import fr.iutvalence.m3tplayer.Music;

public class MainWindow extends JFrame implements ActionListener, Runnable{
	
	private static final String APP_TITLE = "M3TPlayer";

	private ControlButtonsPanel controllButtonsPanel;
	
	private M3TPlayer m3t;
	
	private JFrame frame;
	
	private StatusBar statusBar = new StatusBar();
	
	private MusicListPanel musicListPanel;
	
	public MainWindow(){
		
		this.setTitle(APP_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.m3t = new M3TPlayer();
		
		this.frame = new JFrame();
		this.musicListPanel = new MusicListPanel(this.m3t.getLibrary().getListMedias());
		
		this.controllButtonsPanel = new ControlButtonsPanel(this);
		this.getContentPane().add(this.controllButtonsPanel, BorderLayout.NORTH);
		this.getContentPane().add(this.musicListPanel, BorderLayout.CENTER);
		this.getContentPane().add(this.statusBar, BorderLayout.SOUTH);
		
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent source = (JComponent) e.getSource();
		
		if(source.equals(this.controllButtonsPanel.getImportButton())){
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio File", "mp3", "wav");
			fileChooser.setFileFilter(filter);
			
			int returnVal = fileChooser.showOpenDialog(this);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	/* TODO Add path parser which returns a Media object ready to use
		    	 *    - title
		    	 *    - path
		    	 *    - artist
		    	 *    - album
		    	 *    - lenght
		    	 */
		    	this.m3t.getLibrary().importMedia(new Music("music", fileChooser.getSelectedFile().getPath(), 10.), true);

		    	this.statusBar.displayMessage("The file has been correctly imported");
		    }
		}
		
		if(source.equals(this.controllButtonsPanel.getPlayButton())){
			new Thread() {
				public void run() {
					MainWindow.this.m3t.playMedia();
				}
			}.start();
			
		}
		
		if(source.equals(this.controllButtonsPanel.getNextButton())){
			this.m3t.changeMedia(PlayerControl.NEXT);
		}
		
		if(source.equals(this.controllButtonsPanel.getPreviousButton())){
			this.m3t.changeMedia(PlayerControl.PREVIOUS);
		}
		
		if(source.equals(this.controllButtonsPanel.getRandomButton())){
			this.m3t.setRandomPlaying();
		}
	}
	
	@Override
	public void run() {
		this.setVisible(true);
	}
	
}

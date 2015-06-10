package fr.iutvalence.m3tplayer.core.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.iutvalence.m3tplayer.core.M3TPlayer;
import fr.iutvalence.m3tplayer.core.enumerations.PlayerControl;

/**
 * The main window of the M3TPlayer
 */
public class MainWindow extends JFrame implements ActionListener, Runnable, MouseListener{
	
	/**
	 * The application's name
	 */
	private static final String APP_TITLE = "M3TPlayer";

	/**
	 * The control button panel
	 */
	private ControlButtonsPanel controllButtonsPanel;
	
	/**
	 * The M3TPlayer
	 */
	private M3TPlayer m3t;
	
	/**
	 * The thread used to controll the player
	 */
	private Thread thread;
	
	/**
	 * The status bar
	 */
	private StatusBar statusBar;
	
	/**
	 * The music list panel
	 */
	private MusicListPanel musicListPanel;
	
	/**
	 * Check if the media is started
	 */
	private boolean isStarted;
	
	public MainWindow(){
		
		this.setTitle(APP_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.m3t = new M3TPlayer();
		this.isStarted = false;
		this.musicListPanel = new MusicListPanel(this.m3t.getLibrary().getListMedias(), this);
		this.statusBar = new StatusBar();
		
		this.controllButtonsPanel = new ControlButtonsPanel(this);
		this.getContentPane().add(this.controllButtonsPanel, BorderLayout.NORTH);
		this.getContentPane().add(this.musicListPanel, BorderLayout.CENTER);
		this.getContentPane().add(this.statusBar, BorderLayout.SOUTH);
		
		this.getContentPane().addMouseListener(this);
		
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
		    	this.m3t.getLibrary().importMedia(new File(fileChooser.getSelectedFile().getPath()).getPath());

		    	this.musicListPanel = new MusicListPanel(this.m3t.getLibrary().getListMedias(), this);
		    	this.getContentPane().add(this.musicListPanel, BorderLayout.CENTER);
		    	
		    	this.statusBar.displayMessage("The file has been correctly imported");
		    }
		}
		
		if(source.equals(this.controllButtonsPanel.getPlayButton())){
			if(this.isStarted)
				this.play();
			else{
				this.m3t = new M3TPlayer();
				this.isStarted = true;
				this.play();
			}
		}
		
		if(source.equals(this.controllButtonsPanel.getNextButton())){	
			if(this.m3t.isPlaying() || this.m3t.isPausing()){
				this.thread.stop();
				this.m3t.changeMedia(PlayerControl.NEXT);
				this.thread = new Thread() {
					public void run() {
						MainWindow.this.m3t.playMedia();				
					}
				};
	
				this.thread.start();
				this.setMediaTitle();
			}
		}
		
		if(source.equals(this.controllButtonsPanel.getPreviousButton())){
			if(this.m3t.isPlaying() || this.m3t.isPausing()){
				this.thread.stop();
				this.m3t.changeMedia(PlayerControl.PREVIOUS);
				this.thread = new Thread() {
					public void run() {
						MainWindow.this.m3t.playMedia();				
					}
				};
				this.thread.start();
				this.setMediaTitle();
			}
		}
		
		if(source.equals(this.controllButtonsPanel.getStopButton())){
			this.stop();
			this.m3t = new M3TPlayer();
		}
		
		if(source.equals(this.controllButtonsPanel.getRandomButton())){
			this.m3t.setRandomPlaying();
			this.setMediaTitle();
		}
	}
	
	public void setMediaTitle(){
		this.setTitle(APP_TITLE + " | " + this.m3t.getCurrentMedia().getTitle());
	}
	
	public void play(){
		if(!this.m3t.isPlaying()){
			this.thread = new Thread() {
				public void run() {
					MainWindow.this.m3t.playMedia();				
				}
			};
			this.thread.start();
			this.m3t.setPlaying(true);

		}
		else {
			this.thread.stop();
			this.m3t.setPlaying(false);
			this.m3t.setPausing(true);
		}
		this.setMediaTitle();
	}
	
	public void stop(){
		if (this.m3t.isPlaying()){
			this.thread.stop();
			MainWindow.this.m3t.setCurretnMedia(MainWindow.this.m3t.getLibrary().getMedia(0));
			this.m3t.setPlaying(false);
			this.m3t.setPausing(false);
		}
	}
	
	@Override
	public void run() {
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent event) {		
		if(event.getClickCount() == 2){
			JTable target = (JTable) event.getSource();
			int row = target.rowAtPoint(event.getPoint());
			this.stop();
			this.m3t.setCurretnMedia(this.m3t.getLibrary().getMedia(row));
			this.play();
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
	}
}

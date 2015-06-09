package fr.iutvalence.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import enumerations.PlayerControl;
import fr.iutvalence.m3tplayer.M3TPlayer;
import fr.iutvalence.m3tplayer.Music;

public class MainWindow extends JFrame implements ActionListener, Runnable{
	
	private static final String APP_TITLE = "M3TPlayer";

	private ControlButtonsPanel controllButtonsPanel;
	
	private M3TPlayer m3t = new M3TPlayer();
	
	private JFrame frame = new JFrame();
	
	private JLabel statusBar = new JLabel("State: Ready");

	
	public MainWindow(){
		
		this.setTitle(APP_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.statusBar.setBorder(BorderFactory.createEtchedBorder());
		
		this.controllButtonsPanel = new ControlButtonsPanel(this);
		this.getContentPane().add(this.controllButtonsPanel);
		this.getContentPane().add(this.statusBar, BorderLayout.SOUTH);
		
		this.pack();
	}
	
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			MainWindow.this.statusBar.setText("Status: Ready");
		}
	};
	
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

		    	int delay = 2000; //milliseconds
		    	Timer timer = new Timer(delay, this.taskPerformer);
		    	timer.start();
		    	
		    	this.statusBar.setText(this.statusBar.getText() + " The file has been correctly imported");
		    }
		}
		
		if(source.equals(this.controllButtonsPanel.getPlayButton())){
			
			this.controllButtonsPanel.getPlayButton().addActionListener(
					  new ActionListener() {
					    

						public void actionPerformed(ActionEvent e) {
					      // création d'un thread d'exécution
					    
					      Thread t = new Thread() {
					        public void run() {
					          // Instanciation et lancement du traitement
					        	M3TPlayer m3t = new M3TPlayer();
					        	m3t.playMedia();
					        }
					      };
					      t.start();
					    }
					  }
					);
			//this.m3t.playMedia();
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

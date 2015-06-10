package fr.iutvalence.m3tplayer.core.gui;

import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.iutvalence.m3tplayer.core.Media;
import fr.iutvalence.m3tplayer.core.Music;

/**
 * Panel which contains the list of the imported media.
 * Medias are stored into a table
 */
public class MusicListPanel extends JScrollPane{

	/**
	 * The media list table
	 */
	private JTable mediaTable;
	
	/**
	 * The data in the list
	 */
	private Object[][] data;
	
	/**
	 * The list of medias
	 */
	private Map<Integer, Media> medias;
	
	public MusicListPanel(Map<Integer, Media> medias, MouseListener listener) {
		super();
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
		this.medias = medias;
		String[] headers = {"Title", "Album", "Artist", "Lenght", "Rating"};
		this.parseMediaList();
		this.mediaTable = new JTable(this.data, headers);
		this.add(this.mediaTable);
		this.setViewportView(this.mediaTable);
		this.mediaTable.addMouseListener(listener);
	}
	
	/**
	 * Parses the media list and put the right elements into the table
	 */
	private void parseMediaList(){
		
		int line = 0;
		Iterator<Media> iterator = this.medias.values().iterator();
		
		this.data = new Object[this.medias.size()][5];
		
		while(iterator.hasNext()){

			Music music = (Music) iterator.next();
			
			this.data[line][0] = music.getTitle();
			String album = music.getAlbum();
			String artist = music.getArtist();
			if(music.getAlbum() == "")
				album = "Unknow";
			if(music.getArtist() == null)
				artist = "Unknow";
			this.data[line][1] = album;
			this.data[line][2] = artist;
			this.data[line][3] = music.getRating();
			
			line++;
		}
	}

	/**
	 * @return the mediaTable
	 */
	public JTable getMediaTable() {
		return this.mediaTable;
	}

}

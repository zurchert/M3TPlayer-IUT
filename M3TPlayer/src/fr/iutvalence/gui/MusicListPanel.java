package fr.iutvalence.gui;

import java.util.Iterator;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.iutvalence.m3tplayer.Media;
import fr.iutvalence.m3tplayer.Music;

public class MusicListPanel extends JScrollPane{

	private JTable mediaTable;
	
	private Object[][] data;
	
	private Map<Integer, Media> medias;
	
	public MusicListPanel(Map<Integer, Media> medias) {
		super();
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
		this.medias = medias;
		String[] headers = {"Title", "Album", "Artist", "Lenght", "Rating"};
		this.parseMediaList();
		this.mediaTable = new JTable(this.data, headers);
		this.add(this.mediaTable);
		this.setViewportView(this.mediaTable);
	}
	
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
}

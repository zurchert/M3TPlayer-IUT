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
		String[] headers = {"Title", "Path", "Lenght", "Rating"};
		this.parseMediaList();
		this.mediaTable = new JTable(this.data, headers);
		this.add(this.mediaTable);
		this.setViewportView(this.mediaTable);
	}
	
	private void parseMediaList(){
		
		int line = 0;
		Iterator<Media> iterator = this.medias.values().iterator();
		
		this.data = new Object[this.medias.size()][4];
		
		while(iterator.hasNext()){
			Media media = (Media) iterator.next();
			this.data[line][0] = media.getTitle();
			this.data[line][1] = media.getPath();
			if(media.getClass().equals(Music.class)){
				Music music = (Music) media;
				this.data[line][2] = music.getLenght();
				this.data[line][3] = music.getRating();
			}
			else{
				this.data[line][2] = null;
				this.data[line][3] = null;
			}
			line++;
		}
	}
}

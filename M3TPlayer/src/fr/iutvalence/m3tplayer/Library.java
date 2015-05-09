package fr.iutvalence.m3tplayer;

import java.util.HashMap;
import java.util.Map;

public class Library {
	
	/**
	 * The list of the imported music.
	 * Music are linked with their unique ID
	 */
	private Map<Integer, Radio> listRadios;

	/**
	 * The list of the imported music.
	 * Music are linked with their unique ID
	 */
	protected Map<Integer, Music> listMusics;
	
	/**
	 * The number of music which were imported into the library
	 */
	protected int importedMusicNumber;
	
	/**
	 * The sum of the lenght of all musics imported into the library.
	 */
	protected float totalMusicLenght;
	
	/**
	 * Initializes the library.
	 * At this moment, the library is empty.
	 */
	public Library() {
		this.listRadios = new HashMap<Integer, Radio>();
		this.listMusics = new HashMap<Integer, Music>();
		this.totalMusicLenght = 0;
		this.importedMusicNumber = 0;
	} 
	
	/**
	 * Import a media into the library.
	 */
	public void importMedia(){
		// TODO complete
	}
}

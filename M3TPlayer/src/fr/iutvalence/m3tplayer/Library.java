package fr.iutvalence.m3tplayer;

import java.util.HashMap;
import java.util.Map;

public class Library {
	
	/**
	 * The list of the imported medias.
	 * Medias are linked with their unique ID
	 */
	private Map<Integer, Media> listMedia;

	/**
	 * Initializes the library.
	 * At this moment, the library is empty.
	 */
	public Library() {
		this.listMedia = new HashMap<Integer, Media>();
	} 
	
}

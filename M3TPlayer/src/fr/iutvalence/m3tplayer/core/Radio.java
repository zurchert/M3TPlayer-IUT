package fr.iutvalence.m3tplayer.core;


/**
 * Represents a Radio station
 */
public class Radio extends Media{
	
	/**
	 * Creates a new Radio
	 * @param title The title of the radio
	 * @param url The url of the radio stream.
	 */
	public Radio(String title, String url) {
		super(title, url);
	}
}

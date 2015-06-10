package fr.iutvalence.m3tplayer.core.enumerations;


/**
 *  Enumeration which gives informations about the music
 */
public enum MediaInformation {

	/**
	 * The media's title
	 */
	TITLE("title"),
	
	/**
	 * The music's artist
	 */
	ARTIST("artist"),
	
	/**
	 * The music's album
	 */
	ALBUM("album"),
	
	/**
	 * The media's path
	 */
	PATH("path"),
	
	/**
	 * The music's rating
	 */
	RATING("rating");
	
	/**
	 * The name of the enum
	 */
	private String name;

	private MediaInformation(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}

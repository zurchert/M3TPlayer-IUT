package fr.iutvalence.m3tplayer;

import java.io.File;

public class Music extends Media{
	
	/**
	 * The music's artist
	 */
	private String artist;
	
	/**
	 * The music's album
	 */
	private String album;
	
	/**
	 * The music's lenght in seconds
	 */
	private final double lenght;
	
	/**
	 * The music's rating.
	 * The rating value is between 0 and 5.
	 */
	private int rating;
	
	/**
	 * The music's path
	 */
	private File path;
	
	/**
	 * Creates a new Music without giving artist and album.
	 * By default the rating is set to 0
	 * @param id     The id of the music
	 * @param title  The title of the music
	 * @param path   The path of the music on the hard-drive
	 * @param lenght The lenght of the music
	 * @see fr.iutvalence.m3tplayer.Media
	 */
	public Music(int id, String title, String path, double lenght) {
		super(id, title);
		this.path = new File(path);
		this.album = null;
		this.artist = null;
		this.lenght = lenght;
		this.rating = 0;
	}

	/**
	 * Creates a new Music giving all informations such as artist, album etc.
	 * By default the rating is set to 0.
	 * @param id     The id of the music
	 * @param title  The title of the music
	 * @param path   The path of the music on the hard-drive
	 * @param lenght The lenght of the music
	 * @param artist The artist of the music
	 * @param album  The album of the music
	 * @see fr.iutvalence.m3tplayer.Media
	 */
	public Music(int id, String title, String path, long lenght, String artist, String album) {
		super(id, title);
		this.path = new File(path);
		this.artist = artist;
		this.album = album;
		this.lenght = lenght;
		this.rating = 0;
	}

	/**
	 * @return The artist
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * Set a specified artist name to the music
	 * @param artist The artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return The album
	 */
	public String getAlbum() {
		return this.album;
	}

	/**
	 * Set a specified album name to the music
	 * @param album The album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return The rating of the music
	 */
	public int getRating() {
		return this.rating;
	}

	/**
	 * Set a specified rating to the music
	 * @param rating The rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return The lenght of the music
	 */
	public double getLenght() {
		return this.lenght;
	}
	
	/**
	 * @return The path of the music
	 */
	public File getPath() {
		return path;
	}

	/**
	 * @param path The path to set
	 */
	public void setPath(File path) {
		this.path = path;
	}

	/**
	 * Returns a String representation of the Music object.
	 * It returns all the informations about the music.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Music "      + super.id     + "\n");
		str.append("\t Title: "  + super.title  + "\n");
		str.append("\t Path: "   + this.path.getPath()  + "\n");
		str.append("\t Artist: " + this.artist  + "\n");
		str.append("\t Album: "  + this.album   + "\n");
		str.append("\t Rating: " + this.rating  + "\n");
		str.append("\t Lenght: " + this.lenght  + "\n");
		
		return str.toString();
	}
	
}

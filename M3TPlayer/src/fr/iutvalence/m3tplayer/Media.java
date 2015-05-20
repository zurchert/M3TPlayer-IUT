package fr.iutvalence.m3tplayer;

import java.io.InputStream;

public class Media {

	/**
	 * The media's id
	 */
	protected final int id;
	
	/**
	 * The media's name
	 */
	protected String title;

	/**
	 * 
	 */
	protected InputStream stream;
	
	/**
	 * Creates a new Media with given data
	 * @param id The media's id
	 * @param title The media's name
	 */
	public Media(int id, String title) {
		this.id = id;
		this.title = title;
	}

	/**
	 * @return the stream
	 */
	public InputStream getStream() {
		return stream;
	}

	/**
	 * @param stream the stream to set
	 */
	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	
	// TODO Check if the ID is not in the Map<int, Media> attribute of the Library class
}

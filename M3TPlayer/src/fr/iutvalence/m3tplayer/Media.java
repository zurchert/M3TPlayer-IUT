package fr.iutvalence.m3tplayer;

import java.io.InputStream;

public class Media {
	
	/**
	 * The media's name
	 */
	protected String title;

	/**
	 * The media's path
	 */
	protected String path;
	
	/**
	 * The media's stream
	 */
	protected InputStream stream;
	
	/**
	 * Creates a new Media with given data
	 * @param title The media's title
	 * @param path The media's path
	 */
	public Media(String title, String path) {
		this.title = title;
		this.path = path;
	}

	/**
	 * @return the stream
	 */
	public InputStream getStream() {
		return this.stream;
	}

	/**
	 * @param stream the stream to set
	 */
	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	

	
	// TODO Check if the ID is not in the Map<int, Media> attribute of the Library class
}

package fr.iutvalence.m3tplayer;

public class Media {

	/**
	 * The media's id
	 */
	protected int id;
	
	/**
	 * The media's name
	 */
	protected String title;

	
	/**
	 * Creates a new Media with given data
	 * @param id The media's id
	 * @param title The media's name
	 */
	public Media(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
}

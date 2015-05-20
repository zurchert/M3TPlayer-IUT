package fr.iutvalence.m3tplayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class M3TPlayer {

	/**
	 * The volume of the player.
	 * Value is between 0 and 100.
	 */
	private int volume;
	
	/**
	 * Random playing mod
	 */
	private boolean randomPlaying;
	
	/**
	 * The current media
	 */
	private Media currentMedia;

	/**
	 * The library
	 */
	private Library library;
	
	/**
	 * 
	 */
	private AdvancedPlayer player;
	
	/**
	 * Initializes the player with default values.
	 * Sets the volume to 100 percent, and the random mode to false.
	 */
	public M3TPlayer() {
		this.volume = 100;
		this.randomPlaying = false;
		this.currentMedia = null;
		this.player = null;
		this.library = new Library();
	}
	
	/**
	 * 
	 * @param mediaID
	 */
	public void playMedia(int mediaID){
		// TODO complete method
		Media mediaToPlay = this.library.getListMedias().get(mediaID);
		try {
			this.player = new AdvancedPlayer(mediaToPlay.getStream());
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		
	}
	
}

package fr.iutvalence.m3tplayer;

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
	 * Initializes the player with default values.
	 * Sets the volume to 100 percent, and the random mode to false.
	 */
	public M3TPlayer() {
		this.volume = 100;
		this.randomPlaying = false;
	}
	
}

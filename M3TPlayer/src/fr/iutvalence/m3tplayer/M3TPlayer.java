package fr.iutvalence.m3tplayer;

import java.util.Random;

import javax.sound.sampled.FloatControl;

import javazoom.jl.decoder.Equalizer.EQFunction;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class M3TPlayer{

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
	 * The audio player
	 */
	private AdvancedPlayer player;
	
	/**
	 * The identifiant of the music
	 */
	private int id;
	
	/**
	 * Initializes the player with default values.
	 * Sets the volume to 100 percent, and the random mode to false, and the identifiant to 0.
	 */
	public M3TPlayer() {
		this.volume = 100;
		this.randomPlaying = false;
		this.currentMedia = null;
		this.player = null;
		this.library = new Library();
		if (this.randomPlaying == false)
			this.id = 0;
	}
	
	/**
	 * Give a random identifiant
	 */
	public void activateRandomPlaying(){
		int size = this.library.listMedias.size();
		if (this.randomPlaying)
			this.randomPlaying = false;
		else {
			this.randomPlaying = true;
			Random random = new Random();
			setId(random.nextInt(size));
		}
	}
	
	/**
	 * Allow to change the volume of the M3TPlayer
	 */
	public void changeVolume(float gain){
		//TODO method
		//FloatControl volControl = (FloatControl) .getControl(FloatControl.Type.MASTER_GAIN);
		//volControl.setValue(gain);
	}
	

	/**
	 * To set the new identifiant
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Allow to play the next song
	 */
	public void nextMedia(){
		int identifiant = this.id;
		identifiant++;
		if (identifiant >= this.library.listMedias.size()){
			setId(0);
		}
		else setId(identifiant);
		playMedia();
	}
	
	/**
	 * Allow to play the previous song
	 */
	public void previousMedia(){
		int identifiant = this.id;
		identifiant--;
		if (identifiant < 0)
			setId(this.library.listMedias.size()-1);
		else setId(identifiant);
		playMedia();
	}
	
	/**
	 * 
	 * @param mediaID
	 */
	public void playMedia(){
		// TODO complete method
		Media mediaToPlay = this.library.getListMedias().get(this.id);
		try {
			this.player = new AdvancedPlayer(mediaToPlay.getStream());
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		try {
			this.player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
}

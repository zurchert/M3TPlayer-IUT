package fr.iutvalence.m3tplayer;

import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import enumerations.PlayerControl;

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
	 * The state of playing
	 */
	private boolean isPlaying;

	/**
	 * The position of the music
	 */
	private int position;
	
	/**
	 * The random generator
	 */
	private Random randomGenerator;

	private boolean isPausing;
	
	/**
	 * Initializes the player with default values.
	 * Sets the volume to 100 percent, and the random mode to false, and the identifiant to 0.
	 */
	public M3TPlayer() {
		this.library = new Library();
		this.volume = 100;
		this.randomPlaying = false;
		this.isPlaying = false;
		this.position = 0;
		if(this.library.isEmpty())
			this.currentMedia = null;
		else
			this.currentMedia = this.library.getMedia(0);

		this.player = null;
		this.randomGenerator = new Random();

	}
	
	/**
	 * @param currentMedia le currentMedia à définir
	 */
	public void setCurrentMedia(Media currentMedia) {
		this.currentMedia = currentMedia;
	}

	/**
	 * @return the library
	 */
	public Library getLibrary() {
		return this.library;
	}

	/**
	 * @param library the library to define
	 */
	public void setLibrary(Library library) {
		this.library = library;
	}

	/**
	 * Switchs the random playing to on/off
	 */
	public void setRandomPlaying(){
		// TODO Fix
		int maxRandId = this.library.getImportedMusicNumber();
		if (this.randomPlaying)
			this.randomPlaying = false;
		else {
			this.randomPlaying = true;
			this.currentMedia = this.library.getMedia(this.randomGenerator.nextInt(maxRandId));			
		}
	}
	
	/**
	 * Allow to stop the music
	 */
	public void stop(){
		this.player.stop();
		this.isPlaying = false;
	}
	
	
	public void pause(){
		// TODO Check method behavior
		Player playing;
		try {
			playing = new Player(this.currentMedia.getStream());
			this.position = playing.getPosition();
			this.player.stop();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Allow to change the volume of the M3TPlayer
	 */
	public void changeVolume(){
		//TODO method
		DataLine.Info info = null;
	    Clip clip;
		try {
			clip = (Clip) AudioSystem.getLine(info);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			double gain = .5D; // number between 0 and 1 (loudest)
		    float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		    gainControl.setValue(dB);
		} catch (LineUnavailableException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}

	/**
	 * Changes the current media to another one.
	 * @param control <tt>PREVIOUS</tt> to play the previous media,
	 *                <tt>NEXT</tt> to play the next media
	 */
	public void changeMedia(PlayerControl control){
		int size = this.library.getImportedMusicNumber();
		if (!this.randomPlaying){
			int mediaId = 0;

			mediaId = this.library.getMediaId(this.currentMedia);

			switch (control) {
				case PREVIOUS:
					mediaId -= 1;
					if (mediaId < 0)
						mediaId = size-1;
					break;
				case NEXT:
					mediaId += 1;
					if (mediaId >= size)
						mediaId = 0;
					break;
				default:
					break;
			}
			
			this.currentMedia = this.library.getMedia(mediaId);
		}
		else{
			this.currentMedia = this.library.getMedia(this.randomGenerator.nextInt(size));
		}
		
	}
	
	/**
	 * @return the currentMedia
	 */
	public Media getCurrentMedia() {
		return this.currentMedia;
	}

	public void setCurretnMedia(Media media){
		this.currentMedia = media;
	}
	
	/**
	 * Plays the current media
	 */
	public void playMedia(){
		try {
			this.player = new AdvancedPlayer(this.currentMedia.getStream());
			this.isPlaying = true;
			this.player.play(this.position, Integer.MAX_VALUE);
			while (this.isPlaying){
				this.changeMedia(PlayerControl.NEXT);
				this.player.play();
			}
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch(NullPointerException e){
			System.out.println("No media to play ! (the source does not exists)");
		}
	}

	/**
	 * @return the isPlaying
	 */
	public boolean isPlaying() {
		return this.isPlaying;
	}

	/**
	 * @param isPlaying the isPlaying to set
	 */
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public boolean isPausing() {
		return this.isPausing;
	}

	/**
	 * @param isPausing the isPausing to set
	 */
	public void setPausing(boolean isPausing) {
		this.isPausing = isPausing;
	}
	
}
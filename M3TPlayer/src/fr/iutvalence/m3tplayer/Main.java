package fr.iutvalence.m3tplayer;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;


public class Main {

	public static void main(String[] args) throws LineUnavailableException, IOException{
		
		
		M3TPlayer m3t = new M3TPlayer();
//		m3t.activateRandomPlaying();
		m3t.changeVolume();
		m3t.playMedia();	
		m3t.playMedia();
		//m3t.changeMedia(PlayingControl.PREVIOUS);
	}

}

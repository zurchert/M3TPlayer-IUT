package fr.iutvalence.m3tplayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class Radio extends Media{

	/**
	 * The url of the radio stream
	 */
	private URL url;
	
	/**
	 * Creates a new Radio
	 * @param id The id of the radio
	 * @param title The title of the radio
	 * @param url The url of the radio stream. Url must end with valid extension.
	 * @see fr.iutvalence.m3tplayer.RadioStreamExtension
	 */
	public Radio(int id, String title, String url) {
		super(id, title);
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("L'url" + url + " n'est pas valide");
			e.printStackTrace();
		}
		try {
			super.stream = new FileInputStream(new File(url));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return The url of the radio
	 */
	public URL getUrl() {
		return this.url;
	}

	/**
	 * @param url The url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	
}

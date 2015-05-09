package fr.iutvalence.m3tplayer;

import java.net.MalformedURLException;
import java.net.URL;

import fr.iutvalence.exceptions.InvalidRadioURLException;

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
			if(!RadioStreamExtension.isValidUrlWithExt(this.url)){
				throw new InvalidRadioURLException();
			}
		} catch (MalformedURLException e) {
			System.out.println("L'url" + url + " n'est pas valide");
			e.printStackTrace();
		} catch (InvalidRadioURLException e) {
			System.out.println("L'extension de l'url n'est pas valide");
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

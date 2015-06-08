package fr.iutvalence.m3tplayer;

import java.net.URL;

public enum RadioStreamExtension {

	/**
	 * .m3u ext
	 */
	M3U(".m3u"),
	
	/**
	 * .mp3 ext
	 */
	MP3(".mp3");
	
	/**
	 * The extension
	 */
	private String extension;

	/**
	 * Creates a new extension
	 * @param extension The extension to create
	 */
	private RadioStreamExtension(String extension) {
		this.extension = extension;
	}
	
	/**
	 * @return the extension
	 */
	public String getExtension(){
		return this.extension;
	}
	
	/**
	 * Checks if a given URL is vali,
	 *    i.e if the url ends with one of the extensions of the Enum.
	 * @param url The url to test
	 * @return <tt>true</tt> if the url get the right extension.
	 */
	public static boolean isValidUrlWithExt(URL url){
		String[] urlParts = url.toString().split(".");
		String ext = urlParts[urlParts.length - 1];
		try{
			RadioStreamExtension.valueOf(ext);
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

}

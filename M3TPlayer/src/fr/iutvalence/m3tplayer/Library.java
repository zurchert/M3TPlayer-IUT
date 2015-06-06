package fr.iutvalence.m3tplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Manages the library of the player.
 *
 */
public class Library {

	/**
	 * The library xml file path on the hard-drive.
	 * 
	 */
	private final static String LIBRARY_PATH = "/Users/Théo/library.xml";

	/**
	 * The library xml file
	 */
	private Document libraryFile;

	/**
	 * The root node of the xml file
	 */
	private Element rootNode;

	/**
	 * The list of the imported medias.
	 * They are linked with their unique ID
	 */
	protected Map<Integer, Media> listMedias;

	/**
	 * The number of music which were imported into the library
	 */
	protected int importedMusicNumber;

	/**
	 * The sum of the lenght of all musics imported into the library.
	 */
	protected float totalMusicLenght;

	/**
	 * Initializes the library.
	 * If a library has been set before, it loads it, else,
	 * it creates a new one.
	 */
	public Library() {
		this.listMedias = new HashMap<Integer, Media>();
		this.totalMusicLenght = 0;
		this.importedMusicNumber = 0;

		File configFile = new File(LIBRARY_PATH);
		if(!configFile.exists()){
			this.rootNode = new Element("M3TPlayer");
			this.libraryFile = new Document(this.rootNode);
			this.saveLibrary();
		}
		else{
			// Loads the xml file
			try {
				this.libraryFile = new SAXBuilder().build(LIBRARY_PATH);
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.rootNode = this.libraryFile.getRootElement();
		}
		
		this.importedMusicNumber = this.listMedias.size();
		this.importMedia(new Music("test", "C:/Users/Théo/Music/MrKey_Trailer.mp3", 10.));
		this.importMedia(new Music("test2", "C:/Users/Théo/Music/Big_Data-Dangerous_(OliverRemix).mp3", 10.));
	} 

	/**
	 * Loads all the medias which were saved into the library.
	 * It is used at the creation of the library object. 
	 */
	public void loadMediaFromFile(){
		Element media = null;
		for (int mediaNumber = 0; mediaNumber < this.listMedias.size(); mediaNumber++) {
			media = this.getMusicNode(mediaNumber);
			if(media != null){
				String title = media.getChildText("title");
				String path = media.getChildText("path");
				String id = media.getAttribute("id").toString();
				if(media.getAttribute("media").toString() == "music"){
					String artist = media.getChildText("artist");
					String album = media.getChildText("album");
					String rating = media.getChildText("rating");
					
					// TODO GET MUSIC LENGHT
					Music music = new Music(title, path, 11, artist, album);
					music.setRating(Integer.getInteger(rating));
					
					this.listMedias.put(Integer.getInteger(id), music);
				}
				else{
					this.listMedias.put(Integer.getInteger(id), new Radio(title, path));
				}
			}
		}
	}
	
	/**
	 * Returns the media node which maches with a given id
	 * @param id The id of the media
	 * @return The media node if the id maches with.
	 * 	       Else it returns <tt>null</tt> if there is no match
	 */
	public Element getMusicNode(int id){
		List<Element> listMusic = this.rootNode.getChildren("Media");
		Iterator<Element> iterator = listMusic.iterator();
		while(iterator.hasNext()){
			Element currentMusicNode = (Element) iterator.next();
			String musicId = currentMusicNode.getAttributeValue("id");
			if(Integer.parseInt(musicId) == id){
				return currentMusicNode;
			}
		}
		return null;
	}
	
	/**
	 * Imports a media into the library
	 * @param media The media object to import
	 */
	public void importMedia(Media media){
		
		//TODO Adding ID to media in XML file ?
		
		Element mediaNode = new Element("Media");
		Element title = new Element("title");
		Element audioPath = new Element("path");
		
		
		mediaNode.setAttribute("id", Integer.toString(this.importedMusicNumber));
		
		// If the media is a Radio
		if(media.getClass().equals(new Radio("radio", "radio").getClass())){
			mediaNode.setAttribute("media", "radio");
		}
		
		// If it is a Music
		else{
			mediaNode.setAttribute("media", "music");
			
			Music music = (Music) media;
			Element artist = new Element("artist");
			Element album = new Element("album");
			Element rating = new Element("rating");
			
			// Set the rating to 0 (default value)
			rating.setText(Integer.toString(music.getRating()));
			artist.setText(music.getArtist());
			album.setText(music.getAlbum());
			title.setText(music.getTitle());
			
			mediaNode.addContent(artist);
			mediaNode.addContent(album);
			mediaNode.addContent(rating);
		}
		
		audioPath.setText(media.getPath());
		
		mediaNode.addContent(title);
		mediaNode.addContent(audioPath);

		this.rootNode.addContent(mediaNode);
		
		this.listMedias.put(importedMusicNumber++, media);
		
		this.saveLibrary();
		
	}
	
	/**
	 * Returns a media by its given id
	 * @param id The media's ID
	 * @return The media which matches with the ID
	 */
	public Media getMedia(int id){
		return this.listMedias.get(id);
	}
	
	/**
	 * @return the listMedias
	 */
	public Map<Integer, Media> getListMedias() {
		return this.listMedias;
	}

	/**
	 * Save the modifications into the xml librairy file
	 */
	public void saveLibrary(){
		XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
		try {
			output.output(this.libraryFile, new FileOutputStream(LIBRARY_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

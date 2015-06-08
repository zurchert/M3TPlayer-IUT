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

import fr.iutvalence.exceptions.UnknownMediaException;

/**
 * Manages the library of the player.
 *
 */
public class Library {

	/**
	 * The library xml file path on the hard-drive.
	 * 
	 */
	private final static String LIBRARY_PATH = "F:\\musics\\library.xml";

	/**
	 * The library xml file
	 */
	private Document libraryFile;

	/**
	 * The list of the imported medias.
	 * They are linked with their unique ID
	 */
	private Map<Integer, Media> listMedias;

	/**
	 * The number of music which were imported into the library
	 */
	private int importedMusicNumber;

	/**
	 * The sum of the lenght of all musics imported into the library.
	 */
	private float totalMusicLenght;

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
			this.libraryFile = new Document(new Element("M3TPlayer"));
			this.saveLibrary();
		}
		else{
			// Loads the xml file
			try {
				this.libraryFile = new SAXBuilder().build(new File(LIBRARY_PATH));
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.loadMediaFromFile();
		}
		this.importedMusicNumber = this.listMedias.size();
	} 

	/**
	 * Checks if the library is empty
	 * @return <tt>true</tt> if the library is empty, <tt>false</tt> if not.
	 */
	public boolean isEmpty(){
		return !(this.importedMusicNumber > 0);
	}
	
	/**
	 * Loads all the medias which were saved into the library.
	 * It is used at the creation of the library object. 
	 */
	public void loadMediaFromFile(){
		int mediaId = 0;
		Element media;
		while((media = this.getMediaNode(mediaId)) != null){
			// If the media is a music
			String title = media.getChildText("title");
			String path = media.getChildText("path");
			
			if(media.getAttributeValue("media") == "music"){
				String artist = media.getChildText("artist");
				String album = media.getChildText("album");
				this.importMedia(new Music(title, path, 10, artist, album)); // TODO change music lenght
			} else{
				this.importMedia(new Radio(title, path));
			}
			mediaId++;
		}
	}

	/**
	 * Returns the media node which maches with a given id
	 * @param id The id of the media
	 * @return The media node if the id maches with.
	 * 	       Else it returns <tt>null</tt> if there is no match
	 */
	public Element getMediaNode(int id){
		List<Element> listMusic = this.libraryFile.getRootElement().getChildren("Media");
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
	 * Imports a media into the library file
	 * @param media The media object to import
	 */
	public void importMedia(Media media){

		Element mediaNode = new Element("Media");
		Element title = new Element("title");
		Element audioPath = new Element("path");


		mediaNode.setAttribute("id", Integer.toString(this.importedMusicNumber));

		// If the media is a Radio
		if(media.getClass().equals(Radio.class)){
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

		this.libraryFile.getRootElement().addContent(mediaNode);

		this.listMedias.put(this.importedMusicNumber++, media);

		this.saveLibrary();

	}

	/**
	 * Returns a media by its given id
	 * @param id The media's ID
	 * @return The media which matches with the ID, or <tt>null</tt> if there is no match
	 */
	public Media getMedia(int id) {
		return this.listMedias.get(id);
	}

	/**
	 * Returns the id in the list of the given media
	 * @param media The media
	 * @return The media's id
	 * @throws UnknownMediaException if the media does not exist in the library
	 */
	public int getMediaId(Media media) throws UnknownMediaException{
		int id = -1;
		for (Media m : this.listMedias.values()) {
			if(!m.equals(media))
				id++;
		}
		if(id == -1)
			throw new UnknownMediaException();
		
		return id + 1;
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

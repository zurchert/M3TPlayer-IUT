package fr.iutvalence.m3tplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import enumerations.MediaInformation;
import fr.iutvalence.exceptions.UnknownMediaException;
import fr.iutvalence.utils.Utils;

/**
 * Manages the library of the player.
 *
 */
public class Library {

	//TODO ADD LENGHT
	
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
		Media mediaToImport = null;
		while((media = this.getMediaNode(mediaId)) != null){
			if(media.getChildText("path").startsWith("http://")){
				mediaToImport = new Radio(media.getChildText("path"), media.getChildText("path"));
			}
			else{
				mediaToImport = new Music(media.getChildText("title"), media.getChildText("path"), 
						10, media.getChildText("artist"), media.getChildText("album"));
			}
			this.importWithoutAddingToFile(mediaToImport); // TODO change music lengh
			mediaId++;
		}
	}

	public void importWithoutAddingToFile(Media media){
		this.listMedias.put(this.importedMusicNumber++, media);
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
	public void importMedia(String path){

		Media media = null;

		Element mediaNode = new Element("Media");
		Element title = new Element("title");
		Element audioPath = new Element("path");

		mediaNode.setAttribute("id", Integer.toString(this.importedMusicNumber));


		// If the media is a Radio
		if(path.startsWith("http://")){
			mediaNode.setAttribute("media", "radio");
			media = new Radio(path, path);
		}

		// If it is a Music
		else{
			MP3File f = null;

			try {
				f = (MP3File) AudioFileIO.read(new File((path)));
			} catch (CannotReadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TagException e) {
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				e.printStackTrace();
			}
			MP3AudioHeader audioHeader = f.getMP3AudioHeader();
			Tag tag = f.getTag();


			mediaNode.setAttribute("media", "music");

			Element artist = new Element("artist");
			Element album = new Element("album");
			Element rating = new Element("rating");
			Element lenght = new Element("lenght");

			String titleText = null;
			String albumText = tag.getFirst(FieldKey.ARTIST);
			String artistText = tag.getFirst(FieldKey.ALBUM);
			int lenghtText = audioHeader.getTrackLength();

			// Set the rating to 0 (default value)
			rating.setText(Integer.toString(0));
			artist.setText(albumText);
			album.setText(artistText);

			if(tag.getFirst(FieldKey.TITLE) == ""){
				titleText = Utils.getLastArrayElement(path.split(Pattern.quote(File.separator)));
				titleText = titleText.split(Pattern.quote("."))[0];
				title.setText(titleText);
			}
			else{
				titleText = tag.getFirst(FieldKey.TITLE);
				title.setText(titleText);
			}

			mediaNode.addContent(artist);
			mediaNode.addContent(album);
			mediaNode.addContent(rating);
			mediaNode.addContent(lenght);
			media = new Music(titleText, path, lenghtText, artistText, albumText);
		}

		audioPath.setText(path);

		mediaNode.addContent(title);
		mediaNode.addContent(audioPath);

		this.libraryFile.getRootElement().addContent(mediaNode);

		this.listMedias.put(this.importedMusicNumber++, media);

		this.saveLibrary();

	}

	/**
	 * Update a specified media
	 * @param id The id of the media
	 * @param informations Map with the informations to update.
	 * @throws UnknownMediaException Raised if there is no media matches with the id
	 */
	public void updateMedia(int id, Map<MediaInformation, String> informations) throws UnknownMediaException{
		Element mediaToUpdate = this.getMediaNode(id);
		if(mediaToUpdate == null) 
			throw new UnknownMediaException();

		// Get the keys of information Map
		Set<MediaInformation> keys = informations.keySet();
		Iterator<MediaInformation> keyIterator = keys.iterator();

		while(keyIterator.hasNext()){
			MediaInformation key = keyIterator.next();
			Element mediaChild = mediaToUpdate.getChild(key.toString());
			if(mediaChild != null){
				mediaChild.setText(informations.get(key));
			}
		}

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
	 */
	public int getMediaId(Media media) {
		return (Integer) Utils.getKeyFromValue(this.listMedias, media);
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

	/**
	 * @return the importedMusicNumber
	 */
	public int getImportedMusicNumber() {
		return this.importedMusicNumber;
	}

	/**
	 * @return the totalMusicLenght
	 */
	public float getTotalMusicLenght() {
		return this.totalMusicLenght;
	}
	
}

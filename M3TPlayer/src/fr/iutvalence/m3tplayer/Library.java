package fr.iutvalence.m3tplayer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Manages the library of the player.
 *
 */
public class Library {

	/**
	 * The library xml file path on the hard-drive.
	 * 
	 */
	private final static String LIBRARY_CONFIG_PATH = "library.xml";
	
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
	 * At this moment, the library is empty.
	 */
	public Library() {
		this.listMedias = new HashMap<Integer, Media>();
		this.listMedias.put(0, new Radio(0, "NRJ", "http://broadcast.infomaniak.ch/radioespace-high.mp3"));
		this.totalMusicLenght = 0;
		this.importedMusicNumber = 0;
		
		File configFile = new File(LIBRARY_CONFIG_PATH);
		if(!configFile.exists())
			this.loadMediasFromFile();
		
		else{
			// Loads the xml file
			try {
				this.libraryFile = new SAXBuilder().build(LIBRARY_CONFIG_PATH);
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.rootNode = this.libraryFile.getRootElement();
		}
	} 
	
	/**
	 * Parse the library config file and import all Medias which are into the file.
	 */
	private void loadMediasFromFile() {
		this.rootNode = new Element("M3TPlayer");
		
		this.libraryFile = new Document(this.rootNode);

		this.saveLibrary();
		
	}
	
	/**
	 * @return the listMedias
	 */
	public Map<Integer, Media> getListMedias() {
		return this.listMedias;
	}

	/**
	 * Saves the library file
	 */
	private void saveLibrary() {
		// TODO Auto-generated method stub
	}
}

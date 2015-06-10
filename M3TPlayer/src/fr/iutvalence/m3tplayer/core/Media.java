package fr.iutvalence.m3tplayer.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Represents a audio Media
 */
public class Media {
	
	/**
	 * The media's name
	 */
	protected String title;

	/**
	 * The media's path
	 */
	protected String path;
	
	/**
	 * The media's stream
	 */
	protected InputStream stream;

	/**
	 * Creates a new Media with given data
	 * @param title The media's title
	 * @param path The media's path
	 */
	public Media(String title, String path) {
		this.title = title;
		this.path = path;
		try {
			this.stream = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the stream
	 */
	public InputStream getStream() {
		return this.stream;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.path == null) ? 0 : this.path.hashCode());
		result = prime * result + ((this.stream == null) ? 0 : this.stream.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!this.path.equals(other.path))
			return false;
		if (this.stream == null) {
			if (other.stream != null)
				return false;
		} else if (!this.stream.equals(other.stream))
			return false;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		return true;
	}

	
	// TODO Check if the ID is not in the Map<int, Media> attribute of the Library class
}

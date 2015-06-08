package enumerations;

public enum MediaInformation {

	TITLE("title"),
	
	ARTIST("artist"),
	
	ALBUM("album"),
	
	PATH("path"),
	
	RATING("rating");
	
	private String name;

	private MediaInformation(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}

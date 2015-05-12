package fr.iutvalence.m3tplayer;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Media> medias = new ArrayList<Media>();
		for (int i = 0; i < 5; i++) {
			medias.add(new Music(i, "test"+i, null, (i+1) * 0.5));
		}
		
		for (Media media : medias) {
			System.out.println(media);
			System.out.println("-------\n");
		}
	}

}

package fr.iutvalence.utils;

import java.util.Map;

public class Utils<E> {

	public static Object getKeyFromValue(Map hm, Object value) {
		for (Object o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}
	
	
	public static <E> E getLastArrayElement(E[] array){
		return array[array.length - 1];
	}
}

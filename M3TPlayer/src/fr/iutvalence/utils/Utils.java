package fr.iutvalence.utils;

import java.util.Map;

/**
 * Utils class which contains static methods
 * @param <E> A given object type
 */
public class Utils<E> {

	/**
	 * Returns the key of a Map by its value
	 * @param map the Map
	 * @param value The value
	 * @return Returns the key which maches with the value
	 */
	public static Object getKeyFromValue(Map map, Object value) {
		for (Object o : map.keySet()) {
			if (map.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * Returns the last element of a array
	 * @param array The array
	 * @return The last element of a array
	 */
	public static <E> E getLastArrayElement(E[] array){
		return array[array.length - 1];
	}
}

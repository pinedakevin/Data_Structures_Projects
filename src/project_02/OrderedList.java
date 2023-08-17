package project_02;

import java.util.Comparator;
import java.util.List;

/**
 * Checks a generic list order using the Comparable and Comparator classes.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 2
 * @Date 06/08/2023
 */
public class OrderedList {

	/**
	 * Checks if a generic list is sorted ascending using the Comparator class.
	 * 
	 * @param <T>
	 * @param list
	 * @param comparator
	 * @return
	 */
	public static <T> boolean checkSorted(List<T> list, Comparator<? super T> comparator) {
		for (int i = 0; i < list.size() - 1; i++) {
			if (comparator.compare(list.get(i), list.get(i + 1)) >= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Overloaded method: checks if list is sorted by the a "natural order" of the
	 * list.
	 *
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
		Comparator<T> c = Comparable::compareTo;
		return checkSorted(list, c);
	}
}

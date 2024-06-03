package effective.java.item30;

public class ArrayUtils {
	public static <T extends Comparable<T>> T findMax(T[] array) {
		if (array == null || array.length == 0)
			throw new IllegalArgumentException("Array must not be empty");

		T max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(max) > 0) {
				max = array[i];
			}
		}
		return max;
	}
}

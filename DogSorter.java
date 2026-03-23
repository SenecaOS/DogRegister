import java.util.Comparator;

public class DogSorter {

	private DogSorter() {

	}

	public static void sort(SortingAlgorithm algorithm, Comparator<Dog> comparator, Dog[] array) {

		switch (algorithm) {
		case BUBBLE_SORT:
			bubbleSort(comparator, array);
			break;
		case INSERTION_SORT:
			insertionSort(comparator, array);
		}

	}

	private static void bubbleSort(Comparator<Dog> comparator, Dog[] array) {
		boolean changed;
		do {
			changed = bubbledUpOneElement(comparator, array);

		} while (changed);

	}

	private static boolean bubbledUpOneElement(Comparator<Dog> comparator, Dog[] array) {
		boolean changed = false;
		for (int i = 1; i < array.length; i++) {
			if (comparator.compare(array[i], array[i - 1]) < 0) {
				Dog temp = array[i];
				array[i] = array[i - 1];
				array[i - 1] = temp;
				changed = true;

			}
		}
		return changed;

	}

	private static void insertionSort(Comparator<Dog> comparator, Dog[] array) {
		for (int i = 1; i < array.length; i++) {
			Dog temp = array[i];

			int previousIndex = i - 1;
			while (previousIndex >= 0 && comparator.compare(array[previousIndex], temp) > 0) {

				array[previousIndex + 1] = array[previousIndex];
				previousIndex--;

			}

			array[previousIndex + 1] = temp;

		}

	}

}

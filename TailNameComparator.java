import java.util.Comparator;

public class TailNameComparator implements Comparator<Dog> {

	public int compare(Dog firstDog, Dog secondDog) {
		if (firstDog.getTailLength() > secondDog.getTailLength()) {
			return 1;

		} else if (secondDog.getTailLength() > firstDog.getTailLength()) {

			return -1;

		}
		return firstDog.getName().compareTo(secondDog.getName());

	}

}

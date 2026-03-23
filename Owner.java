import java.util.Arrays;
import java.util.Comparator;

public class Owner {

	private final static int MAX_DOGS = 7;

	private String name;
	private int dogCounter = 0;

	private Dog[] dogArray = new Dog[MAX_DOGS];

	public Owner(String name, Dog... dog) {

		name = normaliseName(name);
		this.name = name;

		for (Dog d : dog) {
			addDog(d);

			d.setOwner(this);

		}

	}

	public String getName() {

		return name;

	}

	public String toString() {

		String dogNames = "";

		if (getDogs().length >= 1) {
			for (Dog d : getDogs()) {

				dogNames += d.getName() + ", ";

			}
			dogNames = dogNames.substring(0, dogNames.length() - 2);

			return name + " owns dogs: " + dogNames;

		}

		return name + " owns no dogs"; // inga hundar

	}

	public Dog[] getDogs() {
		int i = 0;
		Dog[] dogs = new Dog[dogCounter];
		for (Dog d : dogArray) {
			if (d != null) {
				dogs[i++] = d;
			}

		}
		Arrays.sort(dogs, Comparator.comparing(Dog::getName));
		return dogs;
	}

	public boolean addDog(Dog dog) {
		if (alreadyOwnsDog(dog) == true) {
			return false;
		}

		for (int i = 0; i < dogArray.length; i++) {

			if (dogArray[i] == null) {
				dogArray[i] = dog;
				dog.setOwner(this);
				dogCounter++;
				return true;

			}

		}

		return false;
	}

	private boolean alreadyOwnsDog(Dog dog) {
		for (int i = 0; i < dogArray.length; i++) {
			if (dogArray[i] != null && dogArray[i].getName().equals(dog.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean removeDog(String dog) {
		dog = normaliseName(dog);
		for (int i = 0; i < dogArray.length; i++) {
			if (dogArray[i] != null && dogArray[i].getName().equals(dog)) {

				if (dogArray[i].getOwner() != null) {
					dogArray[i].setOwner(null);
					return true; // efter att metod anropet setOwner exekveras kommer ytterligare ett metodanrop
									// till denna metod som ignorerar
					// denna del och fortsätter under den istället, därmed undviks oändlig rekursion
				}
				dogArray[i] = null;
				dogCounter--;
				return true;

			}

		}
		return false;

	}

	public boolean removeDog(Dog dog) {

		return removeDog(dog.getName());

	}

	public boolean ownsAnyDog() {
		return dogCounter > 0;
	}

	public boolean ownsMaxDogs() {
		return dogCounter == MAX_DOGS;

	}

	public boolean ownsDog(String dog) {
		dog = normaliseName(dog);
		return ownsDogByCondition(dog, null);

	}

	public boolean ownsDog(Dog dog) {
		return ownsDogByCondition(null, dog);

	}

	private boolean ownsDogByCondition(String name, Dog dog) {
		for (Dog d : dogArray) {
			if (d != null && ((dog != null && d == (dog)) || (name != null && d.getName().equalsIgnoreCase(name)))) {
				return true;

			}

		}

		return false;
	}

	

	private String normaliseName(String name) {

		name = name.toLowerCase();

		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);

		return name;
	}

}

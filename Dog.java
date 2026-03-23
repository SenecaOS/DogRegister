
public class Dog {

	private static final double TAIL_LENGTH_DIVISOR = 10;
	private static final double DACHSHUND_TAIL_LENGTH = 3.7;

	private String name;
	private String breed;
	private int age;
	private int weight;
	private double tailLength;
	private Owner owner;

	public Dog(String name, String breed, int age, int weight) {

		if (name == null || name.trim().isEmpty() || breed == null || breed.trim().isEmpty()) {
			throw new IllegalArgumentException("None of the attributes name or breed can be null or empty");
		}
		if (age < 0 || weight < 0) {
			throw new IllegalArgumentException("None of the attributes age or weight can be below 0");

		}

		this.name = normaliseName(name);
		this.breed = normaliseName(breed);
		this.age = age;
		this.weight = weight;
		tailLength = (double) age * weight / TAIL_LENGTH_DIVISOR; // del av de funktionella kraven på generisk
																	// svanslängd

	}

	public Dog(String name, String breed, int age, int weight, Owner owner) {

		this(name, breed, age, weight);
		setOwner(owner);

	}

	public String getName() {

		return name;
	}

	public String getBreed() {

		return breed;
	}

	public int getAge() {

		return age;
	}

	public int getWeight() {

		return weight;
	}

	public double getTailLength() {

		if (breed.equals("Tax") || breed.equals("Dachshund") || breed.equals("Teckel") || breed.equals("Mäyräkoira")) {

			tailLength = DACHSHUND_TAIL_LENGTH;

		}

		return tailLength;

	}

	public String toString() {

		return "Name: " + name + "\nBreed " + breed + "\nAge: " + age + "\nWeight: " + weight + "\nTail length: "
				+ tailLength + "\nOwner: " + owner;

	}

	public int updateAge(int increment) {
		if (increment > 0) {

			if (age <= Integer.MAX_VALUE - increment) {
				age += increment;
			}

			else {
				age = Integer.MAX_VALUE;
			}
			updateTailLength(); // svanslängden måste uppdateras eftersom den är beroende av åldern
		}

		return age;

	}

	public Owner getOwner() {

		return owner;
	}

	public boolean setOwner(Owner newOwner) {

		if (newOwner == null) {
			Owner formerOwner = owner;
			this.owner = null;
			formerOwner.removeDog(this.getName());
			return true;

		}

		if (newOwner != null && newOwner == this.owner) {
			return false;
		}

		if (this.owner != null) {
			this.owner.removeDog(this.getName());
		}

		this.owner = newOwner;

		if (newOwner != null && !newOwner.ownsDog(this)) {
			newOwner.addDog(this);
		}

		return true;
	}

	private double updateTailLength() {

		return tailLength = (double) age * weight / TAIL_LENGTH_DIVISOR;

	}

	private String normaliseName(String name) {
		if (name != null) {
			name = name.toLowerCase();

			name = Character.toUpperCase(name.charAt(0)) + name.substring(1);

			return name;
		}
		return null;

	}

}

import java.util.ArrayList;

public class DogRegister {
	private static final String EXIT_COMMAND = "EXIT";
	private static final String ADD_OWNER_COMMAND = "ADD OWNER";
	private static final String REMOVE_OWNER_COMMAND = "REMOVE OWNER";
	private static final String ADD_DOG_COMMAND = "ADD DOG";
	private static final String LIST_OWNERS_COMMAND = "LIST OWNERS";
	private static final String REMOVE_DOG_COMMAND = "REMOVE DOG";
	private static final String LIST_DOGS_COMMAND = "LIST DOGS";
	private static final String INCREASE_AGE_COMMAND = "INCREASE AGE";
	private static final String CHANGE_OWNER_COMMAND = "CHANGE OWNER";

	private InputReader input;
	private OwnerCollection owners = new OwnerCollection();

	public static void main(String[] args) {
		new DogRegister().start();

	}

	private void start() {
		setUp();
		runCommandLoop();
		shutDown();

	}

	private void setUp() {

		System.out.println(
				"Hej och välkommen till Hundregistret. Syftet med detta program är att hantera hundar och dess ägare");
		System.out.println("Nedan följer olika kommandon du som användare kan använda för att använda hundregistret:");
		System.out.println();
		System.out.println("KOMMANDON:");
		System.out.println("För att lägga till ägare skriv \"Add owner\"");
		System.out.println("För att ta bort ägare skriv \"Remove owner\"");
		System.out.println("För att lägga till hund skriv \"Add dog\"");
		System.out.println("För att ta bort hundar skriv \"Remove dog\"");
		System.out.println("För att se alla ägare skriv \"List owners\"");
		System.out.println("För att se alla hundar skriv \"List dogs\"");
		System.out.println("För att öka hundarnas ålder med 1 år skriv \"Increase age\"");
		System.out.println("För att byta ägare skriv \"Change owner\"");
		System.out.println("För att avsluta programmet skriv \"Exit\"");
		input = new InputReader();
	}

	private void runCommandLoop() {
		String command;
		do {
			command = readCommand();
			executeCommand(command);

		} while (!command.equalsIgnoreCase(EXIT_COMMAND));

	}

	private String readCommand() {
		System.out.println();
		String command = input.readLine("Vilket alternativ väljer du");
		return command;
	}

	private void executeCommand(String command) {
		command = command.toUpperCase();
		switch (command) {
		case ADD_OWNER_COMMAND:
			addOwner();
			break;
		case REMOVE_OWNER_COMMAND:
			removeOwner();
			break;
		case ADD_DOG_COMMAND:
			addDog();
			break;
		case LIST_OWNERS_COMMAND:
			listOwners();
			break;

		case REMOVE_DOG_COMMAND:
			removeDog();
			break;
		case CHANGE_OWNER_COMMAND:
			changeOwner();
			break;
		case LIST_DOGS_COMMAND:
			listDogs();
			break;
		case EXIT_COMMAND:	
			return;
		case INCREASE_AGE_COMMAND:
			increaseAge();
			break;
		default:

			printErrorMessage("Felaktigt kommando, försök igen");
		}
	}

	private void shutDown() {
		System.out.print("Program avslutat");

	}

	private void increaseAge() {
		if (dogsCount() == 0) {
			printErrorMessage("Fel! Inga hundar registrerade");
			return;
		}

		for (Owner o : owners.getAllOwners()) {
			for (Dog d : o.getDogs()) {
				d.updateAge(1);

			}

		}
		System.out.println("Hundarnas ålder har nu ökat med 1!");

	}

	private void listDogs() {

		if (dogsCount() == 0) {
			printErrorMessage("Fel! Inga hundar registrerade");
			return;
		}

		String printedMessage = "";

		double minTailLength = input.readDouble("Vilken minsta svanslängd ska listan filtreras efter i cm");

		Dog[] sortedDogsArray = sortDogsArray(minTailLength);

		if (sortedDogsArray.length == 0) {
			printErrorMessage("Fel! Ingen hund som mötte kriteriet registrerade");

		}

		for (Dog d : sortedDogsArray) {
			printedMessage += d.getName() + " has tail length " + d.getTailLength() + " cm and owner "
					+ d.getOwner().getName() + ", ";

		}
		printedMessage = printedMessage.substring(0, printedMessage.length() - 2); // ta bort kommatecknet och
																					// mellanrummet
																					// i slutet

		System.out.print(printedMessage);
		System.out.println();

	}

	private Dog[] sortDogsArray(double minTailLength) {
		ArrayList<Dog> sortedDogsList = new ArrayList<>();
		TailNameComparator tailComparator = new TailNameComparator();

		for (Owner o : owners.getAllOwners()) {
			addDogWithLongEnoughTail(o, minTailLength, sortedDogsList);
		}

		Dog[] sortedDogsArray = sortedDogsList.toArray(new Dog[0]);

		DogSorter.sort(SortingAlgorithm.BUBBLE_SORT, tailComparator, sortedDogsArray);

		return sortedDogsArray;

	}

	private void addDogWithLongEnoughTail(Owner o, double minTailLength, ArrayList<Dog> sortedDogsList) {
		for (Dog d : o.getDogs()) {
			if (d.getTailLength() >= minTailLength) {
				sortedDogsList.add(d);
			}
		}
	}
	

	private void changeOwner() {

		if (!canChangeOwner()) {
			return;
		}

		Owner currentOwner = getValidOwner("Namn på nuvarande ägare", "Fel! Nuvarande ägare finns inte");
		if (currentOwner == null) {
			return; // felmeddelandet visas redan i getValidOwner()

		}

		if (currentOwner.getDogs().length == 0) {
			printErrorMessage("Fel! Ägaren äger inga hundar");
			return;

		}

		String nameOfDog = input.readLine("Namn på hund");

		if (!currentOwner.ownsDog(nameOfDog)) {

			printErrorMessage("Fel! Ägaren äger ingen sådan hund");
			return;
		}

		Owner newOwner = getValidOwner("Namn på ny ägare", "Fel! Ny ägare finns inte");
		if (newOwner == null || !isEligibleNewOwner(newOwner, currentOwner, nameOfDog)) {
			return; // felmeddelandet visas redan i metodanrop

		}
		
		Dog d = getDogByName(currentOwner, nameOfDog);
		if(d != null) {
		currentOwner.removeDog(d);
		newOwner.addDog(d);
		System.out.println(d.getName() + " har nu fått ny ägare " + newOwner.getName());
		}
	}
	
	private Dog getDogByName(Owner owner, String name){
		
		Dog[] ownersDogs = owner.getDogs();
		for(int i = 0; i<ownersDogs.length; i++) {
			if(ownersDogs[i].getName().equalsIgnoreCase(name)) {
				return ownersDogs[i];
				
			}
		}
		return null;
	}
	

	private boolean isEligibleNewOwner(Owner newOwner, Owner currentOwner, String nameOfDog) {
		if (currentOwner == newOwner) {
			printErrorMessage("Fel! Nya ägaren är samma som den gamla");
			return false;

		}

		if (newOwner.ownsMaxDogs()) {
			printErrorMessage("Fel! Nya ägaren äger max antal hundar");
			return false;

		}

		if (newOwner.ownsDog(nameOfDog)) {
			printErrorMessage("Fel! Nya ägaren äger redan en hund med samma namn");
			return false;
		}

		return true;
	}

	private boolean canChangeOwner() {
		if (owners.size() < 2) {
			printErrorMessage("Fel! För få ägare för att utföra byte");
			return false;

		}

		if (dogsCount() == 0) {
			printErrorMessage("Fel! Inga hundar registrerade");
			return false;

		}

		return true;

	}

	private Owner getValidOwner(String prompt, String errorMessage) {
		String name = input.readLine(prompt);
		if (!owners.containsOwner(name)) {
			printErrorMessage(errorMessage);
			return null;

		}
		return owners.getOwner(name);

	}

	private void removeDog() {

		if (dogsCount() == 0) {
			printErrorMessage("Fel! Inga hundar registrerade");
			return;
		}

		Owner owner = getValidOwner("Ägarens namn", "Fel! Ingen sådan ägare finns");
		
		if (owner == null) {
			return; // felmeddelande skrivs ut i getValidOwner()anropet
		}

		String dogName = input.readLine("Namn på hund");

		if (!owner.ownsDog(dogName)) {
			printErrorMessage("Fel! Ingen sådan hund finns bland ägarens hundar");
			return;
		}
		
		Dog d = getDogByName(owner, dogName);
		if(d != null) {
		System.out.print("Hunden " + d.getName() + " borttagen från " + owner.getName()
				+ "s ägda hundar");
		System.out.println();

		owner.removeDog(dogName);
		}
	}

	private void listOwners() {

		StringBuilder ownersWithDogs = new StringBuilder();
		if (owners.size() == 0) {
			printErrorMessage("Fel! Inga ägare registrerade");
			return;

		}

		for (Owner o : owners.getAllOwners()) {
			buildString(o, ownersWithDogs);

		}

		ownersWithDogs.delete(ownersWithDogs.length() - 3, ownersWithDogs.length()); // tar bort kommatecknet och
																						// newline
																						// vid utskrift

		System.out.print(ownersWithDogs);
		System.out.println();

	}

	private void buildString(Owner o, StringBuilder ownersWithDogs) {

		if (o.getDogs().length == 0) {
			ownersWithDogs.append(o.getName()).append(" owns no dogs\n");
			return;
		}

		if (o.getDogs().length == 1) {
			ownersWithDogs.append(o.getName()).append(" owns dog: ");
		} else {
			ownersWithDogs.append(o.getName()).append(" owns dogs: "); // Lägger till pluralform på dog för flera hundar
		}

		for (Dog dog : o.getDogs()) {
			ownersWithDogs.append(dog.getName()).append(", ");
		}
		ownersWithDogs.append("\n");

	}

	private void addDog() {
		if (owners.size() == 0) {
			printErrorMessage("Fel! Inga ägare registrerade");
			return;
		}

		Owner owner = getValidOwner("Namn på ägare till hund", "Fel! Nuvarande ägare finns inte");
		if (owner == null) {
			return; // felmeddelande skrivs ut i getValidOwner()anropet
		}

		if (owner.ownsMaxDogs()) {
			printErrorMessage("Fel! Ägaren äger redan max antal hundar");
			return;

		}

		String dogName = input.readLine("Namn på hund");

		if (owner.ownsDog(dogName)) {
			printErrorMessage("Fel! Ägaren äger redan hund med det namnet");
			return;
		}
		Dog dog = inputDogAttributes(owner, dogName);
		if (dog == null) {
			return; // validering misslyckad
		}

		owner.addDog(dog);
		System.out.println(dog.getName() + " har nu fått ägare " + owner.getName());

	}

	private Dog inputDogAttributes(Owner owner, String dogName) {
		if (owner.ownsDog(dogName)) {
			printErrorMessage("Fel! Ägaren äger redan hund med det namnet");
			return null;
		}

		String breed = input.readLine("Hundras");
		int age = input.readInt("Ålder");
		int weight = input.readInt("Vikt");
		Dog dog = new Dog(dogName, breed, age, weight);

		return dog;

	}

	private void addOwner() {

		String name = input.readLine("Namn på ägare du vill lägga till");

		if (owners.containsOwner(name)) {

			printErrorMessage("Fel! Ägare med det namnet finns redan");
			return;

		}

		Owner owner = new Owner(name);
		owners.addOwner(owner);
		System.out.print("Ägare " + owner.getName() + " har nu lagts till");
		System.out.println();

	}

	private void removeOwner() {
		if (owners.size() == 0) {
			printErrorMessage("Fel! Inga ägare registrerade");
			return;
		}
		String name = input.readLine("Namn på ägare du vill ta bort");
		if (!owners.containsOwner(name)) {
			printErrorMessage("Fel! Ingen sådan ägare finns!");
			return;
		}
		Owner owner = owners.getOwner(name);
		owners.removeOwner(owner);
		System.out.print("Ägare " + owner.getName() + " har tagits bort");
	}

	private void printErrorMessage(String message) {
		System.out.println(message);

	}

	private int dogsCount() {
		int dogCount = 0;
		for (Owner o : owners.getAllOwners()) {
			dogCount += o.getDogs().length;

		}
		return dogCount;
	}

}

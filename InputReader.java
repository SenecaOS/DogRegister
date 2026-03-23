import java.util.Scanner;

public class InputReader {

	private static boolean instanceCreated;
	private Scanner sc;

	public InputReader() {
		this(new Scanner(System.in));

	}

	public InputReader(Scanner sc) {
		if (instanceCreated)
			throw new IllegalStateException("Endast en instans kan skapas");

		this.sc = sc;
		instanceCreated = true;

	}

	public int readInt(String prompt) {

		int value = 0;

		while (true) {

			System.out.print(prompt + "?>");

			if (!sc.hasNextInt()) {
				System.out.println("Fel! Måste vara ett heltal");
				sc.nextLine();
				continue;
			}

			value = sc.nextInt();

			sc.nextLine();

			if (value >= 0) {

				break;

			}

			System.out.println("Fel! Försök igen");

		}

		return value;
	}

	public double readDouble(String prompt) {

		double value = 0;

		while (true) {

			System.out.println(prompt + "?>");

			if (!sc.hasNextDouble()) {
				System.out.println("Fel! Måste vara ett tal");
				sc.nextLine();
				continue;
			}

			value = sc.nextDouble();

			sc.nextLine();

			if (value >= 0) {

				break;

			}
			System.out.print("Fel! Försök igen");

		}

		return value;

	}

	public String readLine(String prompt) {

		String string = "";
		while (true) {

			System.out.print(prompt + "?>");

			string = sc.nextLine();

			string = string.trim();

			if (string.length() >= 0)

				break;
		}

		return string;

	}
}

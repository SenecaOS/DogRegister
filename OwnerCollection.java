import java.util.ArrayList;
import java.util.Comparator;

public class OwnerCollection {
	private ArrayList<Owner> ownerList = new ArrayList<>();

	public boolean addOwner(Owner owner) {

		if (owner != null && !ownerList.contains(owner)) {
			ownerList.add(owner);
			return true;
		}
		return false;
	}

	public boolean removeOwner(Owner owner) {
		return removeOwnerByCondition(null, owner);

	}

	public boolean removeOwner(String name) {
		return removeOwnerByCondition(name, null);
	}

	private boolean removeOwnerByCondition(String name, Owner owner) {

		if (owner != null) {

			return ownerList.remove(owner);

		}

		else if (name != null) {
			return ownerList.remove(getOwner(name));

		}
		return false;

	}

	public boolean containsOwner(Owner owner) {
		return containsOwner(owner.getName());

	}

	public boolean containsOwner(String owner) {

		for (int i = 0; i < ownerList.size(); i++) {
			if (ownerList.get(i) != null && ownerList.get(i).getName().equalsIgnoreCase(owner)) {
				return true;

			}

		}
		return false;

	}

	public Owner getOwner(String owner) {
		for (int i = 0; i < ownerList.size(); i++) {
			if (ownerList.get(i) != null && ownerList.get(i).getName().equalsIgnoreCase(owner)) {
				return ownerList.get(i);

			}

		}
		return null;
	}

	public ArrayList<Owner> getAllOwners() {
		ArrayList<Owner> copy = new ArrayList<>(ownerList);

		copy = new ArrayList<>(ownerList);
		copy.sort(Comparator.comparing(Owner::getName));

		return copy;
	}

	public int size() {

		return ownerList.size();

	}

}

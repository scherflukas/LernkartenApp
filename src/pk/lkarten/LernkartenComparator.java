package pk.lkarten;

import java.util.Comparator;

public class LernkartenComparator implements Comparator<Lernkarte> {

	@Override
	public int compare(Lernkarte o1, Lernkarte o2) {
		return -(Integer.compare(o1.getID(), o2.getID()));
	}
}
package Server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Alphabetizer {

	public List<String> sort(List<String> lines) {
		TreeSet<String> sortedSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < lines.size(); i++) {

			sortedSet.add(lines.get(i));

		}
		Iterator<String> itr = sortedSet.iterator();
		List<String> sortedList = new ArrayList<String>();
		while (itr.hasNext()) {

			sortedList.add(itr.next());

		}
		return sortedList;

	}

}

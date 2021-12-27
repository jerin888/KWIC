package Server;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter {
	List<String> shiftedList = new ArrayList<String>();

	public String[] rotateWords(String[] line) {
		String lastElement;
		lastElement = line[line.length - 1];
		for (int j = line.length - 1; j > 0; j--) {
			line[j] = line[j - 1];
		}
		line[0] = lastElement;
		return (line);
	}

	public void shiftWords(String[] line) {
		for (int k = 0; k < line.length; k++) {
			line = rotateWords(line);
			shiftedList.add(String.join(" ", line));
		}
	}

	public List<String> shiftLines(List<String> lines) {

		for (int i = 0; i < lines.size(); i++) {
			String[] line = lines.get(i).split(" ");
			shiftWords(line);
		}

		return shiftedList;
	}

}

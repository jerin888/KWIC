package Client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class KwicClientTwo {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		List<String> lines = new ArrayList<>();
		List<String> modifiedLines = new ArrayList<>();
		lines.add("Descent of Man");
		lines.add("The Ascent of Man");
		lines.add("The Old Man and The Sea");
		Socket clientSocket = new Socket("localhost", 1888);

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

		ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

		System.out.println("Lines sent from the client side: \n");
		for (String line : lines) {
			System.out.println(line);
		}
		Thread.sleep(3000);

		objectOutputStream.writeObject(lines);

		Object object;
		System.out.println("\nCircular shifted and alphabetized outputs: \n ");

		for (;;) {
			try {
				object = objectInputStream.readObject();
				System.out.println(object);
				modifiedLines.add(object.toString());
			} catch (EOFException exc) {
				break;
			}
		}

		System.out.println("Size: " + modifiedLines.size());
	}

}

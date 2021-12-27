package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
	private final Socket clientSocket;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		CircularShifter circularShifter = new CircularShifter();
		Alphabetizer alphabetizer = new Alphabetizer();
		List<String> sortedList;
		List<String> lines;
		try {

			ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

			Object object = objectInputStream.readObject();
			lines = (ArrayList<String>) object;

			sortedList = alphabetizer.sort(circularShifter.shiftLines(lines));

			for (String line : sortedList) {

				Thread.sleep(2500);
				objectOutputStream.writeObject(line);

			}

			System.out.println("Thread done");

		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
			}
			System.out.println("Closed: " + clientSocket);
		}
	}
}

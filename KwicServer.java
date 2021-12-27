package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KwicServer {

	public static ExecutorService pool = Executors.newFixedThreadPool(8);

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		System.out.println("The KWIC server is running...");
		try {
			serverSocket = new ServerSocket(1888);
			serverSocket.setReuseAddress(true);

			while (true) {
				Socket socket = serverSocket.accept();

				System.out.println("New client connected " + socket.getInetAddress().getHostAddress());
				ClientHandler clientThread = new ClientHandler(socket);
				pool.execute(clientThread);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

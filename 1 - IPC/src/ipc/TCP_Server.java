package ipc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {

	public static void main(String[] args) {

		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				Connection c;
				c = new Connection(clientSocket); 
			}
		}
		catch(IOException e){
			System.out.println("Listen: " + e.getMessage());
		}

	}

}

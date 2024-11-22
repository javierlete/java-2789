package com.ipartek.bases.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(1234)) {

			System.out.println("Servidor en marcha");

			Socket s;
			
			do {
				s = ss.accept();
				new Hilo(s).start();
			} while (true); // TODO Definir cómo se para el servidor
		} catch (IOException e) {
			System.out.println("No se ha podido abrir el socket");
			e.printStackTrace();
		}
	}
}

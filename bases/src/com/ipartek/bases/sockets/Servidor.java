package com.ipartek.bases.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	private static final boolean AUTO_FLUSH = true;

	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(1234)) {
			
			System.out.println("Servidor en marcha");

			try (Socket s = ss.accept();
					PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
					Scanner sc = new Scanner(s.getInputStream())) {
				
				System.out.println("Se ha recibido una conexión");
				
				pw.println("Bienvenido al servidor MAYUSCULATOR(TM)");

				String texto = sc.nextLine();

				pw.println(texto.toUpperCase());
			} catch(IOException e) {
				System.out.println("Ha habido un fallo en la comunicación");
			}
		} catch (IOException e) {
			System.out.println("No se ha podido abrir el socket");
			e.printStackTrace();
		}
	}
}

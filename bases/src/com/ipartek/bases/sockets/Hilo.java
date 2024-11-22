package com.ipartek.bases.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Hilo extends Thread {

	private static final boolean AUTO_FLUSH = true;
	private Socket s;

	public Hilo(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
				Scanner sc = new Scanner(s.getInputStream())) {

			System.out.println("Se ha recibido una conexión");

			pw.println("Bienvenido al servidor MAYUSCULATOR(TM)");

			String texto;

			do {
				texto = sc.nextLine();
				pw.println(texto.toUpperCase());
			} while (!"!SALIR".equals(texto));

		} catch (IOException e) {
			System.out.println("Ha habido un fallo en la comunicación");
			e.printStackTrace();
		}
	}

}

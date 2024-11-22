package com.ipartek.bases.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private static final boolean AUTO_FLUSH = true;

	public static void main(String[] args) {
		try (Socket s = new Socket("localhost", 1234);
				PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
				Scanner sc = new Scanner(s.getInputStream())) {

			String texto = sc.nextLine();
			
			System.out.println(texto);

			pw.println("Prueba");
			
			texto = sc.nextLine();
			
			System.out.println(texto);
			
		} catch (IOException e) {
			System.out.println("Ha habido un fallo en la comunicación");
		}
	}
}

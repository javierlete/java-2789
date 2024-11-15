package com.ipartek.bibliotecas;

import java.util.Scanner;

public class Consola {
	private static final Scanner SC = new Scanner(System.in);

	private Consola() {
	}

	public static void p(String mensaje) {
		System.out.print(mensaje);
	}

	public static void pl() {
		System.out.println();
	}

	public static void pl(String mensaje) {
		System.out.println(mensaje);
	}

	public static String leerString(String mensaje) {
		p(mensaje + ": ");
		return SC.nextLine();
	}

	public static int leerEntero(String mensaje) {
		int valor = 0;

		boolean hayError = true;

		do {
			String texto = leerString(mensaje);

			try {
				valor = Integer.parseInt(texto);
				hayError = false;
			}catch (NumberFormatException e) {
				pl("El valor introducido no es válido");
			}
		} while (hayError);
		
		return valor;
	}
}

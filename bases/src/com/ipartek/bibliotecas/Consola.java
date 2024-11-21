package com.ipartek.bibliotecas;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

public class Consola {
	private static final boolean NO_REQUERIDO = false;
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

	public static String leerString(String mensaje, boolean requerido) {
		if (requerido) {
			return leerAlgo(mensaje, texto -> {
				if (texto.isBlank()) {
					throw new IllegalArgumentException("El texto no puede estar vacío");
				}

				return texto.trim();
			});
		}

		p(mensaje + ": ");
		return SC.nextLine();
	}

	public static String leerString(String mensaje) {
		return leerString(mensaje, NO_REQUERIDO);
	}

	public static int leerEntero(String mensaje) {
		return leerAlgo(mensaje, Integer::parseInt);
	}

	public static LocalDate leerFecha(String mensaje) {
		return leerAlgo(mensaje, LocalDate::parse);
	}

	public static Long leerLong(String mensaje) {
		return leerAlgo(mensaje, Long::parseLong);
	}

	private static <T> T leerAlgo(String mensaje, Function<String, T> cachoCodigo) {
		T valor = null;

		boolean hayError = true;

		do {
			String texto = leerString(mensaje);

			try {
				valor = cachoCodigo.apply(texto);
				hayError = false;
			} catch (Exception e) {
				pl("El valor introducido no es válido");
			}
		} while (hayError);

		return valor;
	}
}

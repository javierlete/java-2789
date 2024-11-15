package com.ipartek.bases;

public class Condicionales {
	public static void main(String[] args) {
		int i = 5;

		if (i != 0) {
			System.out.println("Es verdadero");
		}

		int opcion = 2;

		switch (opcion) {
		case 1:
			System.out.println("Opción 1");
			break;
		case 2:
			System.out.println("Opción 2");
			break;
		case 3:
			System.out.println("Opción 3");
			break;
		default:
			System.out.println("Opción desconocida");
		}

		int mes = 6, dias;

		switch (mes) {
		case 2:
			dias = 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			dias = 30;
			break;
		default:
			dias = 31;
		}

		System.out.println(dias);

		switch (opcion) {
		case 1 -> System.out.println("Opción 1");
		case 2 -> System.out.println("Opción 2");
		case 3 -> System.out.println("Opción 3");
		default -> System.out.println("Opción desconocida");
		}

		switch (mes) {
		case 2 -> dias = 28;
		case 4, 6, 9, 11 -> dias = 30;
		default -> dias = 31;
		}

		System.out.println(dias);

		dias = switch (mes) {
		case 2 -> 28;
		case 4, 6, 9, 11 -> 30;
		default -> 31;
		};
		
		System.out.println(dias);
	}
}

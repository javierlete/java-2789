package com.ipartek.bases;

public class Bucles {
	public static void main(String[] args) {
		int[] enteros = { 1, 5, 7, 9, 2, 5 };

		for (int entero : enteros) {
			System.out.println("Evaluando " + entero);

			if (entero == 7) {
				System.out.println("Encontrado");
				break;
			}
		}

		System.out.println("Fin del programa");

		char[][] letras = { { 'a', 'b', 'c', 'd' }, { 'e', 'f', 'g' } };
		
		buclePrincipal: for(char[] fila: letras) {
			for(char letra: fila) {
				System.out.println("Evaluando letra " + letra);
				
				if(letra == 'c') {
					System.out.println("Encontrada");
					break buclePrincipal;
				}
			}
		}
		
		boolean encontrado = false;
		
		char letra;
		
		for(int fila = 0; fila < letras.length && !encontrado; fila++) {
			for(int columna = 0; columna < letras[fila].length && !encontrado; columna++) {
				letra = letras[fila][columna];
				
				System.out.println("Evaluando letra " + letra);
				
				if(letra == 'c') {
					System.out.println("Encontrada");
					encontrado = true;
				}
			}
		}
	}
}

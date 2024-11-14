package com.ipartek.bases;

import java.util.Arrays;

public class ArraysEjemplo {
	public static void main(String[] args) {
		var arr = new int[] { 1, 2, 3 };
		int[] arr2 = { 1, 2, 3, 4 };
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr2));
		
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;

		System.out.println(Arrays.toString(arr));

		System.out.println(arr.length);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		for(int dato: arr) {
			System.out.println(dato);
		}

		var tablero = new Character[10][8];

		tablero[0][0] = 'a';
		tablero[9][7] = 'f';

		System.out.println(tablero.length);

		System.out.println(tablero[0].length);

		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[fila].length; columna++) {
				if (tablero[fila][columna] == null) {
					System.out.print(". ");
				} else {
					System.out.print(tablero[fila][columna] + " ");
				}
			}

			System.out.println();
		}

	}
}

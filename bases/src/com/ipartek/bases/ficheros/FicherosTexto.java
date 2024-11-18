package com.ipartek.bases.ficheros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FicherosTexto {
	private static final String RUTA_FICHERO = "fichero.txt";

	public static void main(String[] args) {
		try (FileWriter fw = new FileWriter(RUTA_FICHERO); PrintWriter pw = new PrintWriter(fw)) {
			pw.println("Una línea");
			pw.println("Otra línea");
		} catch (IOException e) {
			System.out.println("Ha habido un error al escribir el fichero");
		}

		FileReader fr = null;
		Scanner sc = null;

		try {
			fr = new FileReader(RUTA_FICHERO);
			sc = new Scanner(fr);

			while (sc.hasNext()) {
				System.out.println("Línea: " + sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero");
		} finally {
			// ANTES DE JAVA 7
			if (sc != null) {
				sc.close();
			}

			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					System.out.println("No se ha podido cerrar el fichero");
				}
			}
		}
	}
}

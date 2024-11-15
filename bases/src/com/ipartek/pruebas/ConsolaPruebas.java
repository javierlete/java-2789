package com.ipartek.pruebas;

import static com.ipartek.bibliotecas.Consola.*;

public class ConsolaPruebas {
	public static void main(String[] args) {
		try {
			pl("Hola a todos");
			
			String mensaje = leerString("Dime el mensaje que quieres");
			
			int indice = leerEntero("Elemento a buscar");
			
			pl("El indice que se va a buscar es " + indice);
			
			String[] arr = { "Uno", "Dos", "Tres" };
			
			try {
				System.out.println(mensaje + arr[indice]);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("No se ha encontrado ese elemento");
			}
		} catch (Exception e) {
			pl("Error no esperado"); // + e.getMessage());
		}
	}
}

package com.ipartek.bases;

import java.util.Scanner;

public class Consola {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Dime el texto: ");
			String prueba = sc.nextLine();
			
			System.out.println(prueba);
		} catch(NumberFormatException e) {
			System.err.println("Ha habido un error");
		} finally {
			System.out.println("Pase lo que pase");
		}
	}
}

package com.ipartek.bases;

import java.time.LocalDate;

public class Conversiones {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String numero = "3";
		
		int i = Integer.valueOf(numero);
		
		String numeroDouble = "5.6";
		
		double d = Double.parseDouble(numeroDouble);
		
		String fecha = "2014-12-11";
		
		LocalDate ld = LocalDate.parse(fecha);
		
		System.out.println(ld.getYear());
		
		double d1 = 6.4999;
		
		int i1 = (int) d1;
		
		System.out.println(i1);
		System.out.println(Math.round(d1));
		
		String texto = "s";
		
		boolean b = "s".trim().equals(texto);
		
		String letra = "asdfasd";
		
		char c = letra.trim().charAt(0);
		
		int entero = 5;
		
		String enteroTexto = String.valueOf(entero);
		
		String fechaConvertida = ld.toString();
	}
}

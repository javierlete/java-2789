package com.ipartek.bases;

public class Bases {
	public static void main(String[] args) {
		System.out.println("Hola");
		
		double d1 = 0.1;
		double d2 = 0.2;
		
		System.out.println(d1 == d2);
		
		double suma = d1 + d2;
		
		System.out.println(suma);
		
		long l = 21231231230L;
		
		System.out.println(l);
		
		String s = String.valueOf(suma);
		
		System.out.println(s);
		
		String s1 = new String("Hola");
		String s2 = new String("Hola");
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
		System.out.println(s1.toUpperCase());
	}
}

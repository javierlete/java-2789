package com.ipartek.bases;

import static java.lang.Math.*;

public class Operadores {
	public static void main(String[] args) {
		System.out.println(5 ^ 2);
		System.out.println(pow(5, 2));
		System.out.println(E);
		System.out.println(PI);

		String s = "asdf";

		if (s == null || s.isBlank()) {
			System.out.println("No hay texto");
		} else {
			System.out.println(s.toUpperCase());
		}
		
		int a, b, c;
		
		a = b = c = 0;
		
		System.out.println(a++);
		
		System.out.println(++b);
		
		++c;
		
		System.out.println(c);
		
		int x = 10;
		
		System.out.println(~x);
		
		a = 6;
		b = 7;
		
		int maximo = a > b ? a : b;
		
		System.out.println(maximo);
		
		a = x++ * (b += c);
	}
}

package com.ipartek.bases.colecciones;

import java.util.HashSet;

public class HashSetEjemplo {
	public static void main(String[] args) {
		var hs = new HashSet<String>();
		
		hs.add("Uno");
		hs.add("Dos");
		hs.add("Tres");
		hs.add("Cuatro");
		hs.add("Tres");
		
		System.out.println(hs);
		
		var iterator = hs.iterator();
		
		while(iterator.hasNext()) {
			String dato = iterator.next();
			
			System.out.println(dato);
		}
		
		for(String dato: hs) {
			System.out.println(dato);
		}
		
		hs.forEach(dato -> System.out.println(dato));
		
		hs.forEach(System.out::println);
	}
}

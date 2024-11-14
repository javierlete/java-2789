package com.ipartek.bases.colecciones;

import java.util.TreeMap;

public class TreeMapEjemplo {
	public static void main(String[] args) {
		var dic = new TreeMap<String, String>();
		
		dic.put("House", "Casa");
		dic.put("Dog", "Perro");
		dic.put("Teacher", "Profesor");
		
		System.out.println(dic);
		
		System.out.println(dic.keySet());
		
		System.out.println(dic.values());
		
		System.out.println(dic.entrySet());
		
		System.out.println(dic.get("Dog"));
		
		for(var par: dic.entrySet()) {
			System.out.println(par.getKey() + ": " + par.getValue());
		}
	}
}

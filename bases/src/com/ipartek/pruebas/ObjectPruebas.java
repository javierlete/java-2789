package com.ipartek.pruebas;

public class ObjectPruebas {
	public static void main(String[] args) {
		var o = new Object();
		
		System.out.println(o);
		
		System.out.println(o.equals(null));
		
		System.out.println(o.toString());
		
		System.out.println(o.getClass().getName());
		
		System.out.println(Integer.toHexString(o.hashCode()));
	}
}

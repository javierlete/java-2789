package com.ipartek.pruebas;

import com.ipartek.pojos.Punto;

public class PuntoPruebas {
	public static void main(String[] args) {
		var p = new Punto();
		
		System.out.println(p);
		
		p.setX(5);
		p.setY(10);
		
		System.out.println(p);
		
		var p2 = new Punto(p);
		
		p2.setX(1000);
		
		System.out.println(p);
		System.out.println(p2);
		
		System.out.println(Punto.X_ORIGEN_COORDENADAS);
		
		System.out.println(Punto.sumar(p, p2));
		
		System.out.println(p.sumar(p2));
	}
}

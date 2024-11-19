package com.ipartek.pruebas;

import com.ipartek.pojos.Poligono;
import com.ipartek.pojos.Punto;

public class PoligonoPruebas {
	public static void main(String[] args) {
		var poligono = new Poligono(1L, "Prueba");
		
		poligono.getPuntos().add(new Punto(1, 2));
		poligono.getPuntos().add(new Punto(6, 8));
		poligono.getPuntos().add(new Punto(4, 5));
		poligono.getPuntos().add(new Punto(3, 5));
		poligono.getPuntos().add(new Punto(5, 7));
		
		System.out.println(poligono);
	}
}

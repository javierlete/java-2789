package com.ipartek.pruebas;

import java.time.LocalDate;

import com.ipartek.pojos.Local;
import com.ipartek.pojos.Persona;

public class LocalPruebas {
	public static void main(String[] args) {
		Local l = new Local(1L, "Ipartek", new Persona(1L, "Javier Lete", LocalDate.of(2000,  1, 1)));
		
		l.entrarPersona(new Persona());
		l.entrarPersona(new Persona("Pepe", LocalDate.now()));
		l.entrarPersona(new Persona(2L, "Juan", LocalDate.now()));
//		l.entrarPersona(null); //l.getPersonas().add(null);
		
		System.out.println(l);
		
		l.getPersonas().forEach(System.out::println);
		
		for(var persona: l.getPersonas()) {
			persona.setNombre("Cambiado");
		}
		
		l.getPersonas().forEach(System.out::println);
		
	}
}

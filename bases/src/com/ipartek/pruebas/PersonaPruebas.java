package com.ipartek.pruebas;

import java.time.LocalDate;

import com.ipartek.pojos.Persona;

public class PersonaPruebas {
	public static void main(String[] args) {
		var p = new Persona();
		
		System.out.println(p.getId());
		System.out.println(p.getNombre());
		System.out.println(p.getFechaNacimiento());
		
		p.setNombre("    Pepe Pérez Martínez     ");
		p.setFechaNacimiento(LocalDate.of(2024, 11, 18));

		System.out.println(p.getNombre());
		System.out.println(p.getFechaNacimiento());
		
		var p1 = new Persona(1L, "Javier Lete", LocalDate.of(2000, 11, 18));

		System.out.println(p1);
		System.out.println(p1.getEdad());
		
		System.out.println(Persona.getEdad(LocalDate.of(1950, 1, 2)));
		
		var p2 = new Persona(p1);
		
		p2.setId(2L);
		p2.setNombre("Otro Otrez");
		
		System.out.println(p1);
		System.out.println(p2);
	}
}

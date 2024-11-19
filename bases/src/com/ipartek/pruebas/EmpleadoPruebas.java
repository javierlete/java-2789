package com.ipartek.pruebas;

import java.time.LocalDate;

import com.ipartek.pojos.Empleado;
import com.ipartek.pojos.Local;
import com.ipartek.pojos.Persona;

public class EmpleadoPruebas {
	public static void main(String[] args) {
		var e = new Empleado("Pepe", LocalDate.of(2000, 1, 2), "12345678A", "1234-1234-1234-1234");

		e.setDni("12345678A");

		System.out.println(e.getEdad());

		Persona p = e;

		Object o = p;

		System.out.println(((Empleado) o).getNombre());

		System.out.println(o);

		if (o instanceof Empleado e2) {
			System.out.println(e2.getNombre());
		} else {
			System.out.println("No es un empleado");
		}

		Object o3 = new Object();

		if (o3 instanceof Empleado) {
			var e3 = (Empleado) o3;
			System.out.println(e3);
		} else {
			System.out.println("No es un empleado");
		}
		
		Local local = new Local(1L, "Puzles", e);
		
		local.entrarPersona(e);
		local.entrarPersona(new Persona("asdfasdf", LocalDate.now()));
		
		local.getPersonas().forEach(System.out::println);
	}
}

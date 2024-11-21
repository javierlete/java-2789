package com.ipartek.pruebas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.pojos.Empleado;
import com.ipartek.pojos.EmpleadoIndefinido;
import com.ipartek.pojos.EmpleadoPorHoras;
import com.ipartek.pojos.Local;
import com.ipartek.pojos.Persona;

public class EmpleadoPruebas {
	private static final String FICHERO_BINARIO = "fichero.dat";

	public static void main(String[] args) {
		var e = new EmpleadoIndefinido(1L, "Pepe", LocalDate.of(2000, 1, 2), "12345678A", "1234-1234-1234-1234", new BigDecimal("12345.67"), 14);

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
		
		Empleado e1 = new EmpleadoPorHoras(2L, "Clon", LocalDate.of(2000, 1, 1), "12345678A", "1234123412341234", 40, new BigDecimal("12.34"));
		Empleado e2 = new EmpleadoPorHoras(2L, "Clon", LocalDate.of(2000, 1, 1), "12345678A", "1234123412341234", 40, new BigDecimal("12.34"));
		
		Local local = new Local(1L, "Puzles", e);
		
		local.entrarPersona(e);
		local.entrarPersona(new Persona("asdfasdf", LocalDate.now()));
		local.entrarPersona(e1);
		local.entrarPersona(e2);
		
		System.out.println(e1 == e2);
		System.out.println(e1.equals(e2));
		
		System.out.println(e1.hashCode());
		System.out.println(e2.hashCode());
		
		local.getPersonas().forEach(System.out::println);
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(Persona persona: local.getPersonas()) {
			if(persona instanceof Empleado empleado) {
				total = total.add(empleado.getSueldoMensual());
			}
		}
		
		System.out.println(total);
		
		try (var fos = new FileOutputStream(FICHERO_BINARIO);
				var oos = new ObjectOutputStream(fos)) {
			oos.writeObject(local);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		Local local2;
		try (var fis = new FileInputStream(FICHERO_BINARIO);
				var ois = new ObjectInputStream(fis)) {
			local2 = (Local)ois.readObject();
			
			System.out.println(local2);
			
			local2.getPersonas().forEach(System.out::println);
		} catch (ClassNotFoundException | IOException e3) {
			e3.printStackTrace();
		}
	}
}

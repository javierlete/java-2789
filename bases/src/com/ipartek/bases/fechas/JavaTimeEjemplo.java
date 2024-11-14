package com.ipartek.bases.fechas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Java 1.8
public class JavaTimeEjemplo {
	public static void main(String[] args) {
		LocalDateTime ld = LocalDateTime.of(2024, 11, 30, 8, 15, 0);
		
		System.out.println(ld);
		
		LocalDateTime finCurso = ld.plusMonths(3);
		
		System.out.println(finCurso);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MMMM/yyyy HH:mm");
		
		System.out.println(dtf.format(ld));
		
		String texto = "1/febrero/2025 13:37";
		
		LocalDateTime ldt = LocalDateTime.parse(texto, dtf);
		
		System.out.println(ldt);
		
		System.out.println(ldt.getMonthValue());
	}
}

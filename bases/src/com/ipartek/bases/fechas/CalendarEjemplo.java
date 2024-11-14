package com.ipartek.bases.fechas;

import java.util.Calendar;
import java.util.GregorianCalendar;

// Java 1.1
public class CalendarEjemplo {
	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar(2024, 11 - 1, 14);
		
		System.out.println(gc.get(Calendar.YEAR));
		System.out.println(gc.get(Calendar.MONTH) + 1);
	}
}

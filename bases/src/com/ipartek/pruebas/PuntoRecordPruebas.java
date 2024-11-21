package com.ipartek.pruebas;

import com.ipartek.pojos.PuntoRecord;

public class PuntoRecordPruebas {
	public static void main(String[] args) {
		PuntoRecord pr = new PuntoRecord(1, 2);
		PuntoRecord pr2 = new PuntoRecord(1, 2);
		
		System.out.println(pr.x());
		System.out.println(pr.y());
		
		System.out.println(pr.getX());
		
		System.out.println(pr.hashCode());
		System.out.println(pr2.hashCode());
		
		System.out.println(pr == pr2);
		System.out.println(pr.equals(pr2));
		
		System.out.println(pr);
	}
}

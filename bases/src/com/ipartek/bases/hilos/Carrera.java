package com.ipartek.bases.hilos;

public class Carrera {
	public static void main(String[] args) {
		Corredor c1 = new Corredor("UNO");
		Corredor c2 = new Corredor("DOS");
		
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		
		t1.start();
		t2.start();
	}
}

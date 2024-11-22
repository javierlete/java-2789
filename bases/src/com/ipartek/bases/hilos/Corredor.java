package com.ipartek.bases.hilos;

import java.util.Random;

public class Corredor implements Runnable {
	private String dorsal;
	private int posicion = 0;
	
	public Corredor(String dorsal) {
		this.dorsal = dorsal;
	}
	
	public int getPosicion() {
		return posicion;
	}
	
	public String getDorsal()  {
		return dorsal;
	}

	@Override
	public void run() {
		for(posicion = 0; posicion <= 100; posicion++) {
			System.out.printf("%s: %s\n", dorsal, posicion);
			
			try {
				Thread.sleep(200 / new Random().nextInt(1, 5));
			} catch (InterruptedException e) {
				// No necesitamos reaccionar ante la interrupción del Thread
				// ya que es exactamente lo que queremos
			}
		}
		
	}
}

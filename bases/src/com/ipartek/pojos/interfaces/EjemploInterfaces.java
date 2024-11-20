package com.ipartek.pojos.interfaces;

import java.util.ArrayList;

public class EjemploInterfaces {
	public static void main(String[] args) {
		var saco = new ArrayList<Rodable>();
		
		saco.add(new Naranja());
		saco.add(new Balon());
		
		for(Rodable r: saco) {
			if(r instanceof Comestible c) {
				c.comer();
			}
			
			r.rodar();
			
			if(r instanceof Comestible c) {
				c.comer();
			}
		}
	}
}

interface Rodable { void rodar(); }
interface Comestible { void comer(); }

class ObjetoDeporte {}
class Fruto {}

class Balon extends ObjetoDeporte implements Rodable {
	@Override
	public void rodar() {
		System.out.println("Balón rodando");
	}
}

class Naranja extends Fruto implements Comestible, Rodable {
	private boolean porElSuelo;
	
	@Override
	public void rodar() {
		porElSuelo = true;
		
		System.out.println("Naranja rodando");
	}

	@Override
	public void comer() {
		if(porElSuelo) {
			System.out.println("¡Qué asco!");
		} else {
			System.out.println("Ñam que rica");
		}
	}
	
}

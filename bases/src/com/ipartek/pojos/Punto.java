package com.ipartek.pojos;

public class Punto {
	public static final int X_ORIGEN_COORDENADAS = 0;
	public static final int Y_ORIGEN_COORDENADAS = 0;

	private Long id;
	private Integer x;
	private Integer y;

	public Punto(Long id, Integer x, Integer y) {
		setId(id);
		setX(x);
		setY(y);
	}
	
	public Punto(Punto punto) {
		this(punto.getId(), punto.getX(), punto.getY());
	}
	
	public Punto(Integer x, Integer y) {
		this(null, x, y);
	}
	
	public Punto() {
		this(null, X_ORIGEN_COORDENADAS, Y_ORIGEN_COORDENADAS);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		if (x == null || x < 0) {
			throw new IllegalArgumentException("La x no puede ser negativa y es obligatoria");
		}
		
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		if(y == null || y < 0) {
			throw new IllegalArgumentException("La y no puede ser negativa y es obligatoria");
		}
		
		this.y = y;
	}

	/**
	 * Devuelve la suma de este punto con el otro proporcionado
	 * (No modifica el objeto)
	 * @param otro el otro punto a sumar
	 * @return un punto nuevo con la suma total
	 */
	public Punto sumar(Punto otro) {
		return sumar(this, otro);
	}
	
	/**
	 * Devuelve la suma de dos puntos según sus coordenadas
	 * @param a primer punto
	 * @param b segundo punto
	 * @return un punto nuevo independiente de los anteriores
	 */
	public static Punto sumar(Punto a, Punto b) {
//		return new Punto(null, a.x + b.x, a.y + b.y);
		return new Punto(null, a.getX() + b.getX(), a.getY() + b.getY());
	}

	public static Punto sumar(Punto a, Punto b, Punto c) {
		return sumar(sumar(a, b), c);
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s, %s)", id, x, y);
	}

}

package com.ipartek.almacen.presentacion;

import static com.ipartek.bibliotecas.Consola.*;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.accesodatos.DaoProductoTreeMap;
import com.ipartek.almacen.pojos.Producto;

public class AlmacenConsola {
	private static final int SALIR = 0;

	private static final DaoProducto DAO = DaoProductoTreeMap.getInstancia();

	private static final boolean SIN_ID = false;

	private static final boolean CON_ID = true;
	
	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);
		} while (opcion != SALIR);
	}

	private static void mostrarMenu() {
		pl("""

				MENU
				====
				1. Listado
				2. Buscar por id
				3. Buscar por nombre
				4. Buscar por precio
				5. Añadir
				6. Modificar
				7. Borrar

				0. Salir

				""");
	}

	private static int pedirOpcion() {
		return leerEntero("Selecciona una opción");
	}

	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listado();
		case 2 -> buscarPorId();
		case 3 -> buscarPorNombre();
		case 4 -> buscarPorPrecio();
		case 5 -> anyadir();
		case 6 -> modificar();
		case 7 -> borrar();
		case 0 -> pl("Gracias por usar nuestra aplicación");
		default -> pl("No existe esa opción");
		}
	}

	private static void listado() {
		var productos = DAO.obtenerTodos();
		
		mostrarListadoProductos(productos);
	}

	private static void mostrarListadoProductos(Iterable<Producto> productos) {
		productos.forEach(p -> mostrarLineaProducto(p));
	}

	private static void mostrarLineaProducto(Producto producto) {
		pl(producto);
	}

	private static void buscarPorId() {
		var id = leerLong("Dime el id");
		
		var producto = DAO.obtenerPorId(id);
		
		mostrarFichaProducto(producto);
	}

	private static void mostrarFichaProducto(Producto producto) {
		pl(producto);
	}

	private static void buscarPorNombre() {
		var nombre = leerString("Dime el nombre a buscar", REQUERIDO);
		
		var productos = DAO.obtenerPorNombreParcial(nombre);
		
		mostrarListadoProductos(productos);
	}

	private static void buscarPorPrecio() {
		var minimo = leerBigDecimal("Dime el precio mínimo");
		var maximo = leerBigDecimal("Dime el precio máximo");
		
		var productos = DAO.obtenerPorPrecio(minimo, maximo);
		
		mostrarListadoProductos(productos);
	}

	private static void anyadir() {
		var producto = pedirProducto(SIN_ID);
		
		DAO.insertar(producto);
	}

	private static void modificar() {
		var producto = pedirProducto(CON_ID);
		
		DAO.modificar(producto);
	}

	private static Producto pedirProducto(boolean conId) {
		Long id = null;
		
		if(conId) {
			id = leerLong("Id");
		}
		
		var nombre = leerString("Nombre");
		var precio = leerBigDecimal("Precio");
		var fecha = leerFecha("Fecha de caducidad");
		
		var producto = new Producto(id, nombre, precio, fecha);
		
		return producto;
	}

	private static void borrar() {
		var id = leerLong("Dime el id a borrar");
		
		DAO.borrar(id);
	}
}

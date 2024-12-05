package com.ipartek.almacen.controladores.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/producto")
public class ProductoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(ProductoAdminServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición

		String sId = request.getParameter("id");

		// Convertir los datos

		if (sId != null) {

			Long id = Long.parseLong(sId);

			// Empaquetarlos en objetos
			// Ejecutar la lógica de negocio

			var producto = Fabrica.getAdminNegocio().buscarProductoPorId(id);

			// Empaquetar datos para la pantalla

			request.setAttribute("producto", producto);
		}

		request.setAttribute("hoy", LocalDate.now());
		
		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");
		String fechaCaducidad = request.getParameter("fecha");
		
		// Convertir los datos
		// Empaquetarlos en objetos
		
		var producto = new Producto(id, nombre, precio, fechaCaducidad);
		
		// Ejecutar la lógica de negocio
		
		if(!producto.isCorrecto()) {
			LOG.info(producto.toString());
			
			request.setAttribute("producto", producto);
			
			request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
			
			return;
		}
		
		var negocio = Fabrica.getAdminNegocio();
		
		if(producto.getId() == null) {
			negocio.altaProducto(producto);
		} else {
			negocio.modificarProducto(producto);
		}
		
		// Empaquetar datos para la pantalla
		// Mostrar la siguiente pantalla
		
		response.sendRedirect(request.getContextPath() + "/admin/index");
	}
}

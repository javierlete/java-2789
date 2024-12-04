package com.ipartek.almacen.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

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

		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición
		
		String sId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String sPrecio = request.getParameter("precio");
		String sFecha = request.getParameter("fecha");
		
		// Convertir los datos
		
		Long id = sId.isBlank() ? null: Long.parseLong(sId);
		BigDecimal precio = new BigDecimal(sPrecio);
		LocalDate fechaCaducidad = sFecha.isBlank() ? null : LocalDate.parse(sFecha);
		
		// Empaquetarlos en objetos
		
		var producto = new Producto(id, nombre, precio, fechaCaducidad);
		
		// Ejecutar la lógica de negocio
		
		var negocio = Fabrica.getAdminNegocio();
		
		if(id == null) {
			negocio.altaProducto(producto);
		} else {
			negocio.modificarProducto(producto);
		}
		
		// Empaquetar datos para la pantalla
		// Mostrar la siguiente pantalla
		
		response.sendRedirect(request.getContextPath() + "/admin/index");
	}
}

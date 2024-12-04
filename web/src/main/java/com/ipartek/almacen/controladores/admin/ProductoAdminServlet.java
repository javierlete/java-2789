package com.ipartek.almacen.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

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

			var producto = new Producto(id, "Producto " + id, new BigDecimal(1234), LocalDate.now());

			// Empaquetar datos para la pantalla

			request.setAttribute("producto", producto);
		}
		
		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}
}

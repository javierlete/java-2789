package com.ipartek.almacen.controladores.admin;

import java.io.IOException;

import com.ipartek.almacen.fabrica.Fabrica;

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

			var producto = Fabrica.getUsuarioNegocio().buscarProductoPorId(id); 

			// Empaquetar datos para la pantalla

			request.setAttribute("producto", producto);
		}
		
		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}
}

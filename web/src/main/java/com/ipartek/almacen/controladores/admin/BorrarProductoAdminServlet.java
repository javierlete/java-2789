package com.ipartek.almacen.controladores.admin;

import java.io.IOException;

import com.ipartek.almacen.fabrica.Fabrica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/producto/borrar")
public class BorrarProductoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición

		String sId = request.getParameter("id");

		// Convertir los datos

		Long id = Long.parseLong(sId);

		// Empaquetarlos en objetos
		// Ejecutar la lógica de negocio

		Fabrica.getAdminNegocio().borrarProducto(id);

		// Empaquetar datos para la pantalla
		// Mostrar la siguiente pantalla
		
		response.sendRedirect(request.getContextPath() + "/admin/index");
	}
}

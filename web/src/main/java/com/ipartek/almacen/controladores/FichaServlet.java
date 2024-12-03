package com.ipartek.almacen.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipartek.almacen.fabrica.Fabrica;

@WebServlet("/ficha")
public class FichaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recibir datos de petición
		
		String sId = request.getParameter("id");
		
		// Convertir los datos
		
		Long id = Long.parseLong(sId);
		
		// Empaquetarlos en objetos
		// Ejecutar la lógica de negocio
		
		var producto = Fabrica.getUsuarioNegocio().buscarProductoPorId(id);
		
		// Empaquetar datos para la pantalla
		
		request.setAttribute("producto", producto);
		
		// Mostrar la siguiente pantalla

		request.getRequestDispatcher("/WEB-INF/vistas/ficha.jsp").forward(request, response);
	}
}

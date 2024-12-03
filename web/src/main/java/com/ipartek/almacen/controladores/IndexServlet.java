package com.ipartek.almacen.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipartek.almacen.fabrica.Fabrica;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recibir datos de petición
		// Convertir los datos
		// Empaquetarlos en objetos
		// Ejecutar la lógica de negocio
		
		var productos = Fabrica.getUsuarioNegocio().verProductos();
		
		// Empaquetar datos para la pantalla
		
		request.setAttribute("productos", productos);
		
		// Mostrar la siguiente pantalla

		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
	}
}

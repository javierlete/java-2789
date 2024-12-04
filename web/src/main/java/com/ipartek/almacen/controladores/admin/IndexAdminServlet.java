package com.ipartek.almacen.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ipartek.almacen.pojos.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/index")
public class IndexAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición
		// Convertir los datos
		// Empaquetarlos en objetos
		// Ejecutar la lógica de negocio
		var productos = new ArrayList<Producto>();

		for (int i = 1; i <= 5; i++) {
			productos.add(new Producto((long)i, "Producto " + i, new BigDecimal(10 * i), LocalDate.of(2000 + i, i, i * 2)));
		}

		// Empaquetar datos para la pantalla
		
		request.setAttribute("productos", productos);
		
		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/admin/index.jsp").forward(request, response);
	}
}

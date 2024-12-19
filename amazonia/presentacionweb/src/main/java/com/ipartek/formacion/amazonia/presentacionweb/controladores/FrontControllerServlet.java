package com.ipartek.formacion.amazonia.presentacionweb.controladores;

import static com.ipartek.formacion.amazonia.presentacionweb.controladores.Globales.*;

import java.io.IOException;

import com.ipartek.formacion.amazonia.entidades.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fc/*")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;

	private String[] partes;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;

		String pathInfo = request.getPathInfo() == null ? "/" : request.getPathInfo();

		partes = pathInfo.split("/");

		// response.getWriter().println(Arrays.toString(partes));

		if (partes.length == 0) {
			index();
			return;
		}

		switch (partes[1]) {
		case "detalle" -> detalle();
		default -> response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void detalle() throws ServletException, IOException {
		// Recibir información de la petición
		String sId = request.getParameter("id");

		Producto producto;
		
		if (sId != null) {
			// Convertir
			Long id = Long.parseLong(sId);

			// Empaquetar en modelo
			// Lógica de negocio
			producto = anonimoNegocio.detalleProducto(id);
		} else {
			producto = anonimoNegocio.detalleProducto(partes[2]);
		}
		
		// Empaquetar información para la vista
		request.setAttribute("producto", producto);

		// Ir a la vista
		reenviar("/detalle.jsp");
	}

	private void index() throws ServletException, IOException {
		var productos = anonimoNegocio.listarProductos();

		request.setAttribute("productos", productos);
		reenviar("/index.jsp");
	}

	private void reenviar(String vista) throws ServletException, IOException {
		request.getRequestDispatcher(VISTAS + vista).forward(request, response);
	}
}

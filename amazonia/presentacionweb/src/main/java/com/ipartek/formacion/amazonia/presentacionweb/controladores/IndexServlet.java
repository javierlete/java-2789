package com.ipartek.formacion.amazonia.presentacionweb.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ipartek.formacion.amazonia.presentacionweb.controladores.Globales.*;

@WebServlet("/fc/*")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		switch(request.getPathInfo()) {
		case "/" -> index();
		default -> response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
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

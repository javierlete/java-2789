package com.ipartek.formacion.ejemplos.maven.appweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ejemplo")
public class EjemploMvcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("coleccion", new Integer[] {1, 2, 3, 4});
		
		request.getRequestDispatcher("ejemplo.jsp").forward(request, response);
	}
}

package com.ipartek.almacen.presentacion;

import java.io.IOException;
import java.io.PrintWriter;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.negocio.UsuarioNegocio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		UsuarioNegocio negocio = Fabrica.getUsuarioNegocio();
		
		String listado = "";
		
		for(var p: negocio.verProductos()) {
			listado += "<li>" + p.getNombre() + "</li>\n";
		}
		
		out.printf("""
				<!DOCTYPE html>
				<html>
				<head>
					<title>Productos</title>
				</head>
				<body>
					<ul>
						%s
					</ul>
				</body>
				</html>
				
				""", listado);
	}
}

package com.ipartek.almacen.presentacion;

import java.io.IOException;
import java.io.PrintWriter;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.negocio.UsuarioNegocio;
import com.ipartek.almacen.pojos.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String sId = request.getParameter("id");
		String nombre = request.getParameter("nombre");

		UsuarioNegocio negocio = Fabrica.getUsuarioNegocio();

		String incrustacion = "";

		if (sId == null) {
			Iterable<Producto> productos;
			
			if(nombre == null) {
				productos = negocio.verProductos();
			} else {
				productos = negocio.buscarProductosPorNombre(nombre);
			}
			
			incrustacion += "<ul>\n";
			
			for (var p : productos) {
				incrustacion += "<li>" + p.getNombre() + "</li>\n";
			}
			
			incrustacion += "</ul>\n";
		} else {
			var producto = negocio.buscarProductoPorId(Long.parseLong(sId));
			
			incrustacion += "<dl>\n";

			
			incrustacion += "<dt>Nombre</dt><dd>" + producto.getNombre() + "</dd>\n";
			incrustacion += "<dt>Precio</dt><dd>" + producto.getPrecio() + "</dd>\n";
			incrustacion += "<dt>Fecha de caducidad</dt><dd>" + producto.getFechaCaducidad() + "</dd>\n";
			
			
			incrustacion += "</dl>\n";
		}
		
		out.printf("""
				<!DOCTYPE html>
				<html>
				<head>
					<title>Productos</title>
				</head>
				<body>
					%s
				</body>
				</html>
				""", incrustacion);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

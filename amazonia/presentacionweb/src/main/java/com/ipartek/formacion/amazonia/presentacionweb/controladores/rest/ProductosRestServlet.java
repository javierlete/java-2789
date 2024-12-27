package com.ipartek.formacion.amazonia.presentacionweb.controladores.rest;

import java.io.IOException;

import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.presentacionweb.controladores.Globales;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/productos/*")
public class ProductosRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Jsonb jsonb = JsonbBuilder.create();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		var id = obtenerIdDePath(request);
		
		if (id != null) {
			var producto = Globales.adminNegocio.detalleProducto(id);

			if(producto == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			var json = jsonb.toJson(producto);
			
			response.getWriter().write(json);
			return;
		}
		
		var productos = Globales.adminNegocio.listarProductos();

		var json = jsonb.toJson(productos);

		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var producto = jsonb.fromJson(request.getInputStream(), Producto.class);
		
		var errores = Globales.adminNegocio.validarProducto(producto);
		
		if(errores.size() > 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(jsonb.toJson(errores));
			return;
		}
		
		producto = Globales.adminNegocio.anadirProducto(producto);
		
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().write(jsonb.toJson(producto));
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var id = obtenerIdDePath(request);
		var producto = jsonb.fromJson(request.getInputStream(), Producto.class);
		
		if(id != producto.getId()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		var errores = Globales.adminNegocio.validarProducto(producto);
		
		if(errores.size() > 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(jsonb.toJson(errores));
			return;
		}
		
		producto = Globales.adminNegocio.modificarProducto(producto);
		
		response.getWriter().write(jsonb.toJson(producto));
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var id = obtenerIdDePath(request);
		
		Globales.adminNegocio.borrarProducto(id);
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	private Long obtenerIdDePath(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();

		if (pathInfo != null && pathInfo.length() > 1) {
			String sId = pathInfo.substring(1);
			
			Long id = Long.parseLong(sId);
			
			return id;
		}

		return null;
	}

}

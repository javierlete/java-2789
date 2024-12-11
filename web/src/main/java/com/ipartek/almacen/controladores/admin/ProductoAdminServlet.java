package com.ipartek.almacen.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Categoria;
import com.ipartek.almacen.pojos.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/producto")
public class ProductoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(ProductoAdminServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición

		String sId = request.getParameter("id");

		// Convertir los datos

		if (sId != null) {

			Long id = Long.parseLong(sId);

			// Empaquetarlos en objetos
			// Ejecutar la lógica de negocio

			var producto = Fabrica.getAdminNegocio().buscarProductoPorId(id);

			// Empaquetar datos para la pantalla

			request.setAttribute("producto", producto);
		}

		request.setAttribute("hoy", LocalDate.now());
		request.setAttribute("categorias", Fabrica.getAdminNegocio().verCategorias());
		
		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibir datos de petición
		
		String sId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String sPrecio = request.getParameter("precio");
		String sFechaCaducidad = request.getParameter("fecha");
		String sCategoria = request.getParameter("categoria");
		
		// Convertir los datos
		var id = sId.isBlank() ? null : Long.parseLong(sId);
		var precio = sPrecio.isBlank() ? null : new BigDecimal(sPrecio);
		var fechaCaducidad = sFechaCaducidad.isBlank() ? null: LocalDate.parse(sFechaCaducidad);
		
		var lCategoria = Long.parseLong(sCategoria);
		var categoria = lCategoria == 0 ? null : Categoria.builder().id(lCategoria).build();
		
		// Empaquetarlos en objetos
		
		var producto = Producto.builder().id(id).nombre(nombre).precio(precio).fechaCaducidad(fechaCaducidad).categoria(categoria).build();
		
		// Ejecutar la lógica de negocio
		
		var negocio = Fabrica.getAdminNegocio();

		if(!negocio.validar(producto)) {
			LOG.info(producto.toString());
			
			request.setAttribute("producto", producto);
			request.setAttribute("errores", negocio.errores(producto));
			
			request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
			
			return;
		}
		
		if(producto.getId() == null) {
			negocio.altaProducto(producto);
		} else {
			negocio.modificarProducto(producto);
		}
		
		// Empaquetar datos para la pantalla
		// Mostrar la siguiente pantalla
		
		response.sendRedirect(request.getContextPath() + "/admin/index");
	}
}

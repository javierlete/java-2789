package com.ipartek.formacion.amazonia.presentacionweb.controladores;

import static com.ipartek.formacion.amazonia.presentacionweb.controladores.Globales.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.entidades.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lombok.extern.java.Log;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@Log
@WebServlet("/fc/*")
public class FrontControllerServlet extends HttpServlet {
	private static final String RUTA_IMGS = "/imgs/";

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	private String[] partes;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.session = request.getSession();

		String pathInfo = request.getPathInfo() == null ? "/" : request.getPathInfo();

		partes = pathInfo.split("/");

		log.info(Arrays.toString(partes));

		if (partes.length == 0) {
			index();
			return;
		}

		switch (partes[1]) {
		case "detalle" -> detalle();
		case "login" -> login();
		case "logout" -> logout();
		case "admin" -> admin();
		default -> notFound();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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

	private void login() throws ServletException, IOException {
		String error = request.getParameter("error-login");

		if(error != null) {
			request.setAttribute("errorLogin", error);
		}
		
		if (esPost()) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			var usuario = Usuario.builder().email(email).password(password).build();

			var usuarioAutenticado = anonimoNegocio.iniciarSesion(usuario);

			if (usuarioAutenticado != null) {
				session.setAttribute("usuario", usuarioAutenticado);

				redirigir("/");
				return;
			} else {
				request.setAttribute("usuario", usuario);
				request.setAttribute("errorLogin", "Usuario o contraseña incorrectos");
			}

		}

		// Atributo que marca a la cabecera para lanzar un JavaScript que visualice el modal de Login
		request.setAttribute("login", "");

		reenviar("/index.jsp");
	}

	private void logout() throws IOException {
		session.invalidate();

		redirigir("/");
	}

	private void admin() throws ServletException, IOException {
		if (partes.length < 3) {
			adminProductos();
			return;
		}

		switch (partes[2]) {
		case "producto" -> adminProducto();
		case "producto-borrar" -> adminProductoBorrar();
		case "productos-borrar" -> adminProductosBorrar();
		default -> notFound();
		}
	}

	private void adminProducto() throws ServletException, IOException {
		if (esPost()) {
			// POST
			String sId = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String sPrecio = request.getParameter("precio");
			String url = request.getParameter("url");
			String descripcion = request.getParameter("descripcion");

			Long id = sId.isBlank() ? null : Long.parseLong(sId);
			BigDecimal precio = sPrecio.isBlank() ? null : new BigDecimal(sPrecio);

			Producto producto = Producto.builder().id(id).nombre(nombre).precio(precio).url(url)
					.descripcion(descripcion).build();

			var errores = adminNegocio.validarProducto(producto);
			
			if(errores.size() > 0) {
				request.setAttribute("errores", errores);
				request.setAttribute("producto", producto);
				
				reenviar("/admin/producto.jsp");
				return;
			}
			
			Producto productoConfirmado;

			if (producto.getId() == null) {
				productoConfirmado = adminNegocio.anadirProducto(producto);
			} else {
				productoConfirmado = adminNegocio.modificarProducto(producto);
			}

			// https://www.baeldung.com/upload-file-servlet
			String rutaImagenes = obtenerRutaImagenes();

			Part parteImagen = request.getPart("imagen");

			if (!parteImagen.getSubmittedFileName().isBlank()) {
				parteImagen.write(rutaImagenes + productoConfirmado.getId() + ".jpg");
			}

			redirigir("/admin");
		} else {
			// GET
			String sId = request.getParameter("id");
			if (sId != null) {
				var id = Long.parseLong(sId);

				var producto = adminNegocio.detalleProducto(id);

				request.setAttribute("producto", producto);
			}

			reenviar("/admin/producto.jsp");
		}
	}

	private void adminProductoBorrar() throws IOException {
		String sId = request.getParameter("id");
	
		Long id = Long.parseLong(sId);
	
		adminNegocio.borrarProducto(id);
	
		borrarImagen(id);
	
		redirigir("/admin/");
	}

	private void adminProductosBorrar() throws IOException {
		String[] sIds = request.getParameterValues("id");

		List<Long> ids = Arrays.stream(sIds).map(s -> Long.parseLong(s)).toList();

		adminNegocio.borrarProductos(ids);

		for(Long id: ids) {
			borrarImagen(id);
		}
		
		redirigir("/admin/");
	}

	private void adminProductos() throws ServletException, IOException {
		var productos = adminNegocio.listarProductos();

		request.setAttribute("productos", productos);
		reenviar("/admin/productos.jsp");
	}

	private void reenviar(String vista) throws ServletException, IOException {
		request.getRequestDispatcher(VISTAS + vista).forward(request, response);
	}

	private void redirigir(String ruta) throws IOException {
		response.sendRedirect(request.getContextPath() + "/fc" + ruta);
	}

	private boolean esPost() {
		return "POST".equals(request.getMethod());
	}

	private void notFound() {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	private String obtenerRutaImagenes() {
		return getServletContext().getRealPath(RUTA_IMGS);
	}

	private void borrarImagen(Long id) {
		String rutaImagenes = obtenerRutaImagenes();
	
		File ficheroBorrar = new File(rutaImagenes + id + ".jpg");
	
		ficheroBorrar.delete();
	}

}

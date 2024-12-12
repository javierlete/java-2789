package com.ipartek.almacen.controladores;

import java.io.IOException;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recibir datos de petición
		// Convertir los datos
		// Empaquetarlos en objetos
		// Ejecutar la lógica de negocio
		// Empaquetar datos para la pantalla
		// Mostrar la siguiente pantalla
		
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recibir datos de petición
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Convertir los datos
		// Empaquetarlos en objetos
		
		var usuario = Usuario.builder().email(email).password(password).build();
		
		// Ejecutar la lógica de negocio
		var usuarioLogueado = Fabrica.getUsuarioNegocio().autenticar(usuario);
		
		if(usuarioLogueado != null) {
			HttpSession session = request.getSession();
			
			// Empaquetar datos para la pantalla
			session.setAttribute("usuario", usuarioLogueado);
			
			// Mostrar la siguiente pantalla
			response.sendRedirect(request.getContextPath() + "/admin/");
			
			return;
		}
		
		// Empaquetar datos para la pantalla
		request.setAttribute("errores", "El usuario o la contraseña son incorrectos");
		
		// Mostrar la siguiente pantalla
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		
	}
}

package com.ipartek.formacion.amazonia.presentacionweb.filtros;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/fc/admin/*")
public class AdminFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 8851366991483082378L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var req = (HttpServletRequest) request;
		var res = (HttpServletResponse) response;

		var session = req.getSession();
		var usuario = session.getAttribute("usuario");

		if (usuario == null) {
			res.sendRedirect(req.getContextPath() + "/fc/login");
			return;
		}

		chain.doFilter(request, response);
	}
}

<%@page import="com.ipartek.almacen.fabrica.Fabrica"%>
<%@page import="com.ipartek.almacen.negocio.UsuarioNegocio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% var nombre = request.getParameter("nombre"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscador</title>
</head>
<body>
	<h1>Buscar por nombre</h1>

	<form action="" method="post">
		<input type="search" name="nombre" placeholder="Texto a buscar"
			value="<%= nombre != null ? nombre : "" %>">
		<button>Buscar</button>
	</form>

	<ul>

		<%
		UsuarioNegocio negocio = Fabrica.getUsuarioNegocio();

		if (nombre != null) {
			var productos = negocio.buscarProductosPorNombre(nombre);

			for (var producto : productos) {
		%>
		<li><%=producto%></li>
		<%
		}
		}
		%>
	</ul>

</body>
</html>
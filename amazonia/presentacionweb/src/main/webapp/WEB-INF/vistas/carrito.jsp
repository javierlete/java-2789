<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>Producto</th>
			<th>Precio</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${sessionScope.carrito.lineas}" var="l">
			<tr>
				<td>${l.producto.nombre}</td>
				<td>${l.producto.precio}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
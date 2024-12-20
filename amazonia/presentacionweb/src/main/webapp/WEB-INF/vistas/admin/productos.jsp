<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table table-striped table-hover table-bordered">
	<caption>Productos</caption>
	<thead class="table-dark">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th>Url</th>
			<th>OPCIONES</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${productos}" var="p">
			<tr>
				<th>${p.id}</th>
				<td>${p.nombre}</td>
				<td>${p.precio}</td>
				<td>${p.url}</td>
				<td><a class="btn btn-sm btn-primary" href="fc/admin/producto?id=${p.id}">Editar</a> <a
					class="btn btn-sm btn-danger" href="fc/admin/producto-borrar?id=${p.id}">Borrar</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot class="table-dark">
		<tr>
			<td colspan="4"></td>
			<td><a class="btn btn-sm btn-primary" href="fc/admin/producto">Añadir</a></td>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
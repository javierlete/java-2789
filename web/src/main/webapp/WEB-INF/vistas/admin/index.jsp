<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<table class="table table-bordered table-hover table-striped">
	<caption>Productos</caption>
	
	<thead class="table-dark">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th>Fecha de caducidad</th>
			<th>OPCIONES</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${productos}" var="p">
		<tr>
			<th>${p.id}</th>
			<td>${p.nombre}</td>
			<td>${p.precio}</td>
			<td>${p.fechaCaducidad}</td>
			<td>
				<a href="producto?id=${p.id}" class="btn btn-primary btn-sm">Editar</a>
				<a onclick="javascript:return confirm('¿Estás seguro de que quieres borrar ${p.nombre}?')" href="producto/borrar?id=${p.id}" class="btn btn-danger btn-sm">Borrar</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	
	<tfoot class="table-dark">
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<a href="producto" class="btn btn-primary btn-sm">Añadir</a>
			</td>
		</tr>
	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
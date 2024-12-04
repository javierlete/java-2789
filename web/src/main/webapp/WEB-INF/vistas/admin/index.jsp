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
		<tr>
			<th>1</th>
			<td>Un producto</td>
			<td>1234,56€</td>
			<td>2000-01-02</td>
			<td>
				<a href="#" class="btn btn-primary btn-sm">Editar</a>
				<a href="#" class="btn btn-danger btn-sm">Borrar</a>
			</td>
		</tr>
		<tr>
			<th>1</th>
			<td>Un producto</td>
			<td>1234,56€</td>
			<td>2000-01-02</td>
			<td>
				<a href="#" class="btn btn-primary btn-sm">Editar</a>
				<a href="#" class="btn btn-danger btn-sm">Borrar</a>
			</td>
		</tr>
	</tbody>
	
	<tfoot class="table-dark">
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<a href="#" class="btn btn-primary btn-sm">Añadir</a>
			</td>
		</tr>
	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
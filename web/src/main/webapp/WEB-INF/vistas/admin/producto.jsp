<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<p>${producto}</p>
<p>${errores}</p>

<form action="admin/producto" method="post" class="needs-validation"
	novalidate>
	<div class="row mb-3">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm-10">
			<input type="number" readonly class="form-control" id="id" name="id"
				value="${producto.id}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm-10">
			<input type="text" required class="form-control ${errores.nombre != null ? 'is-invalid' : '' }" id="nombre"
				name="nombre" value="${producto.nombre}">
			<div class="invalid-feedback">El nombre no se puede dejar vacío</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="precio" class="col-sm-2 col-form-label">Precio</label>
		<div class="col-sm-10">
			<input type="number" required min="0" step=".01" class="form-control ${errores.precio != null ? 'is-invalid' : '' }"
				id="precio" name="precio" value="${producto.precio}">
			<div class="invalid-feedback">El precio es obligatorio y no puede ser negativo</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="fecha" class="col-sm-2 col-form-label">Fecha de
			caducidad</label>
		<div class="col-sm-10">
			<input type="date" min="${hoy}" class="form-control ${errores.fecha != null ? 'is-invalid' : '' }" id="fecha"
				name="fecha" value="${producto.fechaCaducidad}">
			<div class="invalid-feedback">La fecha debe ser posterior o igual a la actual</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="categoria" class="col-sm-2 col-form-label">Categoria</label>
		<div class="col-sm-10">
			<select class="form-select ${errores.categoria != null ? 'is-invalid' : '' }" id="categoria"
				name="categoria">
				<option value="0">Ninguna</option>
				<c:forEach items="${categorias}" var="c">
					<option value="${c.id}" ${c.id == producto.categoria.id ? 'selected' : ''}>${c.nombre}</option>
				</c:forEach>
			</select>
			<div class="invalid-feedback"></div>
		</div>
	</div>

	<button type="submit" class="btn btn-primary">Guardar</button>

	<%--
	<ul>
		<c:forEach items="${producto.errores.values()}" var="error">
			<li>${error}</li>
		</c:forEach>
	</ul>
	--%>
</form>

<script src="js/validacion.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
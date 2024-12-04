<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form method="post">
	<div class="row mb-3">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm-10">
			<input type="number" readonly class="form-control" id="id" name="id" value="${producto.id}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="nombre" name="nombre" value="${producto.nombre}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="precio" class="col-sm-2 col-form-label">Precio</label>
		<div class="col-sm-10">
			<input type="number" step=".01" class="form-control" id="precio" name="precio" value="${producto.precio}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="fecha" class="col-sm-2 col-form-label">Fecha de caducidad</label>
		<div class="col-sm-10">
			<input type="date" class="form-control" id="fecha" name="fecha" value="${producto.fechaCaducidad}">
		</div>
	</div>
	
	<button type="submit" class="btn btn-primary">Guardar</button>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
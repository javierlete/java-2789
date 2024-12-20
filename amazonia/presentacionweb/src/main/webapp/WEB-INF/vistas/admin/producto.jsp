<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="fc/admin/producto" method="post">
	<%-- <p>${producto}</p> --%>

	<div class="row mb-3">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm-10">
			<input readonly class="form-control" id="id" name="id"
				value="${producto.id}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="nombre" name="nombre"
				value="${producto.nombre}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="precio" class="col-sm-2 col-form-label">Precio</label>
		<div class="col-sm-10">
			<input type="number" step=".01" class="form-control" id="precio"
				name="precio" value="${producto.precio}">
		</div>
	</div>
	<div class="row mb-3">
		<label for="url" class="col-sm-2 col-form-label">URL</label>
		<div class="col-sm-10">
			<input type="url" class="form-control" id="url"
				name="url" value="${producto.url}">
		</div>
	</div>
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm-10">
			<button class="btn btn-primary">Guardar</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
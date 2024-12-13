<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<form action="registro" method="post">
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm-10">
			<input type="text" required class="form-control" id="nombre"
				name="nombre" value="">
		</div>
	</div>
	<div class="row mb-3">
		<label for="email" class="col-sm-2 col-form-label">Email</label>
		<div class="col-sm-10">
			<input type="email" required class="form-control" id="email"
				name="email" value="">
		</div>
	</div>
	<div class="row mb-3">
		<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
		<div class="col-sm-10">
			<input type="password" required class="form-control" id="password"
				name="password" value="">
		</div>
	</div>

	<button type="submit" class="btn btn-primary">Guardar</button>
	<div class="text-danger">${errores}</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
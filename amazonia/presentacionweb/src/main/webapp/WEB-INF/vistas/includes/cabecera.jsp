<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<fmt:setLocale value="es-ES" />
<!DOCTYPE html>
<html lang="es">
<head>

<base href="${pageContext.request.contextPath}/">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Amazonia</title>
<link rel="icon" href="imgs/cart4.svg">
<link href="css/bootstrap.min.css" rel="stylesheet">

<script src="js/bootstrap.bundle.min.js"></script>
<c:if test="${login != null}">
	<script>
		window.addEventListener("DOMContentLoaded", () => new bootstrap.Modal('#login').show());
	</script>
</c:if>

</head>
<body>

	<nav class="navbar navbar-expand-lg bg-dark sticky-top"
		data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="index">Amazonia</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="index">Principal</a></li>
				</ul>
				<ul class="navbar-nav mb-2 mb-lg-0">
					<c:if test="${sessionScope.usuario != null}">
						<li class="nav-item"><a class="nav-link" href="fc/admin/">Administración</a></li>
					</c:if>

					<c:if test="${sessionScope.usuario != null}">
						<li class="navbar-text">${sessionScope.usuario.nombre}</li>
					</c:if>

					<c:choose>
						<c:when test="${sessionScope.usuario != null}">
							<li class="nav-item"><a class="nav-link" href="fc/logout">Cerrar
									sesión</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="fc/registro">Registro</a></li>
							<li class="nav-item"><a class="nav-link"
								data-bs-toggle="modal" href="#login">Iniciar sesión</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Modal -->
	<div class="modal fade" id="login" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="fc/login" method="post" class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Iniciar
						sesión</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="row mb-3">
						<label for="email" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email" name="email"
								value="${requestScope.usuario.email}">
						</div>
					</div>
					<div class="row mb-3">
						<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password"
								name="password">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<c:if test="${requestScope.usuario != null}">
						<div class="text-danger">Usuario o contraseña incorrectos</div>
					</c:if>
					<button type="submit" class="btn btn-primary">Iniciar
						sesión</button>
				</div>
			</form>
		</div>
	</div>

	<%="<main class='container mt-3 mb-5 pb-5'>"%>
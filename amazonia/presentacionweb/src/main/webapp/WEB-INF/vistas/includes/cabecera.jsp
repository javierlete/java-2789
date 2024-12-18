<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!doctype html>
<html lang="es">
<head>

<base href="${pageContext.request.contextPath}/">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Amazonia</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
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
					<c:if test="${sessionScope.usuario.rol.nombre == 'ADMIN' }">
						<li class="nav-item"><a class="nav-link" href="admin/">Administración</a></li>
					</c:if>
					
					<c:if test="${sessionScope.usuario != null}">
						<li class="navbar-text">${sessionScope.usuario.nombre}</li>
					</c:if>

					<c:choose>
						<c:when test="${sessionScope.usuario != null}">
							<li class="nav-item"><a class="nav-link" href="logout">Cerrar
									sesión</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="registro">Registro</a></li>
							<li class="nav-item"><a class="nav-link" href="login">Iniciar
									sesión</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<%="<main class='container mt-3 mb-5 pb-5'>"%>
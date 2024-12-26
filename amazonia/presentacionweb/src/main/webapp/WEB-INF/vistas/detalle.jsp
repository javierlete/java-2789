<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="card mb-3">
	<div class="row g-0">
		<div class="col-md-4">
			<img src="imgs/${producto.id}.jpg" class="img-fluid rounded-start" alt="...">
		</div>
		<div class="col-md-8">
			<div class="card-body">
				<h5 class="card-title">${producto.nombre}</h5>
				<div class="card-text">${producto.descripcion}</div>
				<p class="card-text">
					<a href="fc/carrito/anadir?id=${producto.id}" class="btn btn-primary">Añadir al carrito</a>
				</p>
				<p class="card-text">
					<small class="text-body-secondary">
						<fmt:formatNumber type="currency" value="${producto.precio}"/>
					</small>
				</p>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>Producto</th>
			<th class="text-end">Precio</th>
			<th class="text-end">Cantidad</th>
			<th class="text-end">Total</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${sessionScope.carrito.lineas}" var="l">
			<tr>
				<td>${l.producto.nombre}</td>
				<td class="text-end"><fmt:formatNumber type="currency"
						value="${l.producto.precio}" /></td>
				<td>
					<div class="input-group ms-auto" style="width: 9rem">
						<a href="#" class="btn btn-secondary">
							<i class="bi bi-dash-lg"></i>
						</a>
						<input class="form-control text-end" type="number" readonly
							min="1" value="${l.cantidad}">
						<a href="fc/carrito/anadir?id=${l.producto.id}" class="btn btn-secondary">
							<i class="bi bi-plus-lg"></i>
						</a>
					</div>
				</td>
				<td class="text-end"><fmt:formatNumber type="currency"
						value="${l.total}" /></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2"></td>
			<th class="text-end fw-bold">Subtotal</th>
			<td class="text-end fw-bold"><fmt:formatNumber type="currency"
					value="${carrito.total}" /></td>
		</tr>
		<tr>
			<td colspan="2"></td>
			<th class="text-end fw-bold">IVA</th>
			<td class="text-end fw-bold"><fmt:formatNumber type="currency"
					value="${carrito.iva}" /></td>
		</tr>
		<tr>
			<td colspan="2"></td>
			<th class="text-end fw-bold">Total</th>
			<td class="text-end fw-bold"><fmt:formatNumber type="currency"
					value="${carrito.totalConIva}" /></td>
		</tr>
	</tfoot>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
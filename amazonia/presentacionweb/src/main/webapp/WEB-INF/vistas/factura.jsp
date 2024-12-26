<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div>
	<p>${factura.numero}</p>
	<p>${factura.fecha}</p>
</div>

<div>
	<p>${factura.nif}</p>
	<p>${factura.nombre}</p>
</div>

<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>Producto</th>
			<th class="text-end">Precio</th>
			<th class="text-end">Cantidad</th>
			<th class="text-end">Total</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${factura.lineas}" var="l">
			<tr>
				<td>${l.nombre}</td>
				<td class="text-end"><fmt:formatNumber type="currency"
						value="${l.precio}" /></td>
				<td>${l.cantidad}</td>
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
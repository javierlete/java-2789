package com.ipartek.almacen.negocio;

import java.util.HashMap;
import java.util.Map;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	private static final DaoProducto DAO_PRODUCTO = Fabrica.getDaoProducto();
	private static final ValidatorFactory FABRICA_VALIDACION = Validation.buildDefaultValidatorFactory();

	@Override
	public Producto altaProducto(Producto producto) {
		if (validar(producto)) {
			return DAO_PRODUCTO.insertar(producto);
		} else {
			throw new NegocioException("El producto no es válido");
		}
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		if (validar(producto)) {
			return DAO_PRODUCTO.modificar(producto);
		} else {
			throw new NegocioException("El producto no es válido");
		}
	}

	@Override
	public void borrarProducto(Long id) {
		DAO_PRODUCTO.borrar(id);
	}
	
	@Override
	public boolean validar(Producto producto) {
		return errores(producto).isEmpty();
	}

	@Override
	public Map<String, String> errores(Producto producto) {
		var validator = FABRICA_VALIDACION.getValidator();

		var constraintViolations = validator.validate(producto);
		
		var errores = new HashMap<String, String>();
		
		constraintViolations.stream().forEach(cv -> errores.put(cv.getPropertyPath().toString(), cv.getMessage()));
		
		return errores;
	}
}

package com.ipartek.formacion.amazonia.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;
import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.fabrica.Fabrica;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class AdminNegocioImpl extends AnonimoNegocioImpl implements AdminNegocio {
	private static final ProductoDao PRODUCTO_DAO = Fabrica.getProductoDao();

	private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

	@Override
	public Producto anadirProducto(Producto producto) {
		return PRODUCTO_DAO.insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return PRODUCTO_DAO.modificar(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		PRODUCTO_DAO.borrar(id);
	}

	@Override
	public void borrarProductos(List<Long> ids) {
		PRODUCTO_DAO.borrar(ids);
	}

	@Override
	public Map<String, String> validarProducto(Producto producto) {
		var validator = validatorFactory.getValidator();

		Set<ConstraintViolation<Producto>> constraintViolations = validator.validate(producto);

		HashMap<String, String> errores = new HashMap<String, String>();

		for (ConstraintViolation<Producto> constraintViolation : constraintViolations) {
			String propiedad = constraintViolation.getPropertyPath().toString();
			String mensaje = constraintViolation.getMessage();

			if (errores.containsKey(propiedad)) {
				errores.put(propiedad, errores.get(propiedad) + ", " + mensaje);
			} else {
				errores.put(propiedad, mensaje);
			}
		}

		return errores;
	}

}

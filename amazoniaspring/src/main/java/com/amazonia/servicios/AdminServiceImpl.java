package com.amazonia.servicios;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonia.entidades.Producto;
import com.amazonia.repositorios.ProductoRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private ProductoRepository productoRepo;
	
	@Override
	public Producto anadirProducto(Producto producto) {
		return productoRepo.save(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return productoRepo.save(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		productoRepo.deleteById(id);
	}

	@Override
	public void borrarProductos(List<Long> ids) {
		productoRepo.deleteAllById(ids);
	}

	@Override
	public Map<String, String> validarProducto(Producto producto) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

}

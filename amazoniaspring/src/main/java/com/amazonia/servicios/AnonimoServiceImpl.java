package com.amazonia.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.amazonia.entidades.Producto;
import com.amazonia.repositorios.ProductoRepository;

@Primary
@Service
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	private ProductoRepository productoRepo;
	
	@Override
	public Iterable<Producto> listarProductos() {
		return productoRepo.findAll();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return productoRepo.findById(id).orElse(null);
	}

	@Override
	public Producto detalleProducto(String url) {
		return productoRepo.findByUrl(url);
	}

}

package com.amazonia.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.amazonia.entidades.Producto;
import com.amazonia.entidades.Usuario;
import com.amazonia.entidades.gruposvalidacion.UsuarioRegistro;
import com.amazonia.repositorios.ProductoRepository;
import com.amazonia.repositorios.RolRepository;
import com.amazonia.repositorios.UsuarioRepository;

@Primary
@Service
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private RolRepository rolRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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

	@Override
	public void registrarUsuario(@Validated(UsuarioRegistro.class) Usuario usuario) {
		usuario.setRol(rolRepo.findByNombre("USER"));

		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		usuarioRepo.save(usuario);
	}

}

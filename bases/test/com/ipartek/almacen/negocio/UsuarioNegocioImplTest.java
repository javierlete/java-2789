package com.ipartek.almacen.negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.accesodatos.DaoRol;
import com.ipartek.almacen.accesodatos.DaoUsuario;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;
import com.ipartek.almacen.pojos.Rol;
import com.ipartek.almacen.pojos.Usuario;

class UsuarioNegocioImplTest {
	private static final String JDBC_URL = "jdbc:sqlite:bases-jpa-test.db?foreign_keys=on";

	private static Rol rol1 = Rol.builder().nombre("ADMIN").descripcion("Administración").build();
	private static Rol rol2 = Rol.builder().nombre("USER").descripcion("Usuarios").build();

	private static Usuario usuario1 = Usuario.builder().email("javier@email.net").password("javier").nombre("Javier")
			.build();
	private static Usuario usuario2 = Usuario.builder().email("pepe@email.net").password("pepe").nombre("Pepe").build();
	private static Usuario usuario3 = Usuario.builder().id(3L).nombre("Nuevo").email("nuevo@email.net").password("nuevo")
			.build();

	private static Usuario USUARIO_NUEVO_EMAIL_EXISTENTE = Usuario.builder().email("javier@email.net").password("javierasdfasd").nombre("Javier asdfasdf")
			.build();

	private static Usuario USUARIO_NUEVO = Usuario.builder().nombre("Nuevo").email("nuevo@email.net").password("nuevo")
			.build();

	private static Producto producto1 = Producto.builder().nombre("Portátil").precio(new BigDecimal("1234.12")).build();
	private static Producto producto2 = Producto.builder().nombre("Monitor").precio(new BigDecimal("123.12")).build();
	private static Producto producto3 = Producto.builder().nombre("Ratón").precio(new BigDecimal("12.12")).build();
	private static final DaoUsuario daoUsuario = Fabrica.getDaoUsuario();
	private static final DaoRol daoRol = Fabrica.getDaoRol();
	private static final DaoProducto daoProducto = Fabrica.getDaoProducto();

	private static final String SQL_BORRADO_USUARIOS = "DELETE FROM usuarios";
	private static final String SQL_BORRADO_PRODUCTOS = "DELETE FROM productos";
	private static final String SQL_BORRADO_ROLES = "DELETE FROM roles";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		rol1.setId(null);
		rol2.setId(null);
		
		usuario1.setId(null);
		usuario2.setId(null);
		
		producto1.setId(null);
		producto2.setId(null);
		producto3.setId(null);
		
		rol1 = daoRol.insertar(rol1);
		rol2 = daoRol.insertar(rol2);
		
		usuario1.setRol(rol1);
		usuario2.setRol(rol2);
		usuario3.setRol(rol2);
		
		daoUsuario.insertar(usuario1);
		daoUsuario.insertar(usuario2);
		
		daoProducto.insertar(producto1);
		daoProducto.insertar(producto2);
		daoProducto.insertar(producto3);
	}

	@AfterEach
	void tearDown() throws Exception {
		Connection con = DriverManager.getConnection(JDBC_URL);
		Statement st = con.createStatement();
		st.executeUpdate(SQL_BORRADO_USUARIOS);
		st.executeUpdate(SQL_BORRADO_PRODUCTOS);
		st.executeUpdate(SQL_BORRADO_ROLES);
	}

	UsuarioNegocio negocio = Fabrica.getUsuarioNegocio();

	@Test
	void testBuscarProductoPorId() {
		var producto = negocio.buscarProductoPorId(1L);

		assertNotNull(producto);
		assertEquals(producto1, producto);

		producto = negocio.buscarProductoPorId(100L);

		assertNull(producto);

		assertThrows(NegocioException.class, () -> negocio.buscarProductoPorId(null));
	}

	@Test
	void testAutenticar() {
		var usuarioCorrecto = Usuario.builder().email("javier@email.net").password("javier").build();

		var javier = usuario1;

		var resultado = negocio.autenticar(usuarioCorrecto);

		assertNotNull(resultado);
		assertEquals(javier, resultado);

		var usuarioPasswordIncorrecto = Usuario.builder().email("javier@email.net").password("incorrecta").build();

		resultado = negocio.autenticar(usuarioPasswordIncorrecto);

		assertNull(resultado);

		var usuarioEmailIncorrecto = Usuario.builder().email("email@incorrecto.net").password("incorrecta").build();

		resultado = negocio.autenticar(usuarioEmailIncorrecto);

		assertNull(resultado);
	}

	@Test
	void testRegistro() {
		var usuario = negocio.registrar(USUARIO_NUEVO);

		assertNotNull(usuario);
		assertEquals(usuario3, usuario);
		
		assertThrows(NegocioException.class, () -> negocio.registrar(USUARIO_NUEVO_EMAIL_EXISTENTE));
		assertThrows(NegocioException.class, () -> negocio.registrar(Usuario.builder().build()));
		assertThrows(NegocioException.class, () -> negocio.registrar(Usuario.builder().email("").nombre("").password("").build()));
		
		
	}
}

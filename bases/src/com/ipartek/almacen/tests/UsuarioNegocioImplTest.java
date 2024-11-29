package com.ipartek.almacen.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.negocio.NegocioException;
import com.ipartek.almacen.negocio.UsuarioNegocio;
import com.ipartek.almacen.pojos.Producto;

class UsuarioNegocioImplTest {

	private static final Producto PRODUCTO1 = new Producto(1L, "Portátil", new BigDecimal("1234.12"), null);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	UsuarioNegocio negocio = Fabrica.getUsuarioNegocio();
	
	@Test
	void testBuscarProductoPorId() {
		var producto = negocio.buscarProductoPorId(1L);
		
		assertNotNull(producto);
		assertEquals(PRODUCTO1, producto);
		
		producto = negocio.buscarProductoPorId(100L);
		
		assertNull(producto);
		
		assertThrows(NegocioException.class, () -> negocio.buscarProductoPorId(null));
	}

}

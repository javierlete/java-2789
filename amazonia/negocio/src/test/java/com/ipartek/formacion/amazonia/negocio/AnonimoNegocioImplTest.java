package com.ipartek.formacion.amazonia.negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ipartek.formacion.amazonia.entidades.Usuario;

class AnonimoNegocioImplTest {

	static String url = "jdbc:sqlite:amazonia-test.db?foreign_keys=on";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String sql = """
				DELETE FROM usuarios WHERE EXISTS (
					SELECT name FROM sqlite_master 
					WHERE 
						type='table' AND name='usuarios'
				);
				""";
		
		var con = DriverManager.getConnection(url);
		var st = con.createStatement();
		
		st.execute(sql);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	
	@BeforeEach
	void setUp() throws Exception {
		String sql = """
				INSERT INTO usuarios (cliente_id,id,password,email,nombre) VALUES
	 (NULL,1,'javier','javier@email.net','Javier'),
	 (NULL,2,'pepe','pepe@email.net','Pepe'),
	 (NULL,3,'juan','juan@email.net','Juan');
				""";
		
		var con = DriverManager.getConnection(url);
		var st = con.createStatement();
		
		st.execute(sql);
	}

	@AfterEach
	void tearDown() throws Exception {
		setUpBeforeClass();
	}

	static AnonimoNegocio negocio = new AnonimoNegocioImpl();

	static Usuario USUARIO1 = Usuario.builder().id(1L).nombre("Javier").email("javier@email.net").password("javier").build();
	static Usuario USUARIO_LOGIN = Usuario.builder().email("javier@email.net").password("javier").build();
	static Usuario USUARIO_LOGIN_USUARIO_INCORRECTO = Usuario.builder().email("incorrecto@email.net").password("incorrecta").build();
	static Usuario USUARIO_LOGIN_PASSWORD_INCORRECTO = Usuario.builder().email("javier@email.net").password("incorrecta").build();

	@Test
	void testIniciarSesion() {
		var usuarioCompleto = negocio.iniciarSesion(USUARIO_LOGIN);
		
		assertNotNull(usuarioCompleto);
		assertEquals(USUARIO1, usuarioCompleto);
		
		var usuarioIncorrectoUsuario = negocio.iniciarSesion(USUARIO_LOGIN_USUARIO_INCORRECTO);
		
		assertNull(usuarioIncorrectoUsuario);

		var usuarioIncorrectoPassword = negocio.iniciarSesion(USUARIO_LOGIN_PASSWORD_INCORRECTO);
		
		assertNull(usuarioIncorrectoPassword);
	}

}

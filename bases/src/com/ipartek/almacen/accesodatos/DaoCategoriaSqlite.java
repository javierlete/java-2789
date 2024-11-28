package com.ipartek.almacen.accesodatos;

import com.ipartek.almacen.pojos.Categoria;

public class DaoCategoriaSqlite extends DaoCategoriaJdbc implements DaoCategoria {

	private static final String SQL_SELECT = "SELECT * FROM categorias";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";

	public DaoCategoriaSqlite(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	public Iterable<Categoria> obtenerTodos() {
		return ejecutarConsulta(SQL_SELECT, pst -> {});
	}

	@Override
	public Categoria obtenerPorId(Long id) {
		return ejecutarConsultaUno(SQL_SELECT_ID, pst -> datosSentencia(pst, id));
	}
	
	
}

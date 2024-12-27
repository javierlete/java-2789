package com.ipartek.formacion.amazonia.presentacionweb.controladores;

import com.ipartek.formacion.amazonia.negocio.AdminNegocio;
import com.ipartek.formacion.amazonia.negocio.AdminNegocioImpl;
import com.ipartek.formacion.amazonia.negocio.AnonimoNegocio;
import com.ipartek.formacion.amazonia.negocio.AnonimoNegocioImpl;
import com.ipartek.formacion.amazonia.negocio.UsuarioNegocio;
import com.ipartek.formacion.amazonia.negocio.UsuarioNegocioImpl;

public class Globales {
	public static final String VISTAS = "/WEB-INF/vistas";
	public static final AnonimoNegocio anonimoNegocio = new AnonimoNegocioImpl();
	public static final UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	public static final AdminNegocio adminNegocio = new AdminNegocioImpl();
}

package com.ipartek.formacion.amazonia.presentacionweb.controladores;

import com.ipartek.formacion.amazonia.negocio.AdminNegocio;
import com.ipartek.formacion.amazonia.negocio.AdminNegocioImpl;
import com.ipartek.formacion.amazonia.negocio.AnonimoNegocio;
import com.ipartek.formacion.amazonia.negocio.AnonimoNegocioImpl;

class Globales {
	static final String VISTAS = "/WEB-INF/vistas";
	static final AnonimoNegocio anonimoNegocio = new AnonimoNegocioImpl();
	static final AdminNegocio adminNegocio = new AdminNegocioImpl();
}

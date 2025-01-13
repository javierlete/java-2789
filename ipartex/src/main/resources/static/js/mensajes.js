'use strict';

// Definimos constantes globales
const URL = '/api/';
const URL_MENSAJES = URL + 'mensajes';

// Esperamos a la carga de todo el documento en el DOM
window.addEventListener('DOMContentLoaded', cargado);

// Código a ejecutar cuando el DOM esté cargado
async function cargado() {
	// Llamada a servicio REST
	const respuesta = await fetch(URL_MENSAJES);
	const mensajes = await respuesta.json();
	
	// Buscamos el elemento raíz al que colgar cada uno de los elementos
	const ul = document.querySelector('ul');
	
	for(const mensaje of mensajes) {
		// Creamos un elemento LI	
		const li = document.createElement('li');
		
		// Lo rellenamos con los datos en su HTML interno
		li.innerHTML = `${mensaje.id}: ${mensaje.texto}`;
		
		// Colgamos el elemento recién creado del UL
		ul.appendChild(li);
	}
}
'use strict';

// Definimos constantes globales
const URL = '/api/';
const URL_MENSAJES = URL + 'mensajes';

let mensajesForm;

// Esperamos a la carga de todo el documento en el DOM
window.addEventListener('DOMContentLoaded', domCargado);

// Código a ejecutar cuando el DOM esté cargado
function domCargado() {
	// Buscamos el formulario de mensajes
	mensajesForm = document.querySelector('#vista-mensajes form');

	// Enlazamos el evento de envío de mensaje
	mensajesForm.addEventListener('submit', envioMensaje);

	// Refrescamos lista de mensajes
	refrescarLista();
}

async function megusta(id) {
	const respuesta = await fetch(`${URL_MENSAJES}/${id}/megusta`);

	if (respuesta.ok) {

		const a = document.querySelector(`#m${id} a:first-of-type`);
		const i = a.querySelector('i');
		const span = a.nextElementSibling;

		let numeroMeGustas = +span.innerText;

		if (i.classList.contains('bi-heart')) {
			i.classList.remove('bi-heart');
			i.classList.add('bi-heart-fill');

			numeroMeGustas++;
		} else {
			i.classList.add('bi-heart');
			i.classList.remove('bi-heart-fill');

			numeroMeGustas--;
		}

		span.innerText = numeroMeGustas;
	} else {
		alert('Tienes que estar logueado para dar me gusta');
	}
}

async function envioMensaje(evento) {
	// Evita que se envíe el formulario al action
	evento.preventDefault();

	const texto = mensajesForm.texto.value;

	const respuesta = await fetch(`${URL_MENSAJES}?texto=${texto}`, { method: 'POST' });

	if (respuesta.ok) {
		const mensaje = await respuesta.json();

		console.log(JSON.stringify(mensaje));
		
		mensajesForm.reset();
		
		refrescarLista();
	} else {
		alert('Error');
	}
}

async function refrescarLista() {
	// Llamada a servicio REST
	const respuesta = await fetch(URL_MENSAJES);
	const mensajes = await respuesta.json();

	// Buscamos el elemento raíz al que colgar cada uno de los elementos
	const divPadre = document.querySelector('#mensajes');
	
	divPadre.innerHTML = '';
	
	for (const mensaje of mensajes) {
		// Creamos un elemento div
		const div = document.createElement('div');

		// Le ponemos ciertos atributos
		div.id = 'm' + mensaje.id;
		div.className = 'list-group-item list-group-item-action';

		// Lo rellenamos con los datos en su HTML interno
		div.innerHTML = `
				<div class="row">
					<div class="col-auto">
						<img class="rounded-circle" src="/imgs/${mensaje.idUsuario}.jpg"
							width="50">
					</div>
					<div class="col">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">
								<span>${mensaje.nombreUsuario}</span> <small
									class="text-body-secondary"
									>@${mensaje.nombreUsuarioIpartex}</small>
							</h5>
							<small>${mensaje.tiempoVidaTexto}</small>
						</div>
						<p class="mb-1">${mensaje.texto}</p>
						<small> 
							<a href="javascript:megusta(${mensaje.id})"><i class="bi bi-heart${mensaje.leGusta ? '-fill' : ''}"></i></a>

							<span>${mensaje.numeroMeGustas}</span> <a
							href="#"><i
								class="bi bi-chat"></i></a> <span>${mensaje.numeroRespuestas}</span>
						</small>
					</div>
				</div>
				`;

		// Colgamos el elemento recién creado del UL
		divPadre.appendChild(div);
	}
}
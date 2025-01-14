'use strict';

// Definimos constantes globales
const URL = '/api/';
const URL_MENSAJES = URL + 'mensajes';

let vistaMensajes, vistaRespuestas, mensajesForm, respuestasForm;

// Esperamos a la carga de todo el documento en el DOM
window.addEventListener('DOMContentLoaded', domCargado);

// Código a ejecutar cuando el DOM esté cargado
function domCargado() {
	vistaMensajes = document.querySelector('#vista-mensajes');
	vistaRespuestas = document.querySelector('#vista-respuestas');
	
	// Buscamos el formulario de mensajes
	mensajesForm = document.querySelector('#vista-mensajes form');

	// Enlazamos el evento de envío de mensaje
	mensajesForm.addEventListener('submit', envioMensaje);

	// Buscamos el formulario de respuestas
	respuestasForm = document.querySelector('#vista-respuestas form');

	// Enlazamos el evento de envío de respuesta
	respuestasForm.addEventListener('submit', envioRespuesta);

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

async function envioRespuesta(evento) {
	// Evita que se envíe el formulario al action
	evento.preventDefault();

	const id = respuestasForm.id.value;
	const texto = respuestasForm.texto.value;

	const respuesta = await fetch(`${URL_MENSAJES}/${id}/respuestas?&texto=${texto}`, { method: 'POST' });

	if (respuesta.ok) {
		const mensaje = await respuesta.json();

		console.log(JSON.stringify(mensaje));
		
		respuestasForm.reset();
		
		refrescarLista(`${URL_MENSAJES}/${id}/respuestas`, '#respuestas');
	} else {
		alert('Error');
	}
}


async function refrescarLista(url = URL_MENSAJES, selector = '#mensajes') {
	// Llamada a servicio REST
	const respuesta = await fetch(url);
	const mensajes = await respuesta.json();

	// Buscamos el elemento raíz al que colgar cada uno de los elementos
	const divPadre = document.querySelector(selector);
	
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
							href="javascript:mostrarRespuestas(${mensaje.id})"><i
								class="bi bi-chat"></i></a> <span>${mensaje.numeroRespuestas}</span>
						</small>
					</div>
				</div>
				`;

		// Colgamos el elemento recién creado del UL
		divPadre.appendChild(div);
	}
}

async function mostrarRespuestas(id) {
	vistaMensajes.style.display = 'none';
	vistaRespuestas.style.display = 'block';
	
	const respuesta = await fetch(`${URL_MENSAJES}/${id}`);
	const mensaje = await respuesta.json();
	
	document.querySelector('#vista-respuestas img').src = `/imgs/${mensaje.idUsuario}.jpg`	
	document.querySelector('#vista-respuestas h5 > span').innerText = mensaje.nombreUsuario;
	document.querySelector('#vista-respuestas h5 > small').innerText = '@' + mensaje.nombreUsuarioIpartex;
	document.querySelector('#vista-respuestas h5 ~ small').innerText = mensaje.tiempoVidaTexto;
	document.querySelector('#vista-respuestas p').innerText = mensaje.texto;
	document.querySelector('#vista-respuestas small>a:first-of-type i').className = mensaje.leGusta ? 'bi bi-heart-fill' : 'bi bi-heart';
	document.querySelector('#vista-respuestas small>span:first-of-type').innerText = mensaje.numeroMeGustas;
	document.querySelector('#vista-respuestas small>span:last-of-type').innerText = mensaje.numeroRespuestas;

	document.querySelector('#vista-respuestas input[name=id]').value = mensaje.id;
	
	refrescarLista(`${URL_MENSAJES}/${id}/respuestas`, '#respuestas');
}
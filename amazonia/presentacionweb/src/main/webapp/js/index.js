window.addEventListener('DOMContentLoaded', documentoCargado);

async function documentoCargado() {
	const ul = document.querySelector('ul');
	
	const respuesta = await fetch('http://www.amazonia.com/api/productos');
	const productos = await respuesta.json();
	
	console.log(productos);
	
	for(const producto of productos) {
		const li = document.createElement('li');
		
		li.innerHTML = `${producto.id}: ${producto.nombre} ${producto.precio}`;
		
		ul.appendChild(li);
	}
}
package net.ostemplate.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.ostemplate.app.productos.models.entity.Producto;
import net.ostemplate.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/lista")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto ->{
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/producto/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(port);
		return producto;
	}
	
	@PostMapping("/producto/nuevo")
	public void insertarProducto (@RequestBody Producto producto) {
		productoService.insertProducto(producto);
	}
	
	@PostMapping("/producto/borrar/{id}")
	public void borrarProducto (@RequestBody Long id) {
		productoService.borrarProducto(id);
	}
	
	@GetMapping("/producto/{nombre}")
	public List<Producto> buscarProductoPorNombre(@PathVariable String nombre){
		return productoService.buscarPorNombre(nombre);
		
		
	}
	@GetMapping("/producto/contiene/{nombre}")
	public List<Producto> buscarProductoPorContieneNombre(@PathVariable String nombre){
		return productoService.buscarPorNombre(nombre);
		
		
	}

}

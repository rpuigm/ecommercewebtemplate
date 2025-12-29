package net.ostemplate.app.productos.controllers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import io.github.classgraph.Resource;
import net.ostemplate.app.productos.models.entity.Producto;
import net.ostemplate.app.productos.models.entity.ProductoCantidad;
import net.ostemplate.app.productos.models.entity.ProductoCaracteristicas;
import net.ostemplate.app.productos.models.entity.ProductoEntity;
import net.ostemplate.app.productos.models.service.FileService;
import net.ostemplate.app.productos.models.service.ProductoServiceI;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

	@InjectMocks
	private ProductoController productoController;

	@Mock
	MultipartFile multiparteFile;

	@Mock
	ProductoServiceI productoServiceI;

	@Mock
	FileService fileService;
	
	@Mock
	InputStream inputStream;

	@Test
	public void subidaImagen() {
		try {
			Mockito.when(productoServiceI.findById(Mockito.anyLong())).thenReturn(mapToProductoDummy());
			Mockito.when(fileService.copyFile(Mockito.any(InputStream.class), Mockito.any(Path.class))).thenReturn(1L);
			Mockito.when(multiparteFile.getInputStream()).thenReturn(inputStream);
			Mockito.when(productoServiceI.modificarProducto(Mockito.any(ProductoEntity.class))).thenReturn(mapToProductoDummy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		productoController.subidaImagen(multiparteFile, 1L, "descripcion");

	}

	@Test
	public void detalleTest(){
		Producto producto = mapToProductoDummy();
		Mockito.when(productoServiceI.findById(Mockito.anyLong()))
			.thenReturn(producto);
		assertEquals(productoController.detalle(1L),producto);

	}

	@Test
	public void listarTest(){
		List<ProductoEntity> listaProductos = new ArrayList<>();
		listaProductos.add(mapToProductoEntityDummy());
		listaProductos.add(mapToProductoEntityDummy());
		Mockito.when(productoServiceI.findAll())
			.thenReturn(listaProductos);
		assertEquals(productoController.listar(),listaProductos);
	}

	@Test
	public void insertarProductoTest(){
		Producto producto = mapToProductoDummy();
		Mockito.when(productoServiceI.insertProducto(Mockito.any(ProductoEntity.class)))
			.thenReturn(producto);
		assertEquals(productoController.insertarProducto(producto),producto);
	}

	@Test
	public void borrarProductoTest(){
		productoController.borrarProducto(1L);
		Mockito.verify(productoServiceI,Mockito.times(1)).borrarProducto(Mockito.anyLong());
	}

	@Test
	public void modificarProductoTest(){
		Producto producto = mapToProductoDummy();
		Mockito.when(productoServiceI.modificarProducto(Mockito.any(ProductoEntity.class)))
			.thenReturn(producto);
		assertEquals(productoController.modificarProducto(producto),producto);
	}
	
	@Test
	public void buscarProductoPorNombreTest(){
		List<ProductoEntity> listaProductos = new ArrayList<>();
		listaProductos.add(mapToProductoEntityDummy());
		listaProductos.add(mapToProductoEntityDummy());
		Mockito.when(productoServiceI.buscarPorNombre(Mockito.anyString()))
			.thenReturn(listaProductos);
		assertEquals(productoController.buscarProductoPorNombre("nombre"),listaProductos);
	}

	@Test
	public void buscarProductoPorContieneNombreTest(){
		List<ProductoEntity> listaProductos = new ArrayList<>();
		listaProductos.add(mapToProductoEntityDummy());
		listaProductos.add(mapToProductoEntityDummy());
		Mockito.when(productoServiceI.buscarPorNombre(Mockito.anyString()))
			.thenReturn(listaProductos);
		assertEquals(productoController.buscarProductoPorContieneNombre("nombre"),listaProductos);
	}

	private ResponseEntity<Resource> mapToResponseEntityDummy() {
		EasyRandom generator = new EasyRandom();
		Resource resource = generator.nextObject(Resource.class);
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=\"" + "foto.jpg" + "\"")
				.body(resource);
	}

	private Producto mapToProductoDummy() {
		EasyRandom generator = new EasyRandom();
		Producto producto = generator.nextObject(Producto.class);
		producto.setProductoCaracteristicas(mapToProductoCaracteristicasDummy());
		return producto;
	}

	private ProductoEntity mapToProductoEntityDummy() {
		EasyRandom generator = new EasyRandom();
		ProductoEntity productoEntity = generator.nextObject(ProductoEntity.class);
		productoEntity.setProductoCaracteristicas(mapToProductoCaracteristicasDummy());
		return productoEntity;
	}

	private ProductoCaracteristicas mapToProductoCaracteristicasDummy() {
		EasyRandom generator = new EasyRandom();
		return generator.nextObject(ProductoCaracteristicas.class);
	}

	private ProductoCantidad mapToProductoCantidadDummy() {
		EasyRandom generator = new EasyRandom();
		return generator.nextObject(ProductoCantidad.class);
	}

	private List<ProductoCantidad> mapToListProductoCantidad() {
		List<ProductoCantidad> listProductoCantidad = new ArrayList<>();
		listProductoCantidad.add(mapToProductoCantidadDummy());
		listProductoCantidad.add(mapToProductoCantidadDummy());
		listProductoCantidad.add(mapToProductoCantidadDummy());
		return listProductoCantidad;
	}
	


}

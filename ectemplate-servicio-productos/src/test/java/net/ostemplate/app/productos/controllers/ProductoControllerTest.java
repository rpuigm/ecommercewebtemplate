package net.ostemplate.app.productos.controllers;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import net.ostemplate.app.productos.models.entity.Producto;
import net.ostemplate.app.productos.models.service.ProductoServiceI;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

	@InjectMocks
	private ProductoController productoController;

	@Mock
	MultipartFile multiparteFile;

	@Mock
	ProductoServiceI productoServiceI;

	@Test
	@Disabled
	public void subidaImagen() {
		Mockito.when(productoServiceI.findById(Mockito.anyLong())).thenReturn(mapToProductoDummy());
		productoController.subidaImagen(multiparteFile, 1L, "descripcion");

	}

	private Producto mapToProductoDummy() {
		EasyRandom generator = new EasyRandom();
		return generator.nextObject(Producto.class);
	}

}

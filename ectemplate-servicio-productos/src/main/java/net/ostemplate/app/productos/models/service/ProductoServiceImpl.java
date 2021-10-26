package net.ostemplate.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ostemplate.app.productos.models.dao.ProductoRepository;
import net.ostemplate.app.productos.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public void insertProducto(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		productoRepository.deleteById(id);
	}

	@Override
	public List<Producto> buscarPorNombre(String nombre) {
		return productoRepository.findByNombre(nombre);
	}

}

package net.ostemplate.app.productos.models.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ostemplate.app.productos.models.dao.ComprasRepository;
import net.ostemplate.app.productos.models.dao.ProductoCantidadRepository;
import net.ostemplate.app.productos.models.entity.Cesta;
import net.ostemplate.app.productos.models.entity.CestaEntity;
import net.ostemplate.app.productos.models.entity.ProductoCantidad;

@Service
public class CestaServiceImpl implements CestaServiceI {

	@Autowired
	private ComprasRepository comprasRepository;
	
	@Autowired
	private ProductoCantidadRepository productoCantidadRepository;

	@Override
	@Transactional
	public CestaEntity guardaCesta(CestaEntity cestaEntity) {
		return comprasRepository.save(cestaEntity);
	}

	@Override
	@Transactional
	public List<CestaEntity> listaCestas() {
		return (List<CestaEntity>) comprasRepository.findAll();
	}

	@Override
	@Transactional
	public Cesta buscarCestaPorUsuarioId(Long idUsuario) {
		return comprasRepository.findByIdUsuario(idUsuario);
	}
	
	@Override
	@Transactional
	public CestaEntity actualizaCesta (CestaEntity cestaEntity) {
		return comprasRepository.save(cestaEntity);
	}
	
	@Override
	@Transactional
	public void eliminaCesta (CestaEntity cestaEntity) {
		comprasRepository.delete(cestaEntity);
	}


	@Override
	@Transactional
	public CestaEntity eliminarDeLaCesta(Long idUsuario, Long idProducto) {
		CestaEntity cestaEntity = comprasRepository.findByIdUsuario(idUsuario);
		List<ProductoCantidad> listaProductoCantidad = cestaEntity.getProductoCantidad();

		if (listaProductoCantidad != null) {
			listaProductoCantidad.stream().filter(producto -> producto.getIdProducto() == idProducto).findAny()
					.map(i -> disminuye(i));

			listaProductoCantidad.removeIf(p -> p.getCantidad() <= 0);

		}

		cestaEntity.setProductoCesta(listaProductoCantidad);
		return guardaCesta(cestaEntity);

	}
	@Override
	@Transactional
	public void eliminarUnProductoDeLasCestas(Long idProducto) {
		productoCantidadRepository.deleteAllByIdProducto(idProducto);
		
	}

	private ProductoCantidad disminuye(ProductoCantidad productoCantidad) {
		productoCantidad.setCantidad(productoCantidad.getCantidad() - 1L);
		return productoCantidad;
	}

}

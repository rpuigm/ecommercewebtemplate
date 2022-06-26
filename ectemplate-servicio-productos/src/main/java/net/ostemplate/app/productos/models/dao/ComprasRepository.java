package net.ostemplate.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import net.ostemplate.app.productos.models.entity.CestaEntity;

public interface ComprasRepository extends CrudRepository<CestaEntity, Long> {
	
	CestaEntity findByIdUsuario(Long idUsuario);

}

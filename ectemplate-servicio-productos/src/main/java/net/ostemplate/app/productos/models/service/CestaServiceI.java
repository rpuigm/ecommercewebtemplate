package net.ostemplate.app.productos.models.service;

import java.util.List;

import net.ostemplate.app.productos.models.entity.Cesta;
import net.ostemplate.app.productos.models.entity.CestaEntity;

public interface CestaServiceI {
	


	Cesta buscarCestaPorUsuarioId(Long idUsuario);

	Cesta eliminarDeLaCesta(Long idUsuario, Long idProducto);

	List<CestaEntity> listaCestas();


	void eliminaCesta(CestaEntity cestaEntity);


	void eliminarUnProductoDeLasCestas(Long idProducto);


	CestaEntity guardaCesta(CestaEntity cestaEntity);

	CestaEntity actualizaCesta(CestaEntity cestaEntity);

}

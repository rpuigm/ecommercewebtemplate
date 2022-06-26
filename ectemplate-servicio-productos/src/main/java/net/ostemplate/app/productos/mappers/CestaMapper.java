package net.ostemplate.app.productos.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import net.ostemplate.app.productos.models.entity.Cesta;
import net.ostemplate.app.productos.models.entity.CestaEntity;

@UtilityClass
public class CestaMapper {

	public Cesta mapToCesta() {
		Cesta cesta = new Cesta();
		cesta.setId(1L);
		cesta.setIdUsuario(1L);
		cesta.setProductoCesta(null);
		return cesta;

	}
	
	public CestaEntity mapToCestaEntityFromCesta(Cesta cesta) {
		CestaEntity cestaEntity = new CestaEntity();
		cestaEntity.setId(cesta.getId());
		cestaEntity.setIdUsuario(cesta.getIdUsuario());
		cestaEntity.setProductoCesta(cesta.getProductoCantidad());
		return cestaEntity;
		
	}
	public Cesta mapToCestaFromCestaEntity(CestaEntity cestaEntity) {
		CestaEntity cesta = new CestaEntity();
		cesta.setId(cestaEntity.getId());
		cesta.setIdUsuario(cestaEntity.getIdUsuario());
		cesta.setProductoCesta(cestaEntity.getProductoCantidad());
		return cestaEntity;
		
	}
	
	public List<Cesta> mapToListCestaFromListCestaEntity(List<CestaEntity> listaCestaEntity){
		List<Cesta> listaCesta = new ArrayList<Cesta>();
		listaCesta = listaCestaEntity.stream()
				.map((cestaEntity)->mapToCestaFromCestaEntity(cestaEntity))
				.collect(Collectors.toList());
		return listaCesta;
		
	}

}
 
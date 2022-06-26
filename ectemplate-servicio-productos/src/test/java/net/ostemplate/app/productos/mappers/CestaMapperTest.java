package net.ostemplate.app.productos.mappers;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import net.ostemplate.app.productos.models.entity.Cesta;
import net.ostemplate.app.productos.models.entity.CestaEntity;

@ExtendWith(MockitoExtension.class)
public class CestaMapperTest {
	
	@Test
	public void mapToListCestaFromListCestaEntityTest() {
		Assert.assertEquals(listCestaDummy(), CestaMapper.mapToListCestaFromListCestaEntity(listCestaEntityDummy()));
	}
	
	private Cesta cestaDummy() {
		EasyRandom easyRandom = new EasyRandom();
		Cesta cesta = easyRandom.nextObject(Cesta.class);
		return cesta;
	}
	
	private CestaEntity cestaEntityDummyFromCestaDummy() {
		return CestaMapper.mapToCestaEntityFromCesta(cestaDummy());
	}
	
	private List<CestaEntity> listCestaEntityDummy(){
		List<CestaEntity> listaCestaEntity = new ArrayList<CestaEntity>();
		listaCestaEntity.add(cestaEntityDummyFromCestaDummy());
		return listaCestaEntity;
	
	}
	
	private List<Cesta> listCestaDummy(){
		List<Cesta> listaCesta = new ArrayList<Cesta>();
		listaCesta.add(cestaDummy());
		return listaCesta;
	
	}
	
	

}

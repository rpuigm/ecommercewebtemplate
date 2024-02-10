package net.ostemplate.app.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.ostemplate.app.productos.mappers.CestaMapper;
import net.ostemplate.app.productos.models.entity.Cesta;
import net.ostemplate.app.productos.models.service.CestaServiceI;

@RestController
public class CestaController {

	@Autowired
	private CestaServiceI cestaServiceI;
	
	@GetMapping("/cesta/recupera-cesta/{idUsuario}")
	public Cesta recuperaCestaPorIdUsuario (@PathVariable Long idUsuario) {
		return cestaServiceI.buscarCestaPorUsuarioId(idUsuario);
	}
	
	
	@PostMapping("/cesta/actualiza-cesta")
	public Cesta actualizaCesta(@RequestBody Cesta cesta) {
		System.out.println("numero productos en cesta"+cesta.getProductoCantidad().size());
		return cestaServiceI.actualizaCesta(CestaMapper.mapToCestaEntityFromCesta(cesta));
	}
	
	@GetMapping("/cesta/listacestas")
	public List<Cesta> listaCestas(){
		return CestaMapper.mapToListCestaFromListCestaEntity(cestaServiceI.listaCestas()) ;
	}
	
	@DeleteMapping("/cesta/elimina-cesta")
	public void recuperaCestaPorIdUsuario (@RequestBody Cesta cesta) {
		cestaServiceI.eliminaCesta(CestaMapper.mapToCestaEntityFromCesta(cesta));
	}
	
}

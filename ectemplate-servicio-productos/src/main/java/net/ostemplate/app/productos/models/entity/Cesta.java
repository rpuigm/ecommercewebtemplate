package net.ostemplate.app.productos.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class Cesta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private Long idUsuario;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_id_cesta")
	private List<ProductoCantidad> productoCantidad;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<ProductoCantidad> getProductoCantidad() {
		return productoCantidad;
	}




	public void setProductoCesta(List<ProductoCantidad> productoCantidad) {
		this.productoCantidad = productoCantidad;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = -2571360982612243290L;

}

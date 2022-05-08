package net.ostemplate.app.productos.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	private String nombre;
	private Double precio;
	private String categoria;
	private Double valoracion;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_idProductoCaracteristicas")
	private ProductoCaracteristicas productoCaracteristicas;
    
    @OneToMany
    @JoinColumn(
    		name="idProducto",
    		foreignKey = @ForeignKey(name="id_producto", value=ConstraintMode.CONSTRAINT))
    private List<ProductoCantidad> productoCantidad;
    
    
    
    @PrePersist
    public void prePersist() {
    	createAt= new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getValoracion() {
		return valoracion;
	}

	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	
	public ProductoCaracteristicas getProductoCaracteristicas() {
		return productoCaracteristicas;
	}

	public void setProductoCaracteristicas(ProductoCaracteristicas productoCaracteristicas) {
		this.productoCaracteristicas = productoCaracteristicas;
	}



	private static final long serialVersionUID = 1285454306356845809L;

}

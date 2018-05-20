package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Direcciones")
public class DireccionEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idDireccion;
	private String calle;
	private int numero;
	private String codigoPostal;
	private String localidad;

	public DireccionEntity() {
	}

	public DireccionEntity(String calle, int numero, String localidad, String codigoPostal) {
		this.setCalle(calle);
		this.setNumero(numero);
		this.setCodigoPostal(codigoPostal);
		this.setLocalidad(localidad);
	}
	
	public int getId() {
		return idDireccion;
	}

	public void setId(int id) {
		this.idDireccion = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}

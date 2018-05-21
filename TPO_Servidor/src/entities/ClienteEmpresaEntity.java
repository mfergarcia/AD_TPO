// Pendiente:
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Ver como resolver: La creacion del cliente implica tambien la cracion
// de la cuenta corriente
package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.CtaCte;
import negocio.Direccion;

@Entity
@DiscriminatorValue("E")
@Table(name="Clientes")
public class ClienteEmpresaEntity extends ClienteEntity{
	private String cuit;
	private String razonSocial;
	
	//Facu: ver como dar de alta tambien la cuenta corriente
	public ClienteEmpresaEntity(Integer idCliente, CtaCteEntity ctaCte, char tipoFactura, String condicionesEspeciales, DireccionEntity direccionFacturacion, char tipo, char estado, String cuit, String razonSocial) {
		super(idCliente, ctaCte, tipoFactura, condicionesEspeciales, direccionFacturacion, tipo, estado);
		this.setCuit(cuit);
		this.setRazonSocial(razonSocial);
	}
	
	public ClienteEmpresaEntity() {
		super();
	}
	
	
	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
}

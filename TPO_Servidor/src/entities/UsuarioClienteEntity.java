package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.UsuarioCliente;

@Entity
@Table(name= "UsuariosCliente")
public class UsuarioClienteEntity {
	@Id
	private String usuario;
	private String pwd;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCliente")
	private ClienteEntity cliente;
	
	
	public UsuarioClienteEntity( String usuario, String pwd, ClienteEntity cliente) {
		super();
		this.usuario = usuario;
		this.pwd = pwd;
		this.cliente = cliente;
	}
	
	public UsuarioClienteEntity(UsuarioCliente uc){
		this.setCliente(new ClienteEntity(uc.getCliente()));
		this.setPwd(uc.getPwd());
		this.setUsuario(uc.getUsuario());
	}

	public UsuarioClienteEntity() {}
	
	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public ClienteEntity getCliente() {
		return cliente;
	}


	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
}

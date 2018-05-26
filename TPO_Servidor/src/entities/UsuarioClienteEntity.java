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
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private String usuario;
	private String pwd;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCliente")
	private ClienteEntity cliente;
	
	
	public UsuarioClienteEntity(Integer id, String usuario, String pwd, ClienteEntity cliente) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.pwd = pwd;
		this.cliente = cliente;
	}
	
	public UsuarioClienteEntity(UsuarioCliente uc){
		if(uc.getId()!= 0)
			this.setId(uc.getId());
		this.setCliente(new ClienteEntity(uc.getCliente()));
		this.setPwd(uc.getPwd());
		this.setUsuario(uc.getUsuario());
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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

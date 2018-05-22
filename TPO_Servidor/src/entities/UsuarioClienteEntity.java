package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "UsuariosCliente")
public class UsuarioClienteEntity {
	@Id
	private Integer id;
	private String usuario;
	private String pwd;
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "idCliente")
	private ClienteEntity cliente;
	
	
	
	
	
}

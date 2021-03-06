package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.UsuarioEmpleado;

@Entity
@Table(name= "UsuarioEmpleado")
public class UsuarioEmpleadoEntity {
	@Id
	private String usuario;
	private String pwd;
	private String menu;
	
	
	public UsuarioEmpleadoEntity() {
		
	}

	public UsuarioEmpleadoEntity(UsuarioEmpleado ue) {
		this.setMenu(ue.getMenu());
		this.setPwd(ue.getPwd());
		this.setUsuario(ue.getUsuario());
		
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


	public String getMenu() {
		return menu;
	}


	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
	
	
}

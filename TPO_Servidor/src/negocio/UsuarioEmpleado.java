//LISTO PARA PROBAR
package negocio;

import entities.UsuarioEmpleadoEntity;

public class UsuarioEmpleado {
	private int id;
	private String usuario;
	private String pwd;
	private String menu;
	
	public UsuarioEmpleado() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioEmpleado(UsuarioEmpleadoEntity uee) {
		this.setId(uee.getId());
		this.setMenu(uee.getMenu());
		this.setPwd(uee.getPwd());
		this.setUsuario(uee.getUsuario());
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

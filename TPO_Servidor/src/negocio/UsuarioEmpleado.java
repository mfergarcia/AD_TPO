//LISTO PARA PROBAR
package negocio;

import entities.UsuarioEmpleadoEntity;

public class UsuarioEmpleado {

	private String usuario;
	private String pwd;
	private String menu;
	
	public UsuarioEmpleado() {
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioEmpleado(UsuarioEmpleadoEntity uee) {

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

//LISTO PARA PROBAR
package negocio;

import entities.UsuarioClienteEntity;

public class UsuarioCliente {
	private int id;
	private String usuario;
	private String pwd;
	private Cliente cliente;
	
	public UsuarioCliente(UsuarioClienteEntity uce) {
		this.setCliente(new Cliente(uce.getCliente()));
		this.setId(uce.getId());
		this.setPwd(uce.getPwd());
		this.setUsuario(uce.getUsuario());
	}

	
	public UsuarioCliente() {
		
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}

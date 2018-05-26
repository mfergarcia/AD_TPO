package dao;

public class UsuarioClienteDAO {
	private static UsuarioClienteDAO instance;
	
	private UsuarioClienteDAO(){}
	
	public static UsuarioClienteDAO getInstancia(){
		if(instance==null)
			instance= new UsuarioClienteDAO();
		return instance;
	}
}

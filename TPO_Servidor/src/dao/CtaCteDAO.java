package dao;

import negocio.CtaCte;

public class CtaCteDAO {
	
	private static CtaCteDAO instancia;
	
	
	private CtaCteDAO(){}
	
	
	public static CtaCteDAO getInstance() {
		if(instancia==null)
			instancia= new CtaCteDAO();
		return instancia;
	}


	public void grabar(CtaCte ctaCte) {
		// TODO Auto-generated method stub
		
	}

}

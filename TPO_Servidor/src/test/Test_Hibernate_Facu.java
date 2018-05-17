package test;

import dao.ClienteEmpresaDAO;
import dao.ClientePersonaDAO;
import negocio.ClienteEmpresa;
import negocio.ClientePersona;
import negocio.CtaCte;
import negocio.Direccion;

public class Test_Hibernate_Facu {
	
	public static void main(String[] args) {
		CtaCte cC= new CtaCte(1);
		Direccion d= new Direccion();
		ClienteEmpresa ce= new ClienteEmpresa(cC, 'A', "Ninguna", d, 'A',"1283821213","Ninguna"); 
		ClientePersona cp= new ClientePersona(cC,'A',"Ninguna", d, 'A',"3229322","Suarez", "Jose");
		ClienteEmpresaDAO.getInstance().grabar(ce);
		ClientePersonaDAO.getInstance().grabar(cp);
	}
}

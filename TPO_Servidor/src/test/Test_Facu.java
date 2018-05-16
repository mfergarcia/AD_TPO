package test;

import dao.ClientePersonaDAO;
import negocio.ClientePersona;

public class Test_Facu {

	public static void main(String[] args) {
		ClientePersonaDAO.getInstance().grabar(new ClientePersona(1,'A', "Ninguna", "29347192", "Suarez", "Alberto"));
		
	}
}

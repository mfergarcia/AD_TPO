package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hbt.HibernateUtil;

public class ClienteDAO {
	private static ClienteDAO instancia;
	
	private ClienteDAO(){}
	
	public static ClienteDAO getInstancia(){
		if(instancia== null)
			instancia= new ClienteDAO();
		
		return instancia;
	} 
	
	
	public char getTipoCliente(int idCliente){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		char tipo= 'N';
		tipo= (char) session.createQuery("Select ce.tipo from ClienteEmpresaEntity ce where idCliente= ?")
					.setParameter(0, idCliente)
					.uniqueResult();
		if(tipo!='N')
			return tipo;
		else
			System.out.println("ID Invalida");
		return 0;
	}
}

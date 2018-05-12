package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.ClienteEmpresaEntity;
import hbt.HibernateUtil;
import negocio.ClienteEmpresa;

public class ClienteEmpresaDAO {
	private static ClienteEmpresaDAO instancia;
	
	private ClienteEmpresaDAO(){}
	
	public ClienteEmpresaDAO getInstance(){
		if(instancia==null)
			instancia=new ClienteEmpresaDAO();
		return instancia;
	}
	
	public void grabar(ClienteEmpresa ce){
		ClienteEmpresaEntity cee= new ClienteEmpresaEntity(ce.getIdCliente(),ce.getCondicionesEspeciales(), ce.getEstado(), ce.getCuit(), ce.getRazonSocial());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cee);
		session.getTransaction().commit();
		session.close();
	}
}


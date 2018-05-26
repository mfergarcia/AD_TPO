package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UsuarioClienteEntity;
import hbt.HibernateUtil;
import negocio.UsuarioCliente;

public class UsuarioClienteDAO {
	private static UsuarioClienteDAO instance;
	
	private UsuarioClienteDAO(){}
	
	public static UsuarioClienteDAO getInstancia(){
		if(instance==null)
			instance= new UsuarioClienteDAO();
		return instance;
	}
	
	public int grabar(UsuarioCliente uc){
		UsuarioClienteEntity uce = new UsuarioClienteEntity(uc);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(uce);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public UsuarioCliente findByUser(String usuario){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioClienteEntity uce= (UsuarioClienteEntity) session.createQuery("from UsuarioClienteEntity where usuario = ?")
									.setParameter(0, usuario)
									.uniqueResult();
		return new UsuarioCliente(uce);
	}
}

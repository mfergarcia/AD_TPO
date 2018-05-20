package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CtaCteEntity;
import hbt.HibernateUtil;
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
		CtaCteEntity cc= new CtaCteEntity(ctaCte.getLimiteCredito());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cc);
		session.getTransaction().commit();
		session.close();
		
	}

}

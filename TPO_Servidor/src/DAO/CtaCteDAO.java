package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.ClienteEmpresaEntity;
import entity.CtaCteEntity;
import hbt.HibernateUtil;
import negocio.ClienteEmpresa;
import negocio.CtaCte;

public class CtaCteDAO {
	private static CtaCteDAO instancia;
	
	private CtaCteDAO(){}
	
	public CtaCteDAO getInstance() {
		if(instancia==null)
			instancia= new CtaCteDAO();
		return instancia;
	}
	
	public void grabar(CtaCte cte){
		CtaCteEntity cee= new CtaCteEntity(cte.getLimiteCredito());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cee);
		session.getTransaction().commit();
		session.close();
	}
}

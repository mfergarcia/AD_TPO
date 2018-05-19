package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
import entities.RemitoEntity;
import hbt.HibernateUtil;
import negocio.Remito;


public class RemitoDAO {
	private static RemitoDAO instancia;
	
	private RemitoDAO(){}
	
	public static RemitoDAO getIntance(){
		if(instancia==null)
			instancia= new RemitoDAO();
		return instancia;
	}
	
	public void grabar (Remito s){
		RemitoEntity se = new RemitoEntity(s.getNumRemito());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();
	}
	
	public RemitoEntity findByID(int numRemito){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		RemitoEntity cee= (RemitoEntity) session.createQuery("from RemitoEntity where idRemito = ?")
									.setParameter(0, numRemito)
									.uniqueResult();
		
		return cee;
	}
	
	

}


package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaEntity;
import entities.PagoEntity;
import entities.RemitoEntity;
import hbt.HibernateUtil;
import negocio.Pago;
import negocio.Remito;


public class RemitoDAO {
	private static RemitoDAO instancia;
	
	private RemitoDAO(){}
	
	public static RemitoDAO getIntance(){
		if(instancia==null)
			instancia= new RemitoDAO();
		return instancia;
	}
	
	public int grabar(Remito s){
		RemitoEntity se = new RemitoEntity(new FacturaEntity(s.getFactura()), s.getFechaRemito());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public void update(Remito s){
		RemitoEntity se = new RemitoEntity(new FacturaEntity(s.getFactura()), s.getFechaRemito());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(se);
		session.getTransaction().commit();
		session.close();
	}
	
	public Remito findByID(int idRemito){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		RemitoEntity re= (RemitoEntity) session.createQuery("from RemitoEntity where idRemito = ?")
									.setParameter(0, idRemito)
									.uniqueResult();
		return new Remito(re);
	}
	
}


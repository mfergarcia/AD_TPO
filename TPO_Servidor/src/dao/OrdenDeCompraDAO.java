package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.OrdenDeCompraEntity;
import hbt.HibernateUtil;
import negocio.OrdenDeCompra;

public class OrdenDeCompraDAO {
	private static OrdenDeCompraDAO instancia;
	
	private OrdenDeCompraDAO() {}
	
	public static OrdenDeCompraDAO getInstance() {
		if(instancia==null) {
			instancia= new OrdenDeCompraDAO();
		}
		return instancia;
	}
	
	public void grabar(OrdenDeCompra oc) {
		OrdenDeCompraEntity oce= new OrdenDeCompraEntity(oc);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(oce);
		session.getTransaction().commit();
		session.close();
	}
	
	public OrdenDeCompra findById(int numOC){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		OrdenDeCompraEntity oce= (OrdenDeCompraEntity) session.createQuery("from OrdenDeCompraEntity where numOC= ?").setParameter(0, numOC).uniqueResult();
		return new OrdenDeCompra(oce);	
	}
}

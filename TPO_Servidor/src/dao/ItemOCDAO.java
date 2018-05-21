package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ItemOCEntity;
import entities.OrdenDeCompraEntity;
import hbt.HibernateUtil;
import negocio.ItemOC;
import negocio.OrdenDeCompra;

public class ItemOCDAO {
private static ItemOCDAO instancia;
	
	private ItemOCDAO() {}
	
	public static ItemOCDAO getInstance() {
		if(instancia==null) {
			instancia= new ItemOCDAO();
		}
		return instancia;
	}
	
	public int grabar(ItemOC ioc) {
		ItemOCEntity ioce= new ItemOCEntity(ioc);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i= (Integer) session.save(ioce);
		session.getTransaction().commit();
		session.close();
		return i;
	}

	public void update(ItemOC itemOC) {
		ItemOCEntity ioce= new ItemOCEntity(itemOC);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(ioce);
		session.getTransaction().commit();
		session.close();
	}
	
}

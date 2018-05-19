package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEntity;
import entities.FacturaEntity;
import entities.ItemArticuloEntity;
import hbt.HibernateUtil;
import negocio.ItemArticulo;

public class ItemArticuloDAO {
	private static ItemArticuloDAO instancia;
	
	private ItemArticuloDAO() {}
	
	public static ItemArticuloDAO getInstance() {
		if(instancia== null)
			instancia= new ItemArticuloDAO();
		return instancia;
	}
	
	public void grabar(ItemArticulo ia) {
		//ArticuloEntity ae= new ArticuloEntity (ia.getArticulo().getCodigoBarras(), );
		//ItemArticuloEntity iae= new ItemArticuloEntity(ia.getArticulo(), ia.getCant(), ia.getPrecioVta(), ia.getEstadoStock());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		//int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();
	}
	
	/*
	public ArticuloEntity findByID(int idArticulo){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity ae= (ArticuloEntity) session.createQuery("from RemitoEntity where idRemito = ?")
									.setParameter(0, idArticulo)
									.uniqueResult();
		
		return ae;
	}*/
}

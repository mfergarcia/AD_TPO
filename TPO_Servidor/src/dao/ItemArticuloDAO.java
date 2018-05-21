package dao;

import java.util.List;

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
	
	public int grabar(ItemArticulo ia) {
		ArticuloEntity ae= new ArticuloEntity (ia.getArticulo());
		ItemArticuloEntity iae= new ItemArticuloEntity(ae, ia.getCant(), ia.getPrecioVta(), ia.getEstadoStock());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i= (Integer) session.save(iae);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	
	public List<ItemArticulo> findAll(int numPedido){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEntity ae= (ArticuloEntity) session.createQuery("from RemitoEntity where idRemito = ?")
									.setParameter(0, numPedido)
									.uniqueResult();
		//TODO
		return null;
	}

	public void update(ItemArticulo itemArticulo) {
		ItemArticuloEntity iae= new ItemArticuloEntity(itemArticulo);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(iae);
		session.getTransaction().commit();
		session.close();
		
	}
}

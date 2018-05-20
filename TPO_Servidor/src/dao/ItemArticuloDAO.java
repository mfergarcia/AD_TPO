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
	
	public void grabar(ItemArticulo ia) {
		ArticuloEntity ae= new ArticuloEntity (ia.getArticulo());
		ItemArticuloEntity iae= new ItemArticuloEntity(ae, ia.getCant(), ia.getPrecioVta(), ia.getEstadoStock());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(iae);
		session.getTransaction().commit();
		session.close();
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
}

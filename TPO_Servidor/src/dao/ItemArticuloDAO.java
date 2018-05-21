package dao;

import java.util.ArrayList;
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
	
	
	@SuppressWarnings("unchecked")
	public List<ItemArticulo> findAll(int numPedido){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<ItemArticuloEntity> ae= (List<ItemArticuloEntity>) session.createQuery("from ItemArticuloEntity where numPedido = ?")
									.setParameter(0, numPedido)
									.list();

		List<ItemArticulo> lia=new ArrayList<ItemArticulo>();
		for(ItemArticuloEntity iae: ae)
			lia.add(new ItemArticulo(iae));
		return lia;
	}

	public void update(ItemArticulo itemArticulo) {
		ItemArticuloEntity iae= new ItemArticuloEntity(itemArticulo);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(iae);
		session.getTransaction().commit();
		session.close();
		
	}
}

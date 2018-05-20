package dao;

import entities.ArticuloEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEnStockEntity;
import hbt.HibernateUtil;
import negocio.ArticuloEnStock;

public class ArticuloEnStockDAO {
	private static ArticuloEnStockDAO instancia;
	
	private ArticuloEnStockDAO(){}
	
	public static ArticuloEnStockDAO getInstance(){
		if(instancia==null)
			instancia= new ArticuloEnStockDAO();
		return instancia;
	}
	
	public void grabar(ArticuloEnStock ae){
		ArticuloEnStockEntity aee= new ArticuloEnStockEntity(ae.getId(),ae.getCodigoBarras(),ae.getCodigoUbicacion(), ae.getCantidad(),
															ae.getLote(),ae.getFechaVencimiento(),ae.getFechaCompra(), ae.getProveedor(), ae.getPrecioCompra());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(aee);
		session.getTransaction().commit();
		session.close();
	}
	
	public ArticuloEnStockEntity findByID(int id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEnStockEntity aee= (ArticuloEnStockEntity) session.createQuery("from AticuloEnStockEntity where id = ?")
									.setParameter(0, id)
									.uniqueResult();
		
		return aee;
	}
	
	
}

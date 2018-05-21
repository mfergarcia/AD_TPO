package dao;

import entities.ArticuloEntity;

import java.util.ArrayList;
import java.util.List;

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
	
	public ArticuloEnStock findByID(int id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEnStockEntity aee= (ArticuloEnStockEntity) session.createQuery("from ArticuloEnStockEntity where id = ?")
									.setParameter(0, id)
									.uniqueResult();
		
		return new ArticuloEnStock(aee);
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticuloEnStock> showAllbylote(String codigoBarras){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<ArticuloEnStockEntity> aee= new ArrayList<ArticuloEnStockEntity>();
		aee= (List<ArticuloEnStockEntity>) session.createQuery("from ArticuloEnStockEntity where codigoBarras= ? order by lote ASC").setParameter(0, codigoBarras).list();
		List<ArticuloEnStock> res= new ArrayList<ArticuloEnStock>();
		for (ArticuloEnStockEntity ae: aee)
			res.add(new ArticuloEnStock(ae));
		return res;
	}

	public void update(ArticuloEnStock articuloEnStock) {
		ArticuloEnStockEntity aee= new ArticuloEnStockEntity(articuloEnStock);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(aee);
		session.getTransaction().commit();
		session.close();
		
	}
}

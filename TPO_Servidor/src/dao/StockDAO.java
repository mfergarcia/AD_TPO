package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
import entities.StockEntity;
import hbt.HibernateUtil;
import negocio.Stock;

public class StockDAO {
	private static StockDAO instancia;
	
	private StockDAO() {}
	
	public static StockDAO getInstance() {
		if(instancia==null)
			instancia= new StockDAO();
		return instancia;
	}
	
	
	public void grabar(Stock s) {
		StockEntity se= new StockEntity(s.getCodigoUbicacion(),s.getCantidadReal(),s.getCantidadReservada(),s.getEstado());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(se);
		session.getTransaction().commit();
		session.close();
		
	} 
	
	public Stock findByID(String codUbicacion){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		StockEntity se= (StockEntity) session.createQuery("from StockEntity where codUbicacion = ?")
									.setParameter(0, codUbicacion)
									.uniqueResult();
		
		return new Stock(se);
	}
	
	@SuppressWarnings("unchecked")
	public Stock findByEstadoLibre(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		String libre="LIBRE";
		List<StockEntity> se= (List<StockEntity>) session.createQuery("from StockEntity where estado = ?")
									.setParameter(0, libre )
									.list();
		if(!se.isEmpty())
			return new Stock(se.get(0));
		else
			return null;
	}

	public void update(Stock s) {
		StockEntity se= new StockEntity(s.getCodigoUbicacion(),s.getCantidadReal(),s.getCantidadReservada(),s.getEstado());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(se);
		session.getTransaction().commit();
		session.close();
	}
}
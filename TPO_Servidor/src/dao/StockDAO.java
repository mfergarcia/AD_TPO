package dao;

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
		int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();
		
	} 
	
	public StockEntity findByID(int idStock){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		StockEntity se= (StockEntity) session.createQuery("from ClienteEmpresaEntity where idCliente = ?")
									.setParameter(0, idStock)
									.uniqueResult();
		
		return se;
	}
}
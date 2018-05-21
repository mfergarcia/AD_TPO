package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.MovStockCompraEntity;
import entities.OrdenDeCompraEntity;
import hbt.HibernateUtil;
import negocio.MovStockCompra;

public class MovStockCompraDAO {
		
	private static MovStockCompraDAO instancia;
	
	private MovStockCompraDAO() {}
	
	public static MovStockCompraDAO getInstance(){
		if(instancia== null)
			instancia= new MovStockCompraDAO();
		return instancia;
	}
	
	
	public int grabar(MovStockCompra sc){
		MovStockCompraEntity sce= new MovStockCompraEntity(sc.getTipoAjuste(),sc.getFecha(),sc.getCant(),sc.getTipoMov(),new OrdenDeCompraEntity(sc.getOc()));
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i= (Integer) session.save(sce);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public void update(MovStockCompra sc){
		MovStockCompraEntity sce= new MovStockCompraEntity(sc.getTipoAjuste(),sc.getFecha(),sc.getCant(),sc.getTipoMov(),new OrdenDeCompraEntity(sc.getOc()));
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(sce);
		session.getTransaction().commit();
		session.close();
		
	}
}

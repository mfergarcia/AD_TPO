package dao;

import entities.ArticuloEntity;
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
			
	}
	
	public ArticuloEnStockEntity findByID(int id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArticuloEnStockEntity aee= (ArticuloEnStockEntity) session.createQuery("from AticuloEnStockEntity where idCliente = ?")
									.setParameter(0, id)
									.uniqueResult();
		
		return aee;
	}
	
	
}

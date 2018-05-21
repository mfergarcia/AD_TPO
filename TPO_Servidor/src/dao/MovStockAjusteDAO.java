package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEnStockEntity;
import entities.MovStockAjusteEntity;
import hbt.HibernateUtil;
import negocio.MovStockAjuste;

public class MovStockAjusteDAO {
	
private static MovStockAjusteDAO instancia;
	
	private MovStockAjusteDAO() {}
	
	public static MovStockAjusteDAO getInstance(){
		if(instancia== null)
			instancia= new MovStockAjusteDAO();
		return instancia;
	}
	
	public int grabar(MovStockAjuste sa){
		MovStockAjusteEntity sae= new MovStockAjusteEntity(sa.getTipoAjuste(), sa.getFecha(), sa.getCant(), sa.getTipoMov(), new ArticuloEnStockEntity(sa.getArtEnStock()));
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i=(Integer)session.save(sae);
		session.getTransaction().commit();
		session.close();
		return i;
	}

	public void update(MovStockAjuste sa) {
		MovStockAjusteEntity sae= new MovStockAjusteEntity(sa.getTipoAjuste(), sa.getFecha(), sa.getCant(), sa.getTipoMov(), new ArticuloEnStockEntity(sa.getArtEnStock()));
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(sae);
		session.getTransaction().commit();
		session.close();
	}
}

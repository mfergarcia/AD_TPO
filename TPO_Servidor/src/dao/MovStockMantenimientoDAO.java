package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.MovStockMantenimientoEntity;
import hbt.HibernateUtil;
import negocio.MovStockAjuste;
import negocio.MovStockMantenimiento;

public class MovStockMantenimientoDAO {
	
	private static MovStockMantenimientoDAO instancia;
	
	private MovStockMantenimientoDAO() {}
	
	public static MovStockMantenimientoDAO getInstance(){
		if(instancia== null)
			instancia= new MovStockMantenimientoDAO();
		return instancia;
	}
	
	public int grabar(MovStockMantenimiento sm){
		MovStockMantenimientoEntity sme= new MovStockMantenimientoEntity(sm.getTipoAjuste(), sm.getFecha(), sm.getCant(), sm.getTipoMov(), 
																		 sm.getUsuarioRegistrado(), sm.getAutorizante(), sm.getDestinoFinal());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i= (Integer) session.save(sme);
		session.getTransaction().commit();
		session.close();
		return i;
	}

	public void update(MovStockMantenimiento sm) {
		MovStockMantenimientoEntity sme= new MovStockMantenimientoEntity(sm.getTipoAjuste(), sm.getFecha(), sm.getCant(), sm.getTipoMov(), 
				 sm.getUsuarioRegistrado(), sm.getAutorizante(), sm.getDestinoFinal());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(sme);
		session.getTransaction().commit();
		session.close();
	}
	
}

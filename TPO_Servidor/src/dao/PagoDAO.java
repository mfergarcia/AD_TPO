package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.PagoEntity;
import hbt.HibernateUtil;
import negocio.Pago;

public class PagoDAO {
	private static PagoDAO instancia;
	
	private PagoDAO(){}
	
	public static PagoDAO getInstance(){
		if(instancia==null)
			instancia= new PagoDAO();
		return instancia;
	}
	
	public void grabar(Pago s) {
		PagoEntity se= new PagoEntity(s.getTipoPago(),s.getImporte());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();

	}	


}
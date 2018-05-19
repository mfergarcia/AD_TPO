package dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaEntity;
import hbt.HibernateUtil;
import negocio.Factura;

public class FacturaDAO {
	private static FacturaDAO instancia;
	
	private FacturaDAO (){}
	
	public static FacturaDAO getInstance(){
		if(instancia==null)
			instancia= new FacturaDAO();
		return instancia;
	}
	
	public void grabar(Factura s) {
		FacturaEntity se= new FacturaEntity(s.getNumFactura(),s.getTipoFactura(),s.getEstadoFactura(), s.getMontoAdeudado(),s.getFechaFactura());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();
		
	} 

}


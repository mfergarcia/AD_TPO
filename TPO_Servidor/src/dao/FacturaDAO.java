package dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.FacturaEntity;
import entities.PagoEntity;
import entities.PedidoEntity;
import entities.RemitoEntity;
import hbt.HibernateUtil;
import negocio.Factura;
import negocio.Pago;

public class FacturaDAO {
	private static FacturaDAO instancia;
	
	private FacturaDAO (){}
	
	public static FacturaDAO getInstance(){
		if(instancia==null)
			instancia= new FacturaDAO();
		return instancia;
	}
	
	public int grabar(Factura s) {
		FacturaEntity se= new FacturaEntity(s);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(se);
		session.getTransaction().commit();
		session.close();
		return i;
	} 
	
	public Factura findByID(int idFactura){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		FacturaEntity fe= (FacturaEntity) session.createQuery("from FacturaEntity where idFactura = ?")
									.setParameter(0, idFactura)
									.uniqueResult();
		return new Factura(fe);
	}
	
	public void update(Factura s) {
		FacturaEntity se= new FacturaEntity(s);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(se);
		session.getTransaction().commit();
		session.close();
		
	} 
}


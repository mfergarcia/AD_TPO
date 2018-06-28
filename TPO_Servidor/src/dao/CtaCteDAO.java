package dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CtaCteEntity;
import entities.FacturaEntity;
import entities.PagoEntity;
import hbt.HibernateUtil;
import negocio.CtaCte;
import negocio.Factura;
import negocio.Pago;

public class CtaCteDAO {
	
	private static CtaCteDAO instancia;
	
	
	private CtaCteDAO(){}
	
	
	public static CtaCteDAO getInstance() {
		if(instancia==null)
			instancia= new CtaCteDAO();
		return instancia;
	}


	public int grabar(CtaCte ctaCte) {
		CtaCteEntity cc= new CtaCteEntity(ctaCte);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i= (Integer) session.save(cc);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public void update(CtaCte ctaCte) {
		CtaCteEntity cc= new CtaCteEntity(ctaCte);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cc);
		session.getTransaction().commit();
		session.close();
		
	}
	public Collection<Factura> obtenerFacturas(Integer id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		Collection<FacturaEntity> fe=(Collection<FacturaEntity>) session.createQuery("Select fe from CtaCteEntity where idCtaCte= ?").setParameter(0,id).list();
		Collection<Factura> lf= new ArrayList<Factura>();
		for (FacturaEntity f: fe)
			lf.add(new Factura(f));
		return lf;
	}
	
	public Collection<Pago> obtenerPagos(Integer id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		Collection<PagoEntity> pe=(Collection<PagoEntity>) session.createQuery("Select pe from CtaCteEntity where idCtaCte= ?").setParameter(0,id).list();
		Collection<Pago> lp= new ArrayList<Pago>();
		for (PagoEntity f: pe)
			lp.add(new Pago(f));
		return lp;
	}
}

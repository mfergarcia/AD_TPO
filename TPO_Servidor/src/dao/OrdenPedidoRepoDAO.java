package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.OrdenPedidoRepoEntity;
import hbt.HibernateUtil;
import negocio.OrdenPedidoRepo;

public class OrdenPedidoRepoDAO {
	private static OrdenPedidoRepoDAO instancia;
	
	private OrdenPedidoRepoDAO() {}
	
	public static OrdenPedidoRepoDAO getInstance() {
		if(instancia==null) {
			instancia= new OrdenPedidoRepoDAO();
		}
		return instancia;
	}
	
	public int grabar(OrdenPedidoRepo opr) {
		OrdenPedidoRepoEntity opre= new OrdenPedidoRepoEntity(opr);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i= (Integer) session.save(opre);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdenPedidoRepo> AllByArtPENDIENTE(String codigoBarras){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<OrdenPedidoRepoEntity> lopre= new ArrayList<OrdenPedidoRepoEntity>();
		lopre= session.createQuery("from OrdenPedidoRepoEntity where articulo.codigoBarras= ? and estado= ?")
				.setParameter(0, codigoBarras)
				.setParameter(1, "PENDIENTE")
				.list();
		List<OrdenPedidoRepo> lopr= new ArrayList<OrdenPedidoRepo>();
		for(OrdenPedidoRepoEntity o: lopre)
			lopr.add(new OrdenPedidoRepo(o));
		return lopr;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdenPedidoRepo> AllByEstado(String estado){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<OrdenPedidoRepoEntity> lopre= new ArrayList<OrdenPedidoRepoEntity>();
		lopre= session.createQuery("from OrdenPedidoRepoEntity where estado= ?")
				.setParameter(0, estado)
				.list();
		List<OrdenPedidoRepo> lopr= new ArrayList<OrdenPedidoRepo>();
		for(OrdenPedidoRepoEntity o: lopre)
			lopr.add(new OrdenPedidoRepo(o));
		return lopr;
	}

	public void update(OrdenPedidoRepo opr) {
		OrdenPedidoRepoEntity opre= new OrdenPedidoRepoEntity(opr);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(opre);
		session.getTransaction().commit();
		session.close();
	}
	
}

package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.PedidoEntity;
import hbt.HibernateUtil;
import negocio.Pedido;

public class PedidoDAO {
	
	private static PedidoDAO instancia;
		
		private PedidoDAO(){}
		
		public static PedidoDAO getIntance(){
			if(instancia==null)
				instancia= new PedidoDAO();
			return instancia;
		}
		
		public int grabar(Pedido p){
			PedidoEntity pe = new PedidoEntity(p);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			int i = (Integer) session.save(pe);
			session.getTransaction().commit();
			session.close();
			return i;
		}
		
		public Pedido findByID(int numPedido){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			PedidoEntity pe= (PedidoEntity) session.createQuery("from PedidoEntity where numPedido = ?")
										.setParameter(0, numPedido)
										.uniqueResult();
			
			return new Pedido(pe);
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Pedido> AllByEstado(String estado){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			List<PedidoEntity> lpe= new ArrayList<PedidoEntity>();
			lpe= (List<PedidoEntity>) session.createQuery("From PedidoEntity where estado= ?").setParameter(0, estado).list();
			List <Pedido> lp= new ArrayList<Pedido>();
			for(PedidoEntity pe: lpe)
				lp.add(new Pedido(pe));
			return lp;	
		}
	
		@SuppressWarnings("unchecked")
		public List<Pedido> AllByCliente(Integer idCliente){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			List<PedidoEntity> lpe= new ArrayList<PedidoEntity>();
			lpe= (List<PedidoEntity>) session.createQuery("From PedidoEntity where idCliente= ?").setParameter(0, idCliente).list();
			List <Pedido> lp= new ArrayList<Pedido>();
			for(PedidoEntity pe: lpe)
				lp.add(new Pedido(pe));
			return lp;	
		}

		public void update(Pedido pedido) {
	
			PedidoEntity pe = new PedidoEntity(pedido);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(pe);
			session.getTransaction().commit();
			session.close();
		}


}

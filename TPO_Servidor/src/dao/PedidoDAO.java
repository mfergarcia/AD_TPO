package dao;

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
		
		

	



}

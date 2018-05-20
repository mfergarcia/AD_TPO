package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
import entities.PedidoEntity;
import entities.RemitoEntity;
import hbt.HibernateUtil;
import negocio.Pedido;
import negocio.Remito;

public class PedidoDAO {
	
	private static PedidoDAO instancia;
		
		private PedidoDAO(){}
		
		public static PedidoDAO getIntance(){
			if(instancia==null)
				instancia= new PedidoDAO();
			return instancia;
		}
		
		public void grabar(Pedido s){
	//TODO	//	RemitoEntity se = new RemitoEntity(s.getNumPedido());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
		//	int i = (Integer) session.save(se);
			session.getTransaction().commit();
			session.close();
		}
		
		public PedidoEntity findByID(int numPedido){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			PedidoEntity re= (PedidoEntity) session.createQuery("from PedidoEntity where idPedido = ?")
										.setParameter(0, numPedido)
										.uniqueResult();
			
			return re;
		}
		
		

	



}

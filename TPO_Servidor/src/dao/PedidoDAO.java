package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
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
			RemitoEntity se = new RemitoEntity(s.getNumPedido());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			int i = (Integer) session.save(se);
			session.getTransaction().commit();
			session.close();
		}
		
		public PedidoEntity findByID(int numRemito){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			RemitoEntity re= (RemitoEntity) session.createQuery("from PedidoEntity where idRemito = ?")
										.setParameter(0, numRemito)
										.uniqueResult();
			
			return re;
		}
		
		

	



}

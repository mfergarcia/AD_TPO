package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.MovStockVentaEntity;
import entities.PedidoEntity;
import hbt.HibernateUtil;
import negocio.MovStockVenta;

public class MovStockVentaDAO {
		
		private static MovStockVentaDAO instancia;
	
		private MovStockVentaDAO() {}
		
		public static MovStockVentaDAO getInstance(){
			if(instancia== null)
				instancia= new MovStockVentaDAO();
			return instancia;
		}
		
		
		public int grabar(MovStockVenta sv){
			MovStockVentaEntity sve= new MovStockVentaEntity(sv.getTipoAjuste(),sv.getFecha(),sv.getCant(), sv.getTipoMov(), new PedidoEntity(sv.getPedido()));
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			int i= (Integer) session.save(sve);
			session.getTransaction().commit();
			session.close();
			return i;
		}

		public void update(MovStockVenta sv) {
			MovStockVentaEntity sve= new MovStockVentaEntity(sv.getTipoAjuste(),sv.getFecha(),sv.getCant(), sv.getTipoMov(), new PedidoEntity(sv.getPedido()));
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.update(sve);
			session.getTransaction().commit();
			session.close();
		}
}

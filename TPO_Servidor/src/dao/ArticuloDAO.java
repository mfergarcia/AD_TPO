package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEntity;
import entities.ClienteEmpresaEntity;
import hbt.HibernateUtil;
import negocio.Articulo;

public class ArticuloDAO {
		private static ArticuloDAO instancia;
		
		private ArticuloDAO() {}
		
		public static ArticuloDAO getInstance() {
			if(instancia==null) {
				instancia= new ArticuloDAO();
			}
			return instancia;
		}
		
		
		public void grabar(Articulo a) {
			ArticuloEntity ae= new ArticuloEntity(a.getCodigoBarras(), a.getDescripcion(), a.getPresentacion(), a.getTamaño(), a.getUnidad(), a.getPrecioVta(), a.getCantFijaCompra(), a.getCantMaxUbicacion(), a.getEstado());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			int i = (Integer) session.save(ae);
			session.getTransaction().commit();
			session.close();
		}
		
		public ArticuloEntity findByID(int idArticulo){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			ArticuloEntity ae= (ArticuloEntity) session.createQuery("from ClienteEmpresaEntity where idCliente = ?")
										.setParameter(0, idArticulo)
										.uniqueResult();
			
			return ae;
		}
}

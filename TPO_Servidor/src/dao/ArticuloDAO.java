package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ArticuloEntity;
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
			session.saveOrUpdate(ae);
			session.getTransaction().commit();
			session.close();
		}
		
		public Articulo findByID(String codigoBarras){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			ArticuloEntity ae= (ArticuloEntity) session.createQuery("from ArticuloEntity where codigoBarras = ?")
										.setParameter(0, codigoBarras)
										.uniqueResult();
			
			return new Articulo(ae);
		}
		
		@SuppressWarnings("unchecked")
		public List<Articulo> showAll(){
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			List<ArticuloEntity> ae= new ArrayList<ArticuloEntity>();
			ae= (List<ArticuloEntity>) session.createQuery("from ArticuloEntity where estado = ?")
										.setParameter(0, 'A')
										.list();
			List<Articulo> a= new ArrayList<Articulo>();
			for(ArticuloEntity e: ae)
				a.add(new Articulo(e));
			return a;
		}
}

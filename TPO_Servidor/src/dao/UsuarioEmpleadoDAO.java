package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.UsuarioEmpleadoEntity;
import hbt.HibernateUtil;
import negocio.UsuarioEmpleado;

public class UsuarioEmpleadoDAO {
	private static UsuarioEmpleadoDAO instance;
	
	private UsuarioEmpleadoDAO(){}
	
	public static UsuarioEmpleadoDAO getInstancia(){
		if(instance==null)
			instance= new UsuarioEmpleadoDAO();
		return instance;
	}
	
	public int grabar(UsuarioEmpleado ue){
		UsuarioEmpleadoEntity uee = new UsuarioEmpleadoEntity(ue);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(uee);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public UsuarioEmpleado findByUser(String usuario){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEmpleadoEntity uee= (UsuarioEmpleadoEntity) session.createQuery("from UsuarioEmpleadoEntity where usuario = ?")
									.setParameter(0, usuario)
									.uniqueResult();
		return new UsuarioEmpleado(uee);
	}
	
}

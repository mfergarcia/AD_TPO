package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Hibernate;
import entity.ClientePersonaEntity;
import hbt.HibernateUtil;
import negocio.ClientePersona;

public class ClientePersonaDAO {
	private static ClientePersonaDAO instancia;
	
	private ClientePersonaDAO() {}
	
	public static ClientePersonaDAO getInstance(){
		if( instancia== null)
			instancia= new ClientePersonaDAO();
		return instancia;
	}
	
	public void grabar(ClientePersona cp){
		ClientePersonaEntity cpe= new ClientePersonaEntity(cp.getIdCliente(),cp.getCondicionesEspeciales(), cp.getEstado(), cp.getDni(), cp.getApellido(),cp.getNombre());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cpe);
		session.getTransaction().commit();
		session.close();
	}
}

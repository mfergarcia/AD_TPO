//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Ver como grabar la cuenta corriente y la direccion
// que son con FK
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entities.ClientePersonaEntity;
import org.hibernate.Hibernate;
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
		ClientePersonaEntity cpe = new ClientePersonaEntity(cp.getCtaCte(), cp.getTipoFactura(), cp.getCondicionesEspeciales(), cp.getDireccionFacturacion(), cp.getTipo(), cp.getEstado(), cp.getDni(), cp.getApellido(), cp.getNombre());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(cpe);
		session.getTransaction().commit();
		session.close();
	}

	
}

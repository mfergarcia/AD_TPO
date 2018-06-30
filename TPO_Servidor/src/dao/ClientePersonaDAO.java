//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Ver como grabar la cuenta corriente y la direccion
// que son con FK
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
import entities.ClientePersonaEntity;
import entities.CtaCteEntity;
import entities.DireccionEntity;

import hbt.HibernateUtil;
import negocio.ClientePersona;
import negocio.Direccion;

public class ClientePersonaDAO {
	
	private static ClientePersonaDAO instancia;
	
	private ClientePersonaDAO() {}
	
	public static ClientePersonaDAO getInstance(){
		if( instancia== null)
			instancia= new ClientePersonaDAO();
		return instancia;
	}
	
	public int grabar(ClientePersona cp){
		Direccion d= cp.getDireccionFacturacion();
		DireccionEntity de= new DireccionEntity(d);
		ClientePersonaEntity cpe = new ClientePersonaEntity(cp.getIdCliente(), new CtaCteEntity(cp.getCtaCte().getLimiteCredito()), cp.getTipoFactura(), cp.getCondicionesEspeciales(), de, cp.getTipo(), cp.getEstado(), cp.getDni(), cp.getApellido(), cp.getNombre());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(cpe);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public void update(ClientePersona cp){
		Direccion d= cp.getDireccionFacturacion();
		DireccionEntity de= new DireccionEntity(d);
		ClientePersonaEntity cpe = new ClientePersonaEntity(cp.getIdCliente(), new CtaCteEntity(cp.getCtaCte()), cp.getTipoFactura(), cp.getCondicionesEspeciales(), de, cp.getTipo(), cp.getEstado(), cp.getDni(), cp.getApellido(), cp.getNombre());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(cpe);
		session.getTransaction().commit();
		session.close();
	}
	
	
	public ClientePersona findByID(int idCliente){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClientePersonaEntity cpe= (ClientePersonaEntity) session.createQuery("from ClientePersonaEntity where idCliente = ?")
									.setParameter(0, idCliente)
									.uniqueResult();
		
		return new ClientePersona(cpe);
	}
	
}

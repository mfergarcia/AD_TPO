//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Ver como grabar la cuenta corriente y la direccion
// que son con FK
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
import entities.CtaCteEntity;
import entities.DireccionEntity;
import hbt.HibernateUtil;
import negocio.ClienteEmpresa;
import negocio.Direccion;

public class ClienteEmpresaDAO {
	
	private static ClienteEmpresaDAO instancia;
	
	private ClienteEmpresaDAO() {
		
	}
	
	public static ClienteEmpresaDAO getInstance(){
		if(instancia==null)
			instancia=new ClienteEmpresaDAO();
		return instancia;
	}
	
	public int grabar(ClienteEmpresa ce){
		Direccion d= ce.getDireccionFacturacion();
		DireccionEntity de= new DireccionEntity(d);
		ClienteEmpresaEntity cee = new ClienteEmpresaEntity( new CtaCteEntity(ce.getCtaCte().getLimiteCredito()), ce.getTipoFactura(), ce.getCondicionesEspeciales(),de, ce.getTipo(), ce.getEstado(), ce.getCuit(), ce.getRazonSocial());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = (Integer) session.save(cee);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public ClienteEmpresa findByID(int idCliente){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ClienteEmpresaEntity cee= (ClienteEmpresaEntity) session.createQuery("from ClienteEmpresaEntity where idCliente = ?")
									.setParameter(0, idCliente)
									.uniqueResult();
		
		return new ClienteEmpresa(cee);
	}
	
	public void update(ClienteEmpresa ce){
		Direccion d= ce.getDireccionFacturacion();
		DireccionEntity de= new DireccionEntity(d);
		ClienteEmpresaEntity cee = new ClienteEmpresaEntity( new CtaCteEntity(ce.getCtaCte().getLimiteCredito()), ce.getTipoFactura(), ce.getCondicionesEspeciales(),de, ce.getTipo(), ce.getEstado(), ce.getCuit(), ce.getRazonSocial());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(cee);
		session.getTransaction().commit();
		session.close();
	}
	
}


//PENDIENTE: 
// @Facu: cambiar para que la herencia se guarde en una sola tabla y 
// adaptar los contructores. Ver como grabar la cuenta corriente y la direccion
// que son con FK
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ClienteEmpresaEntity;
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
	
	public void grabar(ClienteEmpresa ce){
		//ClienteEmpresaEntity cee = new ClienteEmpresaEntity(IDEA new CtaCteEntity(ce.getCtaCte()--Datos de la CtaCte), ce.getTipoFactura(), ce.getCondicionesEspeciales(), ce.getDireccionFacturacion(), ce.getTipo(), ce.getEstado(), ce.getCuit(), ce.getRazonSocial());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		//int i = (Integer) session.save(cee);
		session.getTransaction().commit();
		session.close();
	}

}


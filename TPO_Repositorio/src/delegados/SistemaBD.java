package delegados;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;

import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.DireccionDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;
import interfaces.InterfazRemota;

public class SistemaBD {

	private InterfazRemota ir;
	
	public SistemaBD() throws ExcepcionComunicacion {
		// TODO Auto-generated constructor stub
		try {
			ir = (InterfazRemota) Naming.lookup("//127.0.0.1/sistema");
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (MalformedURLException e) {
			throw new ExcepcionComunicacion("La direccion especificada no es correcta");
		} catch (NotBoundException e) {
			throw new ExcepcionComunicacion("El servicio remoto no está disponible");
		}
	}

	public ClienteDTO loginCliente(String usuario, String pwd) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.loginCliente(usuario, pwd);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}

	public String loginEmpleado(String usuario, String pwd) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.loginEmpleado(usuario, pwd);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	/* NOTA_FG: Creo que no se necesita
	// Recupera un cliente del sistema con idCliente. Devuelve un ClienteDTO
	public ClienteDTO buscarCliente(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.buscarCliente(idCliente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	*/
	
	// Crear un cliente tipo Empresa. Devuelve un ClienteDTO
	public ClienteDTO altaClienteEmpresa(String cuit, String razonSocial, DireccionDTO direccionFacturacionDTO, char tipoFactura, String condicionesEspeciales, float limiteCredito) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.altaClienteEmpresa(cuit, razonSocial, direccionFacturacionDTO, tipoFactura, condicionesEspeciales, limiteCredito);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	// Crear un cliente tipo Empresa. Devuelve un ClienteDTO
	public ClienteDTO altaClientePersona(String dni, String apellido, String nombre, DireccionDTO direccionFacturacionDTO, char tipoFactura, String condicionesEspeciales, float limiteCredito) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.altaClientePersona(dni, apellido, nombre, direccionFacturacionDTO, tipoFactura, condicionesEspeciales, limiteCredito);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	
	
	public void modificarDireccionFacturacion(int idCliente, DireccionDTO direccionDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			ir.modificarDireccionCliente(idCliente, direccionDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public void bajaCliente(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			ir.bajaCliente(idCliente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.altaArticulo(articuloDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public void bajaArticulo(String codBarras) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			ir.bajaArticulo(codBarras);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<ArticuloDTO> obtenerCatalogo() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerCatalogo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public PedidoDTO generarPedido(PedidoDTO pedidoDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.generarPedido(pedidoDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosPorCliente(idCliente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<PedidoDTO> obtenerPedidosAConfirmar() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosAConfirmar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public String aprobarPedido(int numPedido) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.aprobarPedido(numPedido);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	
}
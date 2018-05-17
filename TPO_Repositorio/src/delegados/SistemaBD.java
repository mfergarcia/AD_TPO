package delegados;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import dto.ArticuloDTO;
import dto.ArticuloEnStockDTO;
import dto.ClienteDTO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.DireccionDTO;
import dto.OrdenDeCompraDTO;
import dto.OrdenPedidoRepoDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;
import interfaces.InterfazRemota;

public class SistemaBD {

	private InterfazRemota ir;
	
	public SistemaBD() throws ExcepcionComunicacion {
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

	public int loginCliente(String usuario, String pwd) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.loginCliente(usuario, pwd);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}

	public String loginEmpleado(String usuario, String pwd) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.loginEmpleado(usuario, pwd);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public ClienteEmpresaDTO altaClienteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.altaClienteEmpresa(cteEmpresaDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public ClientePersonaDTO altaClientePersona(ClientePersonaDTO ctePersonaDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.altaClientePersona(ctePersonaDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public char obtenerTipoCliente(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerTipoCliente(idCliente);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}

	public ClienteEmpresaDTO obtenerCteEmpresa(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerCteEmpresa(idCliente);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public ClientePersonaDTO obtenerCtePersona(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerCtePersona(idCliente);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
//	
	public ClienteEmpresaDTO modificarCteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.modificarCteEmpresa(cteEmpresaDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public ClientePersonaDTO modificarCtePersona(ClientePersonaDTO ctePersonaDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.modificarCtePersona(ctePersonaDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public void bajaCliente(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			ir.bajaCliente(idCliente);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.altaArticulo(articuloDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public ArticuloDTO obtenerArticulo(String codBarras) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerArticulo(codBarras);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public ArticuloDTO modificarArticulo(ArticuloDTO articuloDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.modificarArticulo(articuloDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	
	
	public void bajaArticulo(String codBarras) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			ir.bajaArticulo(codBarras);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<ArticuloDTO> obtenerCatalogo() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerCatalogo();
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public PedidoDTO generarPedido(PedidoDTO pedidoDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.generarPedido(pedidoDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosPorCliente(idCliente);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<PedidoDTO> obtenerPedidosAConfirmar() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosAConfirmar();
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public String aprobarPedido(int numPedido) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.aprobarPedido(numPedido);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public String rechazarPedido(int numPedido, String motivo) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.rechazarPedido(numPedido, motivo);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public Collection<PedidoDTO> obtenerPedidosCompletos() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosCompletos();
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public String solicitarPedido(int numPedido) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.solicitarPedido(numPedido);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	
	
	public Collection<PedidoDTO> obtenerPedidosPendDeposito() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosPendDeposito();
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<ArticuloEnStockDTO> prepararPedido(int numPedido) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.prepararPedido(numPedido);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	
	
	public String actualizarStockPorVenta(int numPedido, Collection<ArticuloEnStockDTO> artEnStockDTO) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.actualizarStockPorVenta(numPedido, artEnStockDTO);
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<PedidoDTO> obtenerPedidosADespachar() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerPedidosADespachar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	
	
	public String registrarFechaEntrega(int numPedido, Date fechaEntrega) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.registrarFechaEntrega(numPedido, fechaEntrega);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<OrdenPedidoRepoDTO> obtenerOPRPendientes() throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.obtenerOPRPendientes();
		} catch (RemoteException e) {
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}
	
	public OrdenDeCompraDTO procesarOrdenDeCompra(int numOC) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.procesarOrdenDeCompra(numOC);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public Collection<ArticuloEnStockDTO> cargarArticuloEnStock(int numOC, String codBarras, int cantidad, String lote, Date fechaVenc, String proveedor, float precioCompra)  throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.cargarArticuloEnStock(numOC, codBarras, cantidad, lote, fechaVenc, proveedor, precioCompra);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	

	public String cumplirOrdenDeCompra(int numOC) throws ExcepcionComunicacion, ExcepcionSistema {
		try {
			return ir.cumplirOrdenDeCompra(numOC);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new ExcepcionComunicacion("Error en la comunicacion");
		} catch (ExcepcionSistema e) {
			throw e;
		}	
	}	
	
	
}
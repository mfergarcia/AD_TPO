package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import dto.ArticuloDTO;
import dto.ArticuloEnStockDTO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.CtaCteDTO;
import dto.DireccionDTO;
import dto.OrdenDeCompraDTO;
import dto.OrdenPedidoRepoDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionSistema;


public interface InterfazRemota extends Remote {

	public int loginCliente(String usuario, String pwd) throws RemoteException, ExcepcionSistema;
	public String loginEmpleado(String usuario, String pwd) throws RemoteException, ExcepcionSistema;
	public ClienteEmpresaDTO altaClienteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) throws RemoteException, ExcepcionSistema;
	public ClientePersonaDTO altaClientePersona(ClientePersonaDTO ctePersonaDTO) throws RemoteException, ExcepcionSistema;
	public char obtenerTipoCliente(int idCliente) throws RemoteException, ExcepcionSistema;
	public ClienteEmpresaDTO obtenerCteEmpresa(int idCliente) throws RemoteException, ExcepcionSistema;
	public ClientePersonaDTO obtenerCtePersona(int idCliente) throws RemoteException, ExcepcionSistema;
	public ClienteEmpresaDTO modificarCteEmpresa(ClienteEmpresaDTO cteEmpresaDTO) throws RemoteException, ExcepcionSistema;
	public ClientePersonaDTO modificarCtePersona(ClientePersonaDTO ctePersonaDTO) throws RemoteException, ExcepcionSistema;
	public void bajaCliente(int idCliente) throws RemoteException, ExcepcionSistema;
	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) throws RemoteException, ExcepcionSistema;
	public ArticuloDTO obtenerArticulo(String codBarras) throws RemoteException, ExcepcionSistema;
	public ArticuloDTO modificarArticulo(ArticuloDTO articuloDTO)  throws RemoteException, ExcepcionSistema;
	public void bajaArticulo(String codBarras) throws RemoteException, ExcepcionSistema;
	public Collection<ArticuloDTO> obtenerCatalogo() throws RemoteException, ExcepcionSistema;
	public PedidoDTO generarPedido(PedidoDTO pedidoDTO) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosAConfirmar() throws RemoteException, ExcepcionSistema;
	public CtaCteDTO obtenerCtaCte(int idCliente) throws RemoteException, ExcepcionSistema;
	public String aprobarPedido(int numPedido) throws RemoteException, ExcepcionSistema;
	public String rechazarPedido(int numPedido, String motivo) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosCompletos() throws RemoteException, ExcepcionSistema;
	public String solicitarPedido(int numPedido) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosPendDeposito() throws RemoteException, ExcepcionSistema;
	public Collection<ArticuloEnStockDTO> prepararPedido(int numPedido) throws RemoteException, ExcepcionSistema;
	public String actualizarStockPorVenta(int numPedido, Collection<ArticuloEnStockDTO> artEnStockDTO) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosADespachar() throws RemoteException, ExcepcionSistema;
	public String registrarFechaEntrega(int numPedido, Date fechaEntrega) throws RemoteException, ExcepcionSistema;
	public Collection<OrdenPedidoRepoDTO> obtenerOPRPendientes() throws RemoteException, ExcepcionSistema;
	public String obtenerProveedores(String codBarras) throws RemoteException, ExcepcionSistema;
	public OrdenDeCompraDTO generarOrdenDeCompra(String proveedor, Collection<ArticuloDTO> articulos) throws RemoteException, ExcepcionSistema;
	public OrdenDeCompraDTO obtenerOrdenDeCompra(int numOC) throws RemoteException, ExcepcionSistema;
	public Collection<ArticuloEnStockDTO> cargarArticuloEnStock(int numOC, ArticuloEnStockDTO artEnStockDTO) throws RemoteException, ExcepcionSistema;
	public String cumplirOrdenDeCompra(int numOC) throws RemoteException, ExcepcionSistema;
	public String registrarPago(int idCliente, String tipoPago, float importe) throws RemoteException, ExcepcionSistema;
	public void aplicarPago(int idCliente, float descuento) throws RemoteException, ExcepcionSistema;
	
}
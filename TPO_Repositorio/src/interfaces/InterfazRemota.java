package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.DireccionDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionSistema;

public interface InterfazRemota extends Remote {

	public ClienteDTO loginCliente(String usuario, String pwd) throws RemoteException, ExcepcionSistema;
	public String loginEmpleado(String usuario, String pwd) throws RemoteException, ExcepcionSistema;
	// public ClienteDTO buscarCliente(int idCliente) throws RemoteException, ExcepcionSistema;
	public ClienteDTO altaClienteEmpresa(String cuit, String razonSocial, DireccionDTO direccionFacturacionDTO, char tipoFactura, String condicionesEspeciales, float limiteCredito) throws RemoteException, ExcepcionSistema;
	public ClienteDTO altaClientePersona(String dni, String apellido, String nombre, DireccionDTO direccionFacturacionDTO, char tipoFactura, String condicionesEspeciales, float limiteCredito) throws RemoteException, ExcepcionSistema;
	public void modificarDireccionCliente(int idCliente, DireccionDTO direccionDTO) throws RemoteException, ExcepcionSistema;
	public void bajaCliente(int idCliente) throws RemoteException, ExcepcionSistema;
	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) throws RemoteException, ExcepcionSistema;
	public void modificarArticulo(String codBarras, String desc, String pres, int tamaño, String unidad, float precioVta, int cantFijaCompra, int cantMaxUbi)  throws RemoteException, ExcepcionSistema;
	public void bajaArticulo(String codBarras) throws RemoteException, ExcepcionSistema;
	public Collection<ArticuloDTO> obtenerCatalogo() throws RemoteException, ExcepcionSistema;
	public PedidoDTO generarPedido(PedidoDTO pedidoDTO) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws RemoteException, ExcepcionSistema;
	public Collection<PedidoDTO> obtenerPedidosAConfirmar() throws RemoteException, ExcepcionSistema;
	public String aprobarPedido(int numPedido) throws RemoteException, ExcepcionSistema;
	
}
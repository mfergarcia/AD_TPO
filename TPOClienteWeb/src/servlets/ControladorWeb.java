package servlets;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.ArticuloEnStockDTO;
import dto.CtaCteDTO;
import dto.DireccionDTO;
import dto.ItemArticuloDTO;
import dto.ItemOCDTO;
import dto.OrdenDeCompraDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class ControladorWeb extends HttpServlet {

	private static final long serialVersionUID = 1087702007634924546L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	String jspPage;
    	try {
    		SistemaBD bd= new SistemaBD();
    		String action = request.getParameter("action");
            jspPage = "/index.jsp";
            HttpSession session = request.getSession();
            if ((action == null) || (action.length() < 1))
            {
                action = "default";
            }

            if ("default".equals(action))
            {
                jspPage = "/index.jsp";
            }
            
            else if("CrearPedido".equals(action)) {
            	PedidoDTO p= new PedidoDTO();
            	session.setAttribute("pedido", p );
            	jspPage= "/generarPedido.jsp";
        	}
            
            else if("ElegirArticulo".equals(action)) {
            	PedidoDTO p= (PedidoDTO) session.getAttribute("pedido");
            	ArticuloDTO a=bd.obtenerArticulo(request.getParameter("Articulo"));
            	ItemArticuloDTO i= new ItemArticuloDTO();
            	i.setArticuloDTO(a);
            	i.setCant(Integer.parseInt(request.getParameter("Cantidad")));
            	p.agregarItem(i);
            	session.setAttribute("pedido", p );
            	jspPage= "/generarPedido.jsp";
           	}

            else if("CargarUbicacion".equals(action))
            	jspPage="/cargarUbicacionPedido.jsp";

            else if ("obtenerPedidosAConfirmar".equals(action)){
           		
            	ArrayList<PedidoDTO> pedidosAConfirmar = (ArrayList<PedidoDTO>)bd.obtenerPedidosAConfirmar();
           		request.setAttribute("pedidosAConfirmar", pedidosAConfirmar);
           		jspPage = "/listarPedidosAConfirmar.jsp";
            }

            else if ("obtenerCtaCte".equals(action))
            {
            	String idCliente = request.getParameter("idCliente");
            	String numPedido = request.getParameter("numPedido");
            	int intIdCliente = Integer.parseInt(idCliente);
            	CtaCteDTO ctaCte= bd.obtenerCtaCte(intIdCliente);
            	request.setAttribute("ctaCte", ctaCte);
            	request.setAttribute("idClienteCtaCte", idCliente);
            	request.setAttribute("numPedido", numPedido);
            	jspPage = "/obtenerCtaCte.jsp";
            }

            else if ("aprobarPedido".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	int intNumPedido = Integer.parseInt(numPedido);
            	String nuevoEstado= bd.aprobarPedido(intNumPedido);
            	request.setAttribute("nuevoEstado", nuevoEstado);
            	request.setAttribute("numPedido", numPedido);
            	jspPage = "/resultadoAvancePedido.jsp";
            }

            else if ("ingresarMotivoRechazo".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	request.setAttribute("numPedidoARechazar", numPedido);
            	jspPage = "/ingresarMotivoRechazoPedido.jsp";
            }

            else if ("rechazarPedido".equals(action))
            {
            	String numPedido = request.getParameter("numPedidoARechazar");
            	String motivoRechazo = request.getParameter("motivo");
            	int intNumPedido = Integer.parseInt(numPedido);
            	String nuevoEstado = bd.rechazarPedido(intNumPedido, motivoRechazo);
            	request.setAttribute("numPedido", numPedido);
              	request.setAttribute("nuevoEstado", nuevoEstado);
            	jspPage = "/resultadoAvancePedido.jsp";
            }

            else if ("obtenerPedidosCompletos".equals(action))
            {
            	ArrayList<PedidoDTO> pedidosCompletos = (ArrayList<PedidoDTO>)bd.obtenerPedidosCompletos();
            	request.setAttribute("pedidosCompletos", pedidosCompletos);
           		jspPage = "/listarPedidosCompletos.jsp";
            }

            else if ("solicitarPedido".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	int intNumPedido = Integer.parseInt(numPedido);
            	String nuevoEstado= bd.solicitarPedido(intNumPedido);
            	request.setAttribute("nuevoEstado", nuevoEstado);
            	request.setAttribute("numPedido", numPedido);
            	jspPage = "/resultadoAvancePedido.jsp";
            }
            
            else if ("obtenerPedidosPendDepo".equals(action))
            {
            	ArrayList<PedidoDTO> pedidosPendDepo = (ArrayList<PedidoDTO>)bd.obtenerPedidosPendDeposito();
           		request.setAttribute("pedidosPendDepo", pedidosPendDepo);
           		jspPage = "/listarPedidosPendDepo.jsp";
            }
            
            else if ("obtenerItemsPedidoPendDepo".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	int intNumPedido = Integer.parseInt(numPedido);
            	ArrayList<PedidoDTO> pedidosPendDepo = (ArrayList<PedidoDTO>)bd.obtenerPedidosPendDeposito();
            	ArrayList<ItemArticuloDTO> itemsPedido = new ArrayList<ItemArticuloDTO>(); 
            	PedidoDTO aux;
            	for(Iterator<PedidoDTO> i = pedidosPendDepo.iterator(); i.hasNext(); ) {
            		aux = i.next();
            		if (aux.getNumPedido() == intNumPedido)
            			itemsPedido.addAll(aux.getArticulos());
            	}
            	System.out.println("Consegui " + itemsPedido.size() + " elementos");
            	request.setAttribute("numPedido", numPedido);
            	request.setAttribute("itemsPedido", itemsPedido);
           		jspPage = "/listarItemsPedido.jsp";
            }

            else if ("prepararPedido".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	int intNumPedido = Integer.parseInt(numPedido);
            	ArrayList<ArticuloEnStockDTO> artEnStock = (ArrayList<ArticuloEnStockDTO>)bd.prepararPedido(intNumPedido);
               	request.setAttribute("numPedido", numPedido);
               	request.setAttribute("artEnStock", artEnStock);
               	jspPage = "/listarArticulosEnStock.jsp";
            }

            else if ("actualizarStockPorVenta".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	int intNumPedido = Integer.parseInt(numPedido);
            	ArrayList<ArticuloEnStockDTO> artEnStockAPreparar = (ArrayList<ArticuloEnStockDTO>)session.getAttribute("artEnStockAPreparar");
            	System.out.println("Rescate " + artEnStockAPreparar.size() + " elementos!!!!");
            	//Borro el objeto de sesion
            	session.setAttribute("artEnStockAActualizar", "");
            	String nuevoEstado = bd.actualizarStockPorVenta(intNumPedido, artEnStockAPreparar);
            	request.setAttribute("nuevoEstado", nuevoEstado);
            	request.setAttribute("numPedido", numPedido);
            	jspPage = "/resultadoAvancePedido.jsp";
            }

            else if ("procesarOrdenDeCompra".equals(action))
            {
            	jspPage = "/procesarOrdenDeCompraPendiente.jsp";
            }

            
            else if ("obtenerDetalleOrdenDeCompra".equals(action))
            {
            	String numOC = request.getParameter("numOC");
            	int intNumOC = Integer.parseInt(numOC);
            	OrdenDeCompraDTO ordenDeCompra = (OrdenDeCompraDTO)bd.obtenerOrdenDeCompra(intNumOC);
            	if (ordenDeCompra.getEstado().equals("PENDIENTE")) {
            		request.setAttribute("ordenDeCompra", ordenDeCompra);
            		jspPage = "/obtenerDetalleOrdenDeCompra.jsp";
            	}
            	else {
            		request.setAttribute("mensaje", "La Orden De Compra consultada no se encuentra en estado PENDIENTE");
             		jspPage = "/mostrarExcepcion.jsp";
            	}
            }

            else if ("ingresarArticulosEnStock".equals(action))
            {
            	String numOC = request.getParameter("numOC");
               	String primeraCarga = request.getParameter("primeraCarga");
               	int intPrimeraCarga = Integer.parseInt(primeraCarga);
               	if (intPrimeraCarga == 0) {
                   	int intNumOC = Integer.parseInt(numOC);
               		OrdenDeCompraDTO ordenDeCompra = (OrdenDeCompraDTO)bd.obtenerOrdenDeCompra(intNumOC);
               		ItemOCDTO auxItemOC;
               		ArrayList<ItemOCDTO> itemsOC = new ArrayList<ItemOCDTO>();
               		for (Iterator<ItemOCDTO> i = ordenDeCompra.getItems().iterator(); i.hasNext(); ) {
               			auxItemOC = i.next();
               			itemsOC.add(auxItemOC);
               		}	
               		session.setAttribute("itemsOC", itemsOC);
               	}	
               	request.setAttribute("numOC", numOC);
               	jspPage = "/ingresarArticuloEnStock.jsp";                	
            }

            else if ("cargarArticulosEnStock".equals(action))
            {
            	String numOC = request.getParameter("numOC");
               	int intNumOC = Integer.parseInt(numOC);
               	ArticuloEnStockDTO artEnStock = new ArticuloEnStockDTO();
               	String codBarras = (String)request.getParameter("codBarras");
            	String cantidad = (String)request.getParameter("cantidad");
            	String lote = (String)request.getParameter("lote");
            	String fechaVencimiento = (String)request.getParameter("fechaVencimiento");
            	String precioCompra = (String)request.getParameter("precioCompra");
            	System.out.println("Parametros recibidos: " + codBarras + " " + lote);
               	artEnStock.setCodigoBarras(codBarras);
               	artEnStock.setCantidad(Integer.parseInt(cantidad));
               	artEnStock.setLote(lote);
               	SimpleDateFormat sdfFechaVencimiento = new SimpleDateFormat("yyyy-MM-dd"); 
               	try {
               		artEnStock.setFechaVencimiento(sdfFechaVencimiento.parse(fechaVencimiento)); 
               	} catch (ParseException ex) {
               		artEnStock.setFechaVencimiento(Calendar.getInstance().getTime());
               	}
               	artEnStock.setPrecioCompra(Float.parseFloat(precioCompra));
               	ArrayList<ArticuloEnStockDTO> ubicacionesArtEnStock = (ArrayList<ArticuloEnStockDTO>)bd.cargarArticuloEnStock(intNumOC, artEnStock);
               	request.setAttribute("ubicaciones", ubicacionesArtEnStock);
               	request.setAttribute("numOC", numOC);
               	jspPage = "/mostrarUbicacionesArtEnStock.jsp";                	
            }

            else if ("cumplirOrdenDeCompra".equals(action))
            {
            	String numOC = request.getParameter("numOC");
            	int intNumOC = Integer.parseInt(numOC);
               	String nuevoEstado = (String)bd.cumplirOrdenDeCompra(intNumOC);
               	request.setAttribute("numOC", numOC);
               	request.setAttribute("nuevoEstado", nuevoEstado);
               	jspPage = "/resultadoAvanceOC.jsp";                	
            }

            else if ("obtenerPedidosADespachar".equals(action))
            {
            	ArrayList<PedidoDTO> pedidosADespachar = (ArrayList<PedidoDTO>)bd.obtenerPedidosADespachar();
           		request.setAttribute("pedidosADespachar", pedidosADespachar);
           		jspPage = "/listarPedidosADespachar.jsp";
            }

            else if ("ingresarFechaEntrega".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	request.setAttribute("numPedidoADespachar", numPedido);
            	jspPage = "/ingresarFechaEntrega.jsp";
            }
            
            else if ("despacharPedido".equals(action))
            {
            	String numPedido = request.getParameter("numPedido");
            	String fechaEntrega = request.getParameter("fechaEntrega");
            	int intNumPedido = Integer.parseInt(numPedido);
               	SimpleDateFormat sdfFechaEntrega = new SimpleDateFormat("yyyy-MM-dd"); 
               	Date dFechaEntrega = new Date();
              	try {
               		dFechaEntrega = sdfFechaEntrega.parse(fechaEntrega); 
               	} catch (ParseException ex) {
               		dFechaEntrega = Calendar.getInstance().getTime();
               	}
              	String nuevoEstado = bd.registrarFechaEntrega(intNumPedido, dFechaEntrega);
              	request.setAttribute("numPedido", numPedido);
              	request.setAttribute("nuevoEstado", nuevoEstado);
            	jspPage = "/resultadoAvancePedido.jsp";
            }
           
            dispatch(jspPage, request, response);
    		
    	} catch (ExcepcionComunicacion e) {
    			System.out.println(e.getMensaje());
     		request.setAttribute("mensaje", e.getMensaje());
     		jspPage = "/mostrarExcepcion.jsp";
     		dispatch(jspPage, request, response);
 		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
     		request.setAttribute("mensaje", es.getMensaje());
     		jspPage = "/mostrarExcepcion.jsp";
     		dispatch(jspPage, request, response);
		}
	}
   
	protected void dispatch(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	 if (jsp != null)
    	 {
    		 /*Env�a el control al JSP que pasamos como par�metro, y con los 
             * request / response cargados con los par�metros */
    		 RequestDispatcher rd = request.getRequestDispatcher(jsp);
    		 rd.forward(request, response);
    	 }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

}
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.CtaCteDTO;
import dto.DireccionDTO;
import dto.ItemArticuloDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class MainServlet extends HttpServlet {
private static final long serialVersionUID = 1087702007634924546L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	String jspPage;
    	try {
    		SistemaBD bd= new SistemaBD();
    		String action = request.getParameter("action");
            jspPage = "/menuUsuario.jsp";
            HttpSession session = request.getSession();
            if ((action == null) || (action.length() < 1))
            {
                action = "default";
            }

            if ("default".equals(action))
            {
                jspPage = "/start.jsp";
            }else if("Enviar".equals(action)) {
            	
            	if(request.getParameter("Usuario").equals("Cliente")) {
            		Integer Cliente=bd.loginCliente(request.getParameter("usuario"), request.getParameter("password"));
            		if(Cliente!= null || Cliente!= 0) {
            			request.setAttribute("cliente", Cliente);
            			session.setAttribute("cliente", Cliente);
            			jspPage = "/menuUsuario.jsp";
            		}else 
            	
            			jspPage = "/start.jsp";
            		
            	}else if(request.getParameter("Usuario").equals("Empleado")) {
            		String menu= bd.loginEmpleado(request.getParameter("usuario"), request.getParameter("password"));
            		jspPage= menu;
            	}
            }else if("Cancelar".equals(action))
            	jspPage= "/start.jsp";            
            else if("CompletarPedido".equals(action)) {
            	PedidoDTO p= (PedidoDTO) session.getAttribute("pedido");
            	int Cliente= (Integer) session.getAttribute("cliente");
            	DireccionDTO dirEntrega = new DireccionDTO();
            	dirEntrega.setCalle(request.getParameter("Calle"));
            	dirEntrega.setCodigoPostal(request.getParameter("CodigoPostal"));
            	dirEntrega.setLocalidad(request.getParameter("Localidad"));
            	dirEntrega.setNumero(Integer.parseInt(request.getParameter("Numero")));
            	p.setIdCliente(Cliente);
            	p.setDirEntrega(dirEntrega);
            	bd.generarPedido(p);
            	jspPage= "/menuUsuario.jsp";
            	
           	} else if("ObtenerPedidosPorCliente".equals(action)) {
           		int Cliente= (Integer) session.getAttribute("cliente");
            	Collection<PedidoDTO> lp= bd.obtenerPedidosPorCliente(Cliente);
            	request.setAttribute("pedidos", lp);
            	jspPage= "/cliente-verPedidos.jsp";
            	
            }else if("CrearArticulo".equals(action))
            	jspPage= "/altaArticulo.jsp";
            
            else if ("ArticuloaModificar".equals(action))
            	jspPage= "/obtenerArticuloMod.jsp";
            
            else if("ArticuloaEliminar".equals(action))
            	jspPage= "/eliminarArticulo.jsp";
            
            else if("AltaArticulo".equals(action)) {
            	ArticuloDTO art= new ArticuloDTO();
            	art.setDescripcion(request.getParameter("Descripcion"));
				art.setPresentacion(request.getParameter("Presentacion"));
				art.setCodigoBarras(request.getParameter("codBarras"));
				art.setCantFijaCompra(Integer.parseInt(request.getParameter("cantFijaCompra")));
				art.setCantMaxUbicacion(Integer.parseInt(request.getParameter("cantMaxUbicacion")));
				art.setPrecioVta(Float.parseFloat(request.getParameter("PrecioVenta")));
				art.setTamaño(Integer.parseInt(request.getParameter("Tamaño")));
				art.setUnidad(request.getParameter("Unidad"));
				bd.altaArticulo(art);
				
				jspPage="/menuEmpAdm.jsp";
            }else if("obtenerArticulo".equals(action))
            {
            	ArticuloDTO art= bd.obtenerArticulo(request.getParameter("codBarras"));
            	session.setAttribute("articulo", art);
            	
            	jspPage="/modificarArticulo.jsp";
            }else if("modificarArticulo".equals(action)) {
            	
            	ArticuloDTO art= (ArticuloDTO) session.getAttribute("articulo");
				if(!request.getParameter("Descripcion").equals(""))
					art.setDescripcion(request.getParameter("Descripcion"));
				if(!request.getParameter("Presentacion").equals(""))
					art.setPresentacion(request.getParameter("Presentacion"));
				if(!request.getParameter("Tamaño").equals("")){
					art.setTamaño(Integer.parseInt(request.getParameter("Tamaño")));
				}
				if(!request.getParameter("Unidad").equals("")) {
					art.setUnidad(request.getParameter("Unidad"));
				}
				if(!request.getParameter("PrecioVenta").equals("")){
					art.setPrecioVta(Float.parseFloat(request.getParameter("PrecioVenta")));
				}
				if(!request.getParameter("cantFijaCompra").equals("")){
					art.setCantFijaCompra(Integer.parseInt(request.getParameter("cantFijaCompra")));
				}
				if(!request.getParameter("cantMaxUbicacion").equals(""))
					art.setCantMaxUbicacion(Integer.parseInt(request.getParameter("cantMaxUbicacion")));
				if(!request.getParameter("Estado").equals(""))
					art.setEstado(request.getParameter("Estado").charAt(0));
				bd.modificarArticulo(art);
				jspPage= "/menuEmpAdm.jsp";
				
            }else if("eliminarArticulo".equals(action)) {
            	bd.bajaArticulo(request.getParameter("codigoBarras"));
            	jspPage= "/menuEmpAdm.jsp";
            }else if("AltaClientePersona".equals(action))
            	jspPage="/registrarsePersona.jsp";
            else if("AltaClienteEmpresa".equals(action))
            	jspPage= "/registrarseEmpresa.jsp";
            else if("obtenerCliente".equals(action))
            	jspPage="/obtenerTipoCliente.jsp";
            else if("OPRPend".equals(action))
            	jspPage="/obtenerOrdenRepo.jsp";
            else if("obOC".equals(action))
            	jspPage="/ObtenerOrdenCompra.jsp";
            else if("MostrarCliente".equals(action))
            	jspPage= "/ObtenerClienteEmpYPersona.jsp";
            else if("generarOC".equals(action))
            	jspPage= "/GenerarOrdenDeCompra.jsp";
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
    		 /*Envía el control al JSP que pasamos como parámetro, y con los 
             * request / response cargados con los parámetros */
    		 RequestDispatcher rd = request.getRequestDispatcher(jsp);
    		 rd.forward(request, response);
    	 }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
}

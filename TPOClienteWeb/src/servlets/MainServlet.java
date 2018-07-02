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

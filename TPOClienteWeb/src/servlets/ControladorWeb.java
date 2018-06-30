package servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.DireccionDTO;
import dto.ItemArticuloDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

public class ControladorWeb extends HttpServlet {

	private static final long serialVersionUID = 1087702007634924546L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	try {
    		SistemaBD bd= new SistemaBD();
    		String action = request.getParameter("action");
            String jspPage = "/index.jsp";
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
            	jspPage= "/articulosCatalogo.jsp";
        	}
            
            else if("ElegirArticulo".equals(action)) {
            	PedidoDTO p= (PedidoDTO) session.getAttribute("pedido");
            	ArticuloDTO a=bd.obtenerArticulo(request.getParameter("Articulo"));
            	ItemArticuloDTO i= new ItemArticuloDTO();
            	i.setArticuloDTO(a);
            	i.setCant(Integer.parseInt(request.getParameter("Cantidad")));
            	p.agregarItem(i);
            	session.setAttribute("pedido", p );
            	jspPage= "/articulosCatalogo.jsp";
           	}
            
            else if("CompletarPedido".equals(action)) {
            	PedidoDTO p= (PedidoDTO) session.getAttribute("pedido");
            	DireccionDTO dirEntrega = new DireccionDTO();
            	//Temporal
            	dirEntrega.setCalle("Av de Mayo");
            	dirEntrega.setNumero(200);
            	dirEntrega.setCodigoPostal("1424");
            	dirEntrega.setLocalidad("C.A.B.A.");
            	p.setDirEntrega(dirEntrega);
            	//
            	bd.generarPedido(p);
            	jspPage= "/index.jsp";
           	}
            
            else if ("obtenerPedidosAConfirmar".equals(action))
            {
            	ArrayList<PedidoDTO> pedidosAConfirmar = (ArrayList<PedidoDTO>)bd.obtenerPedidosAConfirmar();
            	System.out.println("La cantidad de elementos son: " + pedidosAConfirmar.size());
            	request.setAttribute("pedidosAConfirmar", pedidosAConfirmar);
            	jspPage = "/aprobarPedido.jsp";
            }else if("ObtenerPedidosPorCliente".equals(action)) {
            	//FALTA INSTANCIA DONDE INICIE SESION COMO CLIENTE Y LLEGUE ACÁ CON UNA ID
            	//Actualmente el metodo se llama por index.jsp, deberia estar en el menu de un cliente
            	Collection<PedidoDTO> lp= bd.obtenerPedidosPorCliente(Integer.parseInt(request.getParameter("idCliente")));
            	request.setAttribute("pedidos", lp);
            	
            	jspPage= "/cliente-verPedidos.jsp";
            }
            
            dispatch(jspPage, request, response);
    		
    		} catch (ExcepcionComunicacion e) {
    			System.out.println(e.getMensaje());
    		} catch (ExcepcionSistema es) {
    			System.out.println(es.getMensaje());
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
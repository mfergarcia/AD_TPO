package servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

            if ((action == null) || (action.length() < 1))
            {
                action = "default";
            }

            if ("default".equals(action))
            {
                jspPage = "/index.jsp";
            }
            
            else if("CrearPedido".equals(action)) {
            	request.setAttribute("Pedido", new PedidoDTO());
            	jspPage= "/articulosCatalogo.jsp";
        	}
            
            else if("ElegirArticulo".equals(action)) {
        		System.out.println("LLegue");
            	String codBarras= request.getParameter("Articulo");
            	Integer cantidad= Integer.parseInt(request.getParameter("Cantidad"));
            	PedidoDTO p= (PedidoDTO) request.getAttribute("Pedido");
            	ArticuloDTO a=bd.obtenerArticulo(codBarras);
            	ItemArticuloDTO i= new ItemArticuloDTO();
            	i.setArticuloDTO(a);
           		i.setCant(cantidad);
           		p.agregarItem(i);
           		request.setAttribute("Pedido", p);	
            	jspPage ="/articulosCatalogo.jsp";
           	}
            
            else if("CompletarPedido".equals(action)) {
            	System.out.println("LLegue");
            	PedidoDTO p= (PedidoDTO) request.getAttribute("Pedido");
            	DireccionDTO dirEntrega = new DireccionDTO();
            	//Temporal
            	dirEntrega.setCalle("Av de Mayo");
            	dirEntrega.setNumero(200);
            	dirEntrega.setCodigoPostal("1424");
            	dirEntrega.setLocalidad("C.A.B.A.");
            	p.setDirEntrega(dirEntrega);
            	//
            	bd.generarPedido(p);
            	jspPage= "/articulosCatalogo.jsp";
           	}
            
            else if ("obtenerPedidosAConfirmar".equals(action))
            {
            	ArrayList<PedidoDTO> pedidosAConfirmar = (ArrayList<PedidoDTO>)bd.obtenerPedidosAConfirmar();
            	System.out.println("La cantidad de elementos son: " + pedidosAConfirmar.size());
            	request.setAttribute("pedidosAConfirmar", pedidosAConfirmar);
            	jspPage = "/aprobarPedido.jsp";
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
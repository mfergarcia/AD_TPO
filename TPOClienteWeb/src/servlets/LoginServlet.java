package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.OrdenDeCompraDTO;
import dto.OrdenPedidoRepoDTO;
import dto.PedidoDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;
import interfaces.InterfazRemota;
import jdk.nashorn.internal.ir.RuntimeNode.Request;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/LoginWeb.jr")
public class LoginServlet extends HttpServlet 
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4507709205142640949L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public LoginServlet() {
      super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//crear el objeto printWrite
    	String jspPage ="/ModificarClientes.jsp";
    	PrintWriter salida=response.getWriter();
		
		

		
			
			//crear conexion con bbdd usando el pool
    	
		try 
		{	// * DESLTILDAR PARA USAR CON OBTENER TIPO CLIENTE
			SistemaBD bd= new SistemaBD();
			String action = request.getParameter("action");
			
			response.setContentType("text/plain");
			jspPage = "/index.jsp";
			
			//inciamos una sessio
			HttpSession session = request.getSession();
			
			if((action == null) || (action.length()<1))
			{
				action ="default";
			}
			
			if ("default".equals(action))
			{
				jspPage = "/index.jsp";
			}
			else if("BUSCAR".equals(action)) //BUSCA POR ID CLIENTE--LO USA OBTENER TIPO CLIENTE Y OBTENER CLIENTEeMPyPERSONA
			{
				String idCliente = request.getParameter("id");
				
				int id = Integer.parseInt(idCliente);
				
				char tipo= bd.obtenerTipoCliente(id);
				
				ClientePersonaDTO cliPerDto = new ClientePersonaDTO();
				ClienteEmpresaDTO cliEmpDto = new ClienteEmpresaDTO();
							
				//HttpSession session = request.getSession();
			
				cliEmpDto = bd.obtenerCteEmpresa(id);
				cliPerDto = bd.obtenerCtePersona(id);
			
			
				//genero una session , guardo instancia de loq ue tengo e request y lo hago perdurar por el timep de vida del programa
			
				session.setAttribute("datos", cliEmpDto);
				request.setAttribute("datos1", cliPerDto);
				session.setAttribute("tipo", tipo);
						
				jspPage= "/ModificarClientes.jsp";
				
				if (tipo == 'P' ) 
				{
					System.out.println("tipo cliente: " +tipo);
					salida.println("El cliente buscado es de tipo: " +tipo);
					
					//request.setAttribute("IdCliente", idCliente);
					//dispatch(jspPage, request, response);
					//ClientePersonaDTO ctePersonaDTO = bd.obtenerCtePersona(id);
					//ClientePersona ctePersona = new ClientePersona(ctePersonaDTO); 
				}
				
				if (tipo == 'E') 
				{
					System.out.println("tipo Empresa: " +tipo);
					salida.println("El cliente buscado es de tipo: " +tipo);
				}
				
				
			
				System.out.println("Cuit :" +cliEmpDto.getCuit()+" Id: "+cliEmpDto.getIdCliente()+" Limite de credito: "+ cliEmpDto.getLimiteCredito());
				
			    System.out.println("TIPO:" +tipo);
			}
				
			
				
				
			dispatch(jspPage, request, response);
			
		}catch (ExcepcionComunicacion e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionSistema e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String jspPage;
    	PrintWriter salida = response.getWriter();
    	
		String listar = request.getParameter("listar");
		
		try{
			SistemaBD bd = new SistemaBD();
						
			jspPage = "/index.jsp";
			//genero la session
			HttpSession session = request.getSession();
			String action = request.getParameter("action");
			
			
			
			if ((action == null) || (action.length() < 1))
            {
                action = "default";
            }

            if ("default".equals(action))
            {
                jspPage = "/index.jsp";
            }
			else if("listarOPR".equals(action)) {
				Collection<OrdenPedidoRepoDTO> ordenPRDTO = new ArrayList<OrdenPedidoRepoDTO>();
				ordenPRDTO=bd.obtenerOPRPendientes();
				
				
				//seteo atributos en la session
				
				
				
				//indico a donde rirecciona 
				jspPage = "/mostrarDatosOrdenPRepo.jsp";
				
				
				int a= 0;
				String var= String.valueOf(a);
				for (OrdenPedidoRepoDTO i:ordenPRDTO ) 
				{
														
					//salida.println("Cantidad Reposicion: "+i.getCantRepo()+"<br/>");
					session.setAttribute(var, i.getNumOrdenPR());
					a++;
					var= String.valueOf(a);
					session.setAttribute(var, i.getEstado());
					a++;
					var= String.valueOf(a);
					session.setAttribute(var, i.getCantRepo());
					a++;
					var= String.valueOf(a);
					session.setAttribute(var, i.getNumPedido());
					a++;
					var= String.valueOf(a);
					session.setAttribute(var, i.getFechaGeneracion());
					a++;
					var= String.valueOf(a);
					//salida.println(" Estado: "+i.getEstado()+"<br/>");
					//salida.println(" Orden Pedido: "+i.getNumOrdenPR()+"<br/>");
					//salida.println(" Numero Pedido: "+i.getNumPedido()+"<br/>");
					//salida.println(" fecha: "+i.getFechaGeneracion()+"<br/><br/>");
				
					
					//session.setAttribute("datos3", ordenPRDTO);
				}
				
				a--;
				var= String.valueOf(a);
				session.setAttribute("iterador",var);
			}else if("BuscarOC".equals(action))//BUSCAr ORDEN DE COMPRA por NUM OC 
			{
				jspPage = "/obtenerOrdenCompraMostar.jsp";
	        	
				String numOc = request.getParameter("obtenerOc");
	        	int Oc = Integer.parseInt(numOc);
        		
    				
    			OrdenDeCompraDTO oCDTO= new OrdenDeCompraDTO();
    			oCDTO= bd.obtenerOrdenDeCompra(Oc);
    			session.setAttribute("OC", oCDTO);
    				
    			salida.println("Numero Orden De compra: "+ oCDTO.getNumOC()+"<br/>");
    			salida.println(" Fecha creacion: "+ oCDTO.getFecha()+"<br/>");
    			salida.println(" Proveedor: "+ oCDTO.getProveedor()+"<br/>");
    			salida.println(" Estado: "+ oCDTO.getEstado()+"<br/>");
    			salida.println(" ITEMS: "+ oCDTO.getItems()+"<br/>");
    		}else if("GenerarOC".equals(action)) 
        	{
        		String proveedor =request.getParameter("proveedor");
            	String articulos = request.getParameter("articulos");
            	jspPage = "/generarOrdenCompraMostar.jsp";
            	
				Collection<ArticuloDTO> art = new ArrayList<ArticuloDTO>();
				ArticuloDTO artTemp= new ArticuloDTO();
				OrdenDeCompraDTO oc= new OrdenDeCompraDTO();
				
				artTemp =bd.obtenerArticulo(articulos);
				
				session.setAttribute("art", artTemp);
				art.add(artTemp);
				
				oc=bd.generarOrdenDeCompra(proveedor, art);
				
				session.setAttribute("goc", oc);
				//salida.println(artTemp.getEstado()+"<br/>");
				//salida.println(artTemp.getCantMaxUbicacion()+"<br/>");
				//salida.println(artTemp.getPresentacion()+"<br/>");
				/*for (ArticuloDTO i:art ) 
				{
					salida.println("Estado: "+i.getEstado()+"<br/>");
					salida.println("Max Ubi: "+i.getCantMaxUbicacion()+"<br/>");
					salida.println("Presentacion: "+i.getPresentacion()+"<br/>");
				}*/
			}else if("SolPedidos".equals(action)) 
			{	
				String numPedido = request.getParameter("numPedido");
        		//salida.println("num Pedido: " + numPedido+"<br/>");
        		int np= Integer.parseInt(numPedido);
        		jspPage = "/solicitarPedidoMostrar.jsp";
        		
        		String var =bd.solicitarPedido(np);
        		session.setAttribute("estado", var);
			
        	}else if("BuscarXiD".equals(action)) 
			{
				String numCli = request.getParameter("idCliente");
    			int nc= Integer.parseInt(numCli);
									
				Collection<PedidoDTO> pedidoId = new ArrayList<PedidoDTO>();
				System.out.println("antes de entrar al bd <br/>");
				pedidoId= bd.obtenerPedidosPorCliente(nc);
				
				System.out.println("Cantidad Reposicion: <br/>");
				
				for (PedidoDTO i : pedidoId ) {
					System.out.println("Cantidad Reposicion: "+i.getNumPedido()+"<br/>");
				}System.out.println("despues del for");
				
				System.out.println("PENDIENTES chau <br/>");
			
    	}if("Despachar".equals(action)) {
    		
				Collection<PedidoDTO> pedidoD = new ArrayList<PedidoDTO>();
				
				pedidoD=bd.obtenerPedidosADespachar();
				
				for (PedidoDTO i:pedidoD ) {
						salida.println("Cantidad Reposicion: "+i.getNumPedido()+"<br/>");
					}System.out.println("despues del for");
		
    	}
            
            
            dispatch(jspPage, request, response);
            
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
		
		
		
		
		
		
		//METDO DE LOGIN
		//FALTA TERMINAR DE FINALIZAR LAS CONDICIoNES
		/*
		PrintWriter salida=response.getWriter();
		String password= request.getParameter("password");
		String usuario = request.getParameter("usuario");
			
		//Buscar en la BD el cliente con ese usuario y que le coincida la calve
		//por lo prontobuscamos un usario
		try {
			SistemaBD bd= new SistemaBD();
			ClienteEmpresaDTO cliEmpDto = new ClienteEmpresaDTO();
			List<ClienteDTO>  miResultSet = new ArrayList<>(); 
			//TEngo que terminar de definir bien el creterio de login
			//pero no tenemos en la bd una tabla donde este la password para chequear
			/*	while(miResultSet.next())
			{
					if(usuario.equals(miResultSet.getString(1)) && password.equals(miResultSet.getString(2)))
					{
						
						
					}
				}
				
			
				if (miResultSet.absolute(0)) out.println("Usuario logueado");
					
					
			}*/
			
	/*		salida.println(" " );
		} catch (ExcepcionComunicacion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		*/
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


}
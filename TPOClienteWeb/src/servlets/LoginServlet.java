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
import dto.ClienteDTO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
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
		PrintWriter salida=response.getWriter();
		
		String idCliente = request.getParameter("id");
		
		
		
		////
		///METODO PARA OBENER CLIENTE EMPRESA
		///DESDE EL ID CLIENTE
		///
	/*	
		String ValIng = request.getParameter("opcion");
		
		System.out.println("ValIng es: " +ValIng);
		System.out.println("ObtenerCteEmpresa es: " +idCliente);
		
		
			System.out.println("Se ingreso ID");
			int id = Integer.parseInt(ValIng);
			try {
				SistemaBD bd= new SistemaBD();
				ClienteEmpresaDTO cliEmpDto = new ClienteEmpresaDTO();
				cliEmpDto = bd.obtenerCteEmpresa(id);
				System.out.println("Cuit :" +cliEmpDto.getCuit()+" Id: "+cliEmpDto.getIdCliente()+" Limite de credito: "+ cliEmpDto.getLimiteCredito());
				salida.println(" IdCliente: \n"+cliEmpDto.getIdCliente());
				salida.println("Tipo de FActura: " +cliEmpDto.getTipoFactura());
				salida.println("Condiciones Especiales: "+cliEmpDto.getCondicionesEspeciales());
				salida.println("Estado: "+cliEmpDto.getEstado());
				salida.println("Razon Social: "+cliEmpDto.getRazonSocial());
				salida.println("Cuit :" +cliEmpDto.getCuit());
				salida.println("Limite de credito: "+ cliEmpDto.getLimiteCredito());
			} catch (ExcepcionComunicacion | NotBoundException | ExcepcionSistema e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		*/
		
			
			
			
	
	// * DESLTILDAR PARA USAR CON OBTENER TIPO CLIENTE
	// * 	
					
			int id = Integer.parseInt(idCliente);
			response.setContentType("text/plain");
				
			//crear conexion con bbdd usando el pool
		
			try {	
					SistemaBD bd= new SistemaBD();
					char tipo= bd.obtenerTipoCliente(id);
		   	 
					if (tipo == 'P'){
						System.out.println("tipo cliente: " +tipo);
						salida.println("El cliente buscado es de tipo: " +tipo);
						//ClientePersonaDTO ctePersonaDTO = bd.obtenerCtePersona(id);
						//ClientePersona ctePersona = new ClientePersona(ctePersonaDTO); 
						}
					if (tipo == 'E') {
					System.out.println("tipo Empresa: " +tipo);
						salida.println("El cliente buscado es de tipo: " +tipo);
						
					}
					
						
					
				   
					System.out.println("TIPO:" +tipo);
			}catch (ExcepcionComunicacion e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (ExcepcionSistema e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		//METDO DE LOGIN
		//FALTA TERMINAR DE FINALIZAR LAS CONDICIoNES
		
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
			
			salida.println(" " );
		} catch (ExcepcionComunicacion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
}
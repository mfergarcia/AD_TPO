package test;

import delegados.SistemaBD;
import dto.ClienteDTO;
import dto.DireccionDTO;
import excepciones.*;

public class TestCliente_FER {

	public TestCliente_FER() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			SistemaBD controlador = new SistemaBD();

			/* Prueba alta Cliente NO FUNCIONA */
			DireccionDTO direccionDTO = new DireccionDTO();
			direccionDTO.setCalle("Lima");
			direccionDTO.setNumero(33);
			direccionDTO.setLocalidad("Buenos Aires");
			direccionDTO.setCodigoPostal("1001");
			ClienteDTO clienteDTO = controlador.altaClienteEmpresa("33-334535353-7", "MAKRO", direccionDTO, 'A', "Solo venta en efectivo", 3000); 
			System.out.print(clienteDTO.getIdCliente() + " " + clienteDTO.getIdentificacion());
			
			/* Prueba login Cliente */
			String usuario = "maria";
			String pwd = "MARIA";
			clienteDTO = controlador.loginCliente(usuario, pwd);
			System.out.println("Su cliente es: " + clienteDTO.getIdCliente() + " " + clienteDTO.getIdentificacion());


			/* Prueba login Empleado 
			String usuario = "user_despacho";
			String pwd = "USER_DESPACHO";
			String menu = controlador.loginEmpleado(usuario, pwd);
			System.out.print("Menu: " + menu);
			*/
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMensaje());
		}
	}

}


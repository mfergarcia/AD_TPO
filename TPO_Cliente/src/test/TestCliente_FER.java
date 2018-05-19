// @Todos: usar este archivo para probar
package test;

import delegados.SistemaBD;
import dto.ClienteDTO;
import dto.ClienteEmpresaDTO;
import dto.DireccionDTO;
import excepciones.*;

public class TestCliente_FER {

	public TestCliente_FER() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			SistemaBD bd = new SistemaBD();

			/* Prueba alta Cliente */
			/* DireccionDTO direccionDTO = new DireccionDTO();
			direccionDTO.setCalle("Lima");
			direccionDTO.setNumero(33);
			direccionDTO.setLocalidad("Buenos Aires");
			direccionDTO.setCodigoPostal("1001");
			*/
			
			ClienteEmpresaDTO cteEmpresaDTO = new ClienteEmpresaDTO();
			cteEmpresaDTO.setRazonSocial("MAKRO");
			cteEmpresaDTO.setCuit("33-334535353-7");
			cteEmpresaDTO = bd.altaClienteEmpresa(cteEmpresaDTO);
			
			//ClienteDTO clienteDTO = controlador.altaClienteEmpresa("33-334535353-7", "MAKRO", direccionDTO, 'A', "Solo venta en efectivo", 3000); 
			System.out.print(/*clienteDTO.getIdCliente() + */" " + cteEmpresaDTO.getCuit());
			
			/* Prueba login Cliente */
		/*	String usuario = "maria";
			String pwd = "MARIA";
			clienteDTO = bd.loginCliente(usuario, pwd);
			System.out.println("Su cliente es: " + clienteDTO.getIdCliente() + " " + clienteDTO.getIdentificacion());
*/

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


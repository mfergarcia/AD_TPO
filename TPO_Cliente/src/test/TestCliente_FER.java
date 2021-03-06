// @Todos: usar este archivo para probar
package test;

import delegados.SistemaBD;
import dto.ClienteDTO;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.DireccionDTO;
import excepciones.*;

public class TestCliente_FER {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			SistemaBD bd = new SistemaBD();
			
			// Prueba Alta Cliente Empresa
	
			ClienteEmpresaDTO cteEmpresa = new ClienteEmpresaDTO();
			cteEmpresa.setCuit("30-22333444-5");
			cteEmpresa.setRazonSocial("Todo fiestas");
			DireccionDTO dirFacturacion = new DireccionDTO();
			dirFacturacion.setCalle("Cabildo");
			dirFacturacion.setNumero(2031);
			dirFacturacion.setCodigoPostal("1490");
			dirFacturacion.setLocalidad("C.A.B.A");
			cteEmpresa.setDireccionFacturacion(dirFacturacion);
			cteEmpresa.setTipoFactura('A');
			cteEmpresa.setCondicionesEspeciales("Venta con tarjeta");
			cteEmpresa.setLimiteCredito(90000);
			cteEmpresa = bd.altaClienteEmpresa(cteEmpresa);
			System.out.println("Cliente Empresa: " + cteEmpresa.getIdCliente() + " " + cteEmpresa.getRazonSocial());;

			cteEmpresa = new ClienteEmpresaDTO();
			cteEmpresa.setCuit("33-33444555-6");
			cteEmpresa.setRazonSocial("Kiosco Josesito");
			dirFacturacion = new DireccionDTO();
			dirFacturacion.setCalle("Aranguren");
			dirFacturacion.setNumero(1028);
			dirFacturacion.setCodigoPostal("1405");
			dirFacturacion.setLocalidad("C.A.B.A");
			cteEmpresa.setDireccionFacturacion(dirFacturacion);
			cteEmpresa.setTipoFactura('B');
			cteEmpresa.setCondicionesEspeciales("Venta solo efectivo");
			cteEmpresa.setLimiteCredito(0);
			cteEmpresa = bd.altaClienteEmpresa(cteEmpresa);
			System.out.println("Cliente Empresa: " + cteEmpresa.getIdCliente() + " " + cteEmpresa.getRazonSocial());;

			cteEmpresa = new ClienteEmpresaDTO();
			cteEmpresa.setCuit("33-44555666-7");
			cteEmpresa.setRazonSocial("Supermercados Chinos & CIA");
			dirFacturacion = new DireccionDTO();
			dirFacturacion.setCalle("Donato Alvarez");
			dirFacturacion.setNumero(235);
			dirFacturacion.setCodigoPostal("1406");
			dirFacturacion.setLocalidad("C.A.B.A");
			cteEmpresa.setDireccionFacturacion(dirFacturacion);
			cteEmpresa.setTipoFactura('B');
			cteEmpresa.setCondicionesEspeciales("Venta con cheque a 30 dias");
			cteEmpresa.setLimiteCredito(100000);
			cteEmpresa = bd.altaClienteEmpresa(cteEmpresa);
			System.out.println("Cliente Empresa: " + cteEmpresa.getIdCliente() + " " + cteEmpresa.getRazonSocial());;
	
			// Prueba Alta Cliente Persona

			ClientePersonaDTO ctePersona = new ClientePersonaDTO();
			ctePersona.setDni("25666777");
			ctePersona.setApellido("San Martin");
			ctePersona.setNombre("Jose");
			dirFacturacion = new DireccionDTO();
			dirFacturacion.setCalle("Cachimayo");
			dirFacturacion.setNumero(230);
			dirFacturacion.setCodigoPostal("1406");
			dirFacturacion.setLocalidad("C.A.B.A");
			ctePersona.setDireccionFacturacion(dirFacturacion);
			ctePersona.setTipoFactura('C');
			ctePersona.setCondicionesEspeciales("Venta solo efectivo");
			ctePersona.setLimiteCredito(0);
			ctePersona = bd.altaClientePersona(ctePersona);
			System.out.println("Cliente Persona: " + ctePersona.getIdCliente() + " " + ctePersona.getApellido() + ", " + ctePersona.getNombre());

			ctePersona = new ClientePersonaDTO();
			ctePersona.setDni("25777888");
			ctePersona.setApellido("Guemes");
			ctePersona.setNombre("Miguel");
			dirFacturacion = new DireccionDTO();
			dirFacturacion.setCalle("Melincue");
			dirFacturacion.setNumero(450);
			dirFacturacion.setCodigoPostal("1425");
			dirFacturacion.setLocalidad("C.A.B.A");
			ctePersona.setDireccionFacturacion(dirFacturacion);
			ctePersona.setTipoFactura('C');
			ctePersona.setCondicionesEspeciales("Venta con tarjeta");
			ctePersona.setLimiteCredito(20000);
			ctePersona = bd.altaClientePersona(ctePersona);
			System.out.println("Cliente Persona: " + ctePersona.getIdCliente() + " " + ctePersona.getApellido() + ", " + ctePersona.getNombre());

			ctePersona = new ClientePersonaDTO();
			ctePersona.setDni("25888999");
			ctePersona.setApellido("Belgrano");
			ctePersona.setNombre("Manuel");
			dirFacturacion = new DireccionDTO();
			dirFacturacion.setCalle("Neuquen");
			dirFacturacion.setNumero(390);
			dirFacturacion.setCodigoPostal("1407");
			dirFacturacion.setLocalidad("C.A.B.A");
			ctePersona.setDireccionFacturacion(dirFacturacion);
			ctePersona.setTipoFactura('C');
			ctePersona.setCondicionesEspeciales("Venta con tarjeta");
			ctePersona.setLimiteCredito(50000);
			ctePersona = bd.altaClientePersona(ctePersona);
			System.out.println("Cliente Persona: " + ctePersona.getIdCliente() + " " + ctePersona.getApellido() + ", " + ctePersona.getNombre());

			/* Prueba obtener Cliente*/
			/* Prueba obtener ClienteEmpresa */
			/* Prueba obtener ClientePersona */

			int idClienteAObtener1 = 3;
			char tipoCliente1 = bd.obtenerTipoCliente(idClienteAObtener1);
			System.out.println("El tipo de cliente a buscar es: " + tipoCliente1);
			if (tipoCliente1 == 'E') {
				ClienteEmpresaDTO cteEmpresa2 = bd.obtenerCteEmpresa(idClienteAObtener1);
				System.out.println("Cliente Empresa: " + cteEmpresa2.getIdCliente() + " " + cteEmpresa2.getRazonSocial());
			}
			else {
				ClientePersonaDTO ctePersona2 = bd.obtenerCtePersona(idClienteAObtener1);
				System.out.println("Cliente Persona: " + ctePersona2.getIdCliente() + " " + ctePersona2.getApellido() + ", " + ctePersona2.getNombre());
			}

			/* Prueba Modificar Cliente Empresa */
			/* Prueba Modificar Cliente Persona */

			int idClienteAObtener2 = 2;
			char tipoCliente2 = bd.obtenerTipoCliente(idClienteAObtener2);
			System.out.println("El tipo de cliente a buscar es: " + tipoCliente2);
			if (tipoCliente2 == 'E') {
				ClienteEmpresaDTO cteEmpresa3 = bd.obtenerCteEmpresa(idClienteAObtener2);
				System.out.println("Cliente Empresa: " + cteEmpresa3.getIdCliente() + " " + cteEmpresa3.getRazonSocial());
				cteEmpresa3.setLimiteCredito(200000);
				cteEmpresa3 = bd.modificarCteEmpresa(cteEmpresa3);
				System.out.println("Cliente Empresa Modificado: " + cteEmpresa3.getIdCliente() + " " + cteEmpresa3.getLimiteCredito());
			}
			else {
				ClientePersonaDTO ctePersona3 = bd.obtenerCtePersona(idClienteAObtener2);
				System.out.println("Cliente Persona: " + ctePersona3.getIdCliente() + " " + ctePersona3.getApellido() + ", " + ctePersona3.getNombre());
				dirFacturacion = new DireccionDTO();
				dirFacturacion.setCalle("Pasaje El Delta");
				dirFacturacion.setNumero(300);
				dirFacturacion.setCodigoPostal("2001");
				dirFacturacion.setLocalidad("Avellaneda");
				ctePersona3.setDireccionFacturacion(dirFacturacion);
				ctePersona3 = bd.modificarCtePersona(ctePersona3);
				System.out.println("Cliente Persona Modificado: " + ctePersona3.getIdCliente() + " " + ctePersona3.getDireccionFacturacion().getCalle());
			}

			
			
			
		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMensaje());
		}
	}

}


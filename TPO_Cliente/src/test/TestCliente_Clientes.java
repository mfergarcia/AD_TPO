package test;

import delegados.SistemaBD;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.DireccionDTO;
import excepciones.*;

public class TestCliente_Clientes {
	
	public static void main(String[] args) {
		try {
			SistemaBD bd = new SistemaBD();
			
			// Prueba alta Cliente Empresa
			/*
			ClienteEmpresaDTO cteEmpresa = new ClienteEmpresaDTO();
			cteEmpresa.setCuit("30-29777999-7");
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
			*/
		
			// Prueba alta Cliente Persona
			/*
			ClientePersonaDTO ctePersona = new ClientePersonaDTO();
			ctePersona.setDni("25666888");
			ctePersona.setApellido("San Martin");
			ctePersona.setNombre("Jose");
			DireccionDTO dirFacturacion = new DireccionDTO();
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
			*/
			
			/* Prueba obtener Cliente*/
			/* Prueba obtener ClienteEmpresa */
			/* Prueba obtener ClientePersona */
			int idClienteAObtener = 6;
			char tipoCliente = bd.obtenerTipoCliente(idClienteAObtener);
			System.out.println("El tipo de cliente a buscar es: " + tipoCliente);
			if (tipoCliente == 'E') {
				ClienteEmpresaDTO cteEmpresa = bd.obtenerCteEmpresa(idClienteAObtener);
				System.out.println("Cliente Empresa: " + cteEmpresa.getIdCliente() + " " + cteEmpresa.getRazonSocial());
			}
			else {
				ClientePersonaDTO ctePersona = bd.obtenerCtePersona(idClienteAObtener);
				System.out.println("Cliente Persona: " + ctePersona.getIdCliente() + " " + ctePersona.getApellido() + ", " + ctePersona.getNombre());
			}

		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
	}

}

package test;

import delegados.SistemaBD;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.DireccionDTO;
import excepciones.*;

public class TestCliente_Clientes {

	public TestCliente_Clientes() {

	}
	
	public static void main(String[] args) {
		try {
			SistemaBD bd = new SistemaBD();
			
			// Prueba alta Cliente Empresa
			/*
			ClienteEmpresaDTO cteEmpresaDTO = new ClienteEmpresaDTO();
			cteEmpresaDTO.setCuit("30-29777999-7");
			cteEmpresaDTO.setRazonSocial("Todo fiestas");
			DireccionDTO dirDTO = new DireccionDTO();
			dirDTO.setCalle("Cabildo");
			dirDTO.setNumero(2031);
			dirDTO.setCodigoPostal("1490");
			dirDTO.setLocalidad("C.A.B.A");
			cteEmpresaDTO.setDireccionFacturacion(dirDTO);
			cteEmpresaDTO.setTipoFactura('A');
			cteEmpresaDTO.setCondicionesEspeciales("Venta con tarjeta");
			cteEmpresaDTO.setLimiteCredito(90000);
			cteEmpresaDTO = bd.altaClienteEmpresa(cteEmpresaDTO);
			
			System.out.println("Cliente Empresa: " + cteEmpresaDTO.getIdCliente() + " " + cteEmpresaDTO.getRazonSocial());;
			*/
		
			// Prueba alta Cliente Persona
			/*
			ClientePersonaDTO ctePersonaDTO = new ClientePersonaDTO();
			ctePersonaDTO.setDni("25666888");
			ctePersonaDTO.setApellido("San Martin");
			ctePersonaDTO.setNombre("Jose");
			DireccionDTO dirDTO = new DireccionDTO();
			dirDTO.setCalle("Cachimayo");
			dirDTO.setNumero(230);
			dirDTO.setCodigoPostal("1406");
			dirDTO.setLocalidad("C.A.B.A");
			ctePersonaDTO.setDireccionFacturacion(dirDTO);
			ctePersonaDTO.setTipoFactura('C');
			ctePersonaDTO.setCondicionesEspeciales("Venta solo efectivo");
			ctePersonaDTO.setLimiteCredito(0);
			ctePersonaDTO = bd.altaClientePersona(ctePersonaDTO);
			
			System.out.println("Cliente Persona: " + ctePersonaDTO.getIdCliente() + " " + ctePersonaDTO.getApellido() + ", " + ctePersonaDTO.getNombre());
			*/
			
			/* Prueba buscar Cliente*/
			int idClienteAObtener = 4;
			char tipoCliente = bd.obtenerTipoCliente(idClienteAObtener);
			System.out.println("El tipo de cliente a buscar es: " + tipoCliente);
			if (tipoCliente == 'E') {
				ClienteEmpresaDTO cteEmpresaDTO = bd.obtenerCteEmpresa(idClienteAObtener);
				System.out.println("Cliente Empresa: " + cteEmpresaDTO.getIdCliente() + " " + cteEmpresaDTO.getRazonSocial());
			}
			else {
				ClientePersonaDTO ctePersonaDTO = bd.obtenerCtePersona(idClienteAObtener);
				System.out.println("Cliente Persona: " + ctePersonaDTO.getIdCliente() + " " + ctePersonaDTO.getApellido() + ", " + ctePersonaDTO.getNombre());
			}

		} catch (ExcepcionComunicacion e) {
			System.out.println(e.getMensaje());
		} catch (ExcepcionSistema es) {
			System.out.println(es.getMensaje());
		}
	}

}

<%@page import="dto.ClienteEmpresaDTO"%>
<%@page import="delegados.SistemaBD"%>
<%@page import="excepciones.*" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <!-- Copiar importacion de clases, no lo hace automatico a veces -->
   <!-- NO USAR ESTE JSP HASTA NO HABER CREADO UN CLIENTE EMPRESA CON LA ID 1 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
public 	ClienteEmpresaDTO obtCliente(){
	try{
		SistemaBD bd= new SistemaBD();
		ClienteEmpresaDTO cedto= bd.obtenerCteEmpresa(1);
		return cedto;
	} catch (ExcepcionComunicacion e) {
		System.out.println(e.getMensaje());
	} catch (ExcepcionSistema es) {
		System.out.println(es.getMensaje());
	}
	return null;
}

public void ModificarCliente(String razonSocial){
	try{
		SistemaBD bd= new SistemaBD();
		ClienteEmpresaDTO cedto= obtCliente();
		cedto.setRazonSocial(razonSocial);
		bd.modificarCteEmpresa(cedto);
	} catch (ExcepcionComunicacion e) {
		System.out.println(e.getMensaje());
	} catch (ExcepcionSistema es) {
		System.out.println(es.getMensaje());
	}
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mira Cliente!</title>
</head>
<body>
RazonSocialCliente= <%= obtCliente().getRazonSocial() %>
<form>
NuevaRazonSocial= <input type="text" name="RazonSocial" id="RazonSocial">
<input type="submit" value="Modificar" onclick="ModificarCliente('RazonSocial');">
</form>
</body>
</html>
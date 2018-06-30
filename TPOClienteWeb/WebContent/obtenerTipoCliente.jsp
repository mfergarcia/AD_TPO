<%@page import="jdk.nashorn.internal.ir.RuntimeNode.Request"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="dto.ClienteDTO"%>
<%@page import="delegados.SistemaBD" %>
<%@page import="excepciones.ExcepcionSistema" %>
<%@page import="excepciones.ExcepcionComunicacion" %>
<%@page import="java.net.MalformedURLException"%>
<%@page import= "java.rmi.NotBoundException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="JavaScript">
function Mostrar(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	var id= document.getElementById("id");
	
	alert("dato ingresado: "+id.value);
	
}

function carga(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	document.getElementById("id").focus();
	
	
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar TIPO CLIENTE </title>
<% 
/*try{
	SistemaBD bd= new SistemaBD();
	*/
%>



</head>
	<body onload="carga();" >

<!--  creo formulario-->
<form action= "LoginWeb.jr" method="GET" onsubmit="return Mostrar();">
<div  align ="center" style ="border-bottom-style: groove;"><h1>Ingrese el ID de Cliente para obtener el TIPO</h1></div>
	ID Cliente:<input type="text" name="id" id="id">
	<button type="submit" value="buscarCliente" >BUSCAR</button>
	<button type="button" value="Submit" onclick="" >Cancelar</button>
	
<!-- 	<br><br><br><jsp:include page="fecha.jsp"></jsp:include> -->
	</form>
	</body>
<%/*
int idCliente= 2;
char tipo =bd.obtenerTipoCliente(idCliente);
out.print("tipo de dato del cliente es:"+tipo);
}catch (ExcepcionComunicacion e) {
	System.out.println(e.getMensaje());
}

*/
%>
</html>

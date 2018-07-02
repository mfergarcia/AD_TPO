<%@page import="jdk.nashorn.internal.ir.RuntimeNode.Request"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="java.io.PrintWriter"%>
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
function Verificar(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	var opcion= document.getElementById("opcion");
	
	//alert("dato ingresado: "+ opcion.value);
	
	if ((opcion.value == "" || opcion.value == null)) {
		//muestro mensaje en patalla
		alert("ID CLIENTE NO INGRESADO");
		opcion.focus();
		return false; 
	} else {
		alert("dato ingresado: "+ opcion.value);
		return true;
	}
	
	
}

function carga(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	document.getElementById("opcion").focus();
	
	
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MODIFICAR</title>
<style>

body{
	background-color:#FFC;
}

table{
	background:#FF6;
	padding:10px;
	border:solid 2px #FF0000;
}

{
	padding:5px 0;
}


</style>
</head>
	<body onload="carga();" >

<!--  creo formulario-->
<form action= "LoginServlet" method="GET" onsubmit="return Verificar();">
<div  align ="center" style =""><h1>BUSCAR CLIENTES A MODIFICAR</h1></div>
	<table align="center" width="25%">
		<tr>
			<td width="20%"><label for="nombre">ID Cliente: </label></td>
			<td width="88%"><input type="text" name="id" id="id"></td>
		</tr>
		<tr>
			<td width="20%"><input type="submit" name ="action" id="action" value="BUSCAR"></td>
			<td width="88%"><input type="submit" name ="cancelar" id="cancelar" value="CANCELAR"></td>
		</tr>
		
	</table>
	<div  align ="center" style =""><jsp:include page="fecha.jsp"></jsp:include></div>
	</form>
	</body>
	
</html>
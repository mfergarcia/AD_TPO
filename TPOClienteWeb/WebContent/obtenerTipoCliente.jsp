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
<form action= "LoginWeb.jr" method="GET" onsubmit="return Mostrar();">
<div  align ="center" style ="border-bottom-style: groove;"><h1>Buscar TIPO CLIENTE</h1></div>
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

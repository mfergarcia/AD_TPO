

<%@page import="sun.dc.DuctusRenderingEngine"%>
<%@page import= "dto.ClienteEmpresaDTO"%>
<%@page import= "dto.ClientePersonaDTO"%>
<%@page import= "dto.OrdenPedidoRepoDTO"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "java.util.Collection"%>
<%@page import= "java.util.Iterator"%>
<%@page import= "java.util.List"%>
<%@page import= "java.io.PrintWriter"%>
<%@page import= "java.util.Iterator"%>
<%@page import= "dto.OrdenDeCompraDTO"%>

<%@page import= "servlets.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" language="JavaScript">
function carga(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	alert("EL ID AHORA ES"+request.getParameter("id"));
	
}



</script>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MODIFICAR CLIENTES</title>

<%
String estado = session.getAttribute("estado").toString();

%> 


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
<body onload="carga();">
<h1 style="text-align:center">Estado del Producto</h1>

<!-- recibo los datos desde  LoginServlet X request.setAttribute("IdCliente", idCliente);
<p> dato que llego de la otr pagina: <%=request.getAttribute("IdCliente") %> </p>
-->
<form action="LoginWeb.jr" method="POST">


<table align="center" width="25%">
  	 <tr>
      <td width="13%"><label for="nombre">Estado: </label></td>
      <td width="87%"><input type="text" name="numOPR" id="numOPR" value=<%=estado%>></td>
    </tr>
    
</form>
</body>
</html>




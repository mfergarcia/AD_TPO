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


<%@page import= "com.tpoClintes.jsp.servlets.LoginServlet"%>
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
String iterator= session.getAttribute("iterador").toString();
int it = Integer.parseInt(iterator);

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
<h1 style="text-align:center">ORDENES PEDIDOS REPOSICION</h1>

<!-- recibo los datos desde  LoginServlet X request.setAttribute("IdCliente", idCliente);
<p> dato que llego de la otr pagina: <%=request.getAttribute("IdCliente") %> </p>
-->
<form action="LoginWeb.jr" method="POST">

<%
int tope= 0;
String var= String.valueOf(tope);
	while (tope <= it){
		
		
%>
<table align="center" width="25%">
  	 <tr>
      <td width="13%"><label for="nombre">N° ORDEN PR: </label></td>
      <td width="87%"><input type="text" name="numOPR" id="numOPR" value=<%=session.getAttribute(var).toString()%>></td>
    </tr>
    <%
	tope++;
    var= String.valueOf(tope);
    %>
     <tr>
    <td width="13%"><label for="usuario">Estado: </label></td>
      <td width="87%"><input type="text" name="fecha" id="fecha" value=<%=session.getAttribute(var).toString()%> ></td>
    </tr>
    <%
	tope++;
    var= String.valueOf(tope);
    %>
    
    <tr>
      <td><label for="apellido">CANTIDAD A REPONER  </label></td>
      <td><input type="text" name="cantRepo" id="cantRepo" value=<%=session.getAttribute(var).toString()%>></td>
    </tr>
     <%
	tope++;
    var= String.valueOf(tope);
    %>
    <tr>
      <td><label for="contra" onfocus="">Numero de Pedido: </label></td>
     <td><input type="text" name="especiales" id="especiales" value=<%=session.getAttribute(var).toString()%>></td>
    </tr>
     <%
	tope++;
    var= String.valueOf(tope);
    %>
    <tr>
      <td><label for="nombre">FECHA GENERACION: </label></td>
      <td><input type="text" name="estado" id="estado" value=<%=session.getAttribute(var).toString()%>></td>
    </tr>
  	<p><br> </p>
	
<%
	tope++;
	var= String.valueOf(tope);
	}
%>

  
</form>
</body>
</html>




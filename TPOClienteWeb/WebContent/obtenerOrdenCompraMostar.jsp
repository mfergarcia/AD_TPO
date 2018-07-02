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
OrdenDeCompraDTO oCDTO= new OrdenDeCompraDTO();
oCDTO = (OrdenDeCompraDTO)session.getAttribute("OC");

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
<h1 style="text-align:center">ORDEN de COMPRA</h1>

<!-- recibo los datos desde  LoginServlet X request.setAttribute("IdCliente", idCliente);
<p> dato que llego de la otr pagina: <%=request.getAttribute("IdCliente") %> </p>
-->
<form action="LoginWeb.jr" method="POST">


<table align="center" width="25%">
  	 <tr>
      <td width="13%"><label for="nombre">NUM OC: </label></td>
      <td width="87%"><input type="text" name="numOPR" id="numOPR" value=<%=oCDTO.getNumOC()%>></td>
    </tr>
   
     <tr>
    <td width="13%"><label for="usuario">Estado: </label></td>
      <td width="87%"><input type="text" name="fecha" id="fecha" value=<%=oCDTO.getEstado()%> ></td>
    </tr>
       
    <tr>
      <td><label for="contra" onfocus="">PROVEEDOR: </label></td>
     <td><input type="text" name="especiales" id="especiales" value=<%=oCDTO.getProveedor()%>></td>
    </tr>
   
    <tr>
      <td><label for="nombre">FECHA GENERACION: </label></td>
      <td><input type="text" name="estado" id="estado" value=<%=oCDTO.getFecha()%>></td>
    </tr>
  	<p><br> </p>


  
</form>
</body>
</html>




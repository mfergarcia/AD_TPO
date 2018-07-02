<%@page import="sun.dc.DuctusRenderingEngine"%>
<%@page import= "dto.ClienteEmpresaDTO"%>
<%@page import= "dto.ClientePersonaDTO"%>
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
<h1 style="text-align:center">NUEVO CLIENTE EMPRESA</h1>

<!-- recibo los datos desde  LoginServlet X request.setAttribute("IdCliente", idCliente);
<p> dato que llego de la otr pagina: <%=request.getAttribute("IdCliente") %> </p>
-->
<form action="LoginWeb.jr" method="POST">

  <table align="center" width="25%">
  	
    <tr>
      <td><label for="usuario">CUIT: </label></td>
      <td><input type="text" name="cuit" id="cuit" value= "solo para empresas" ></td>
    </tr>
    <tr>
      <td><label for="contra" onfocus="">CONDICIONES ESPECIALES: </label></td>
     <td><input type="text" name="especiales" id="especiales" ></td>
    </tr>
    
    
    <tr>
      <td width="13%"><label for="nombre">RAZON SOCIAL: </label></td>
      <td width="87%"><input type="text" name="rsocial" id="rsocial" value= "solo para empresas" ></td>
    </tr>
    <tr>
      <td width="13%"><label for="nombre">ESTADO: </label></td>
      <td width="87%"><input type="text" name="estado" id="estado" ></td>
    </tr>
    <td>
    <tr>
      <td><label for="contra" onfocus="">Limite de Credito: </label></td>
     <td><input type="text" name="limite" id="limite" ></td>
    </tr> 
    </td>
    <tr>
      <td width="13%"><label for="nombre">TIPO FACTURA: </label></td>
      <td width="87%"><input type="text" name="factura" id="factura" ></td>
    </tr>
    <tr>
      <td width="13%"><label for="nombre">DIRECCION: </label><br/></td>
	</tr>
	<tr>      
      <td width="13%"><label for="nombre">CALLE: </label><br/></td>
      <td width="87%"><input type="text" name="calle" id="calle" ></td>
	</tr>
	<tr>      
      <td width="13%"><label for="nombre">NUMERO: </label><br/></td>
      <td width="87%"><input type="text" name="numero" id="numero" ></td>
	</tr>
	<tr>      
      <td width="13%"><label for="nombre">CP: </label></td>
      <td width="87%"><input type="text" name="cp" id="cp" ></td>
	</tr>
	<tr>
      <td width="13%"><label for="nombre">LOCALIDAD: </label></td>
      <td width="87%"><input type="text" name="localidad" id="localidad" value = ></td>
</tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" name="action" id="action" value="AltaEmp"></td>
      
      <td colspan="2" align="center"><input type="submit" name="button" id="button" value="Aceptar"></td>
    </tr>
  </table>
  
  <p><br>
  </p>
</form>
</body>
</html>




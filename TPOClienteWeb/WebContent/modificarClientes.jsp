<%@page import="sun.dc.DuctusRenderingEngine"%>
<%@page import= "dto.ClienteEmpresaDTO"%>
<%@page import= "dto.ClientePersonaDTO"%>
<%@page import= "servlets.LoginServlet"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
ClienteEmpresaDTO cliEmpDto = (ClienteEmpresaDTO)session.getAttribute("datos");
ClientePersonaDTO cliPerDto = (ClientePersonaDTO)request.getAttribute("datos1");
String tipo = session.getAttribute("tipo").toString();
//out.println(" datos en cliente persona: "+cliPerDto.getApellido());
//out.println(" datos en cliente empresa: "+cliEmpDto.getCuit());
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
<h1 style="text-align:center">DATOS CLIENTES</h1>

<!-- recibo los datos desde  LoginServlet X request.setAttribute("IdCliente", idCliente);
<p> dato que llego de la otr pagina: <%=request.getAttribute("IdCliente") %> </p>
-->
<form action="LoginWeb.jr" method="POST">

  <table align="center" width="25%">
  	 <tr>
      <td width="13%"><label for="nombre">ID CLIENTE: </label></td>
      <td width="87%"><input type="text" name="idCliente" id="idCliente" value=<%=cliPerDto.getIdCliente()%>></td>
    </tr>
    <tr>
    
      <td width="13%"><label for="nombre">NOMBRE: </label></td>
      <td width="87%"><input type="text" name="nombre" id="nombre" value=<%=cliPerDto.getNombre()%> ></td>
    </tr>
    <tr>
      <td><label for="apellido">APELLIDO:  </label></td>
      <td><input type="text" name="apellido" id="apellido" value=<%=cliPerDto.getApellido()%>></td>
    </tr>
    <tr>
      <td><label for="usuario">CUIT: </label></td>
      <td><input type="text" name="cuit" id="cuit" value=<%=cliEmpDto.getCuit()%>></td>
    </tr>
    <tr>
      <td><label for="contra" onfocus="">CONDICIONES ESPECIALES: </label></td>
     <td><input type="text" name="especiales" id="especiales" value=<%=cliPerDto.getCondicionesEspeciales()%>></td>
    </tr>
    <tr>
      <td width="13%"><label for="nombre">TIPO: </label></td>
      <td width="87%"><input type="text" name="tipo" id="tipo" value=<%=tipo%> ></td>
      
    </tr>
    <tr>
      <td width="13%"><label for="nombre">DNI: </label></td>
      <td width="87%"><input type="text" name="dni" id="dni" value=<%=cliPerDto.getDni()%> ></td>
    </tr>
    <tr>
      <td width="13%"><label for="nombre">RAZON SOCIAL: </label></td>
      <td width="87%"><input type="text" name="rsocial" id="rsocial" value=<%=cliEmpDto.getRazonSocial()%>></td>
    </tr>
    <tr>
      <td width="13%"><label for="nombre">ESTADO: </label></td>
      <td width="87%"><input type="text" name="estado" id="estado" value=<%=cliPerDto.getEstado()%>></td>
    </tr>
    <tr>
      <td width="13%"><label for="nombre">TIPO FACTURA: </label></td>
      <td width="87%"><input type="text" name="factura" id="factura" value=<%=cliPerDto.getTipoFactura()%>></td>
    </tr>
    <tr>
      <jsp:include page="Direccion.jsp"></jsp:include>
    </tr>
    <!-- 
    <tr>
      <td> <label for="facturas">TIPO FATURA:</label></td>
      <td><select name="factura" id="factura">
        <option>A</option>
        <option>B</option>
        <option>C</option>
        
      </select></td>
    </tr> 
    <tr>
      <td>Tipo: </td>
      <td><label>
        <input type="radio" name="tipo" value="Empresa" id="tipo_0">
        Empresa</label>
        <br>
        <label>
          <input type="radio" name="tipo" value="Fisica" id="tipo_1">
          Persona</label>
        <br>
      </td>
    </tr> -->
    <tr>
      <td colspan="2" align="center"><input type="submit" name="button" id="button" value="Modificar"></td>
      
      <td colspan="2" align="center"><input type="submit" name="button" id="button" value="Aceptar"></td>
    </tr>
  </table>
  <p><br>
  </p>
</form>
</body>
</html>




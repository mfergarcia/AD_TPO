<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.lang.String"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Resultado del Avance del Pedido</title>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">
<%
	String numPedido = (String)request.getAttribute("numPedido");
	String nuevoEstado = (String)request.getAttribute("nuevoEstado");
%>
		<tr>
			<td><b>Numero de Pedido</b></td>
			<td><b>Nuevo Estado</b></td>
		</tr>	
		<tr>
			<td><%=numPedido%></td>
			<td><%=nuevoEstado%></td>
		</tr>
</table>
</body>
</html>
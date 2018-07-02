<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@page import="dto.PedidoDTO" %>
<%@page import="dto.DireccionDTO" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Pedidos Pendientes de Depósito</title>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">
	<tr>
		<td colspan="4"><b>Lista de Pedidos Pendientes de Depósito</b></td>
	</tr>
	<tr>
		<td><b>Numero de Pedido</b></td>
		<td><b>Fecha Generacion</b></td>
		<td><b>Id Cliente</b></td>
		<td><b>Direccion de Entrega</b></td>
		<td><b>Articulos</b></td>
		
	</tr>
<% 
	PedidoDTO aux;
	int numPedido;
	int idCliente;
	String direccion;
	ArrayList<PedidoDTO> pedidosPendDepo = (ArrayList<PedidoDTO>)request.getAttribute("pedidosPendDepo");
	for (Iterator<PedidoDTO> i = pedidosPendDepo.iterator(); i.hasNext();)
	{
		aux = i.next();
		numPedido = aux.getNumPedido();
		idCliente = aux.getIdCliente();
		direccion = aux.getDirEntrega().toString();
%>
	<tr>
		<td><%=numPedido%></td>
		<td><%=aux.getFechaGen()%></td>
		<td><%=aux.getIdCliente()%></td>
		<td><%=direccion%></td>
		<td>
			<a href="ControladorWeb?action=obtenerItemsPedidoPendDepo&numPedido=<%=numPedido%>">Ver Articulos</a>
		</td>
	</tr>
<% 	} %>
</table>	
</body>
</html>
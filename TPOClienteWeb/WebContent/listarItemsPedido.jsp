<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@page import="dto.ItemArticuloDTO"%>
<%@page import="dto.ArticuloDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Items del Pedido</title>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">
	<tr>
		<td colspan="4"><b>Items del Pedido</b></td>
	</tr>
	<tr>
		<td><b>Num Pedido</b></td>
		<td><b>Codigo Barras Articulo</b></td>
		<td><b>Descripcion Articulo</b></td>
		<td><b>Cantidad</b></td>
		<td><b>Precio Unitario de Venta</b></td>
	</tr>
<% 
	ItemArticuloDTO auxItem;
	ArticuloDTO auxArt;
	ArrayList<ItemArticuloDTO> itemsPedido = (ArrayList<ItemArticuloDTO>)request.getAttribute("itemsPedido"); 
	String numPedido = (String) request.getAttribute("numPedido");
	for (Iterator<ItemArticuloDTO> i = itemsPedido.iterator(); i.hasNext();)
	{
		auxItem = i.next();
		auxArt = auxItem.getArticuloDTO();
		String codBarras = auxArt.getCodigoBarras();
		String descripcion = auxArt.getDescripcion();
		int cant = auxItem.getCant();
		float precioVta = auxItem.getPrecioVta();
%>
	<tr>
		<td><%=numPedido%></td>
		<td><%=codBarras%></td>
		<td><%=descripcion%></td>
		<td><%=cant%></td>
		<td><%=precioVta%></td>
	</tr>
<% 	} %>
</table>	
<table>
		<a href="ControladorWeb?action=prepararPedido&numPedido=<%=numPedido%>">Preparar Pedido</a>
</table>
</body>
</html>
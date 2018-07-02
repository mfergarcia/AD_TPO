<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@page import="dto.ArticuloEnStockDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Articulos En Stock</title>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">
	<tr>
		<td colspan="4"><b>Articulos En Stock</b></td>
	</tr>
	<tr>
		<td><b>Num Pedido</b></td>
		<td><b>Codigo Barras Articulo</b></td>
		<td><b>Cantidad</b></td>
		<td><b>Ubicacion</b></td>
	</tr>
<%
	ArticuloEnStockDTO auxArtEnStock;
	ArrayList<ArticuloEnStockDTO> artEnStock = (ArrayList<ArticuloEnStockDTO>)request.getAttribute("artEnStock");
	String numPedido = (String)request.getAttribute("numPedido");
	System.out.println("Antes de llamar al session");
	session.setAttribute("artEnStockAPreparar", artEnStock);
	System.out.println("Despues de llamar al session");
	for (Iterator<ArticuloEnStockDTO> i = artEnStock.iterator(); i.hasNext();)
	{
		auxArtEnStock = i.next();
		String codBarras = auxArtEnStock.getCodigoBarras();
		int cant = auxArtEnStock.getCantidad();
		String ubicacion = auxArtEnStock.getCodigoUbicacion();
%>
	<tr>
		<td><%=numPedido%></td>
		<td><%=codBarras%></td>
		<td><%=cant%></td>
		<td><%=ubicacion%></td>
	</tr>
<% 	} %>
</table>	
<table>
		<a href="ControladorWeb?action=actualizarStockPorVenta&numPedido=<%=numPedido%>">Actualizar Stock por Venta</a>
</table>
</body>
</html>
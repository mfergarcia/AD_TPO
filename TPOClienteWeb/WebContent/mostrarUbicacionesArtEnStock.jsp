<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@page import="dto.ArticuloEnStockDTO" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Ubicacion Articulos En Stock</title>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">
	<tr>
		<td colspan="4"><b>Ubicaciones en el Stock</b></td>
	</tr>
	<tr>
		<td><b>Codigo de Barras</b></td>
		<td><b>Cantidad</b></td>
		<td><b>Ubicacion</b></td>
	</tr>
<% 
	ArticuloEnStockDTO aux;
	ArrayList<ArticuloEnStockDTO> ubicaciones = (ArrayList<ArticuloEnStockDTO>)request.getAttribute("ubicaciones");
	String numOC = (String)request.getAttribute("numOC");
	for (Iterator<ArticuloEnStockDTO> i = ubicaciones.iterator(); i.hasNext();)
	{
		aux = i.next();
		
%>
	<tr>
		<td><%=aux.getCodigoBarras()%></td>
		<td><%=aux.getCantidad()%></td>
		<td><%=aux.getCodigoUbicacion()%></td>
<% 	} %>
</table>	
<table>
	<td>
		<a href="ControladorWeb?action=ingresarArticulosEnStock&numOC=<%=numOC%>&primeraCarga=1">Continuar Ingreso de Articulos</a>
	</td>
	</tr>
</table>
</body>
</html>
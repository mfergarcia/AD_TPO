<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@ page import= "dto.OrdenDeCompraDTO"%>
<%@ page import= "dto.ItemOCDTO"%>
<%@ page import= "dto.ArticuloDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Orden De Compra</title>
<%
	OrdenDeCompraDTO ordenDeCompra = (OrdenDeCompraDTO)request.getAttribute("ordenDeCompra");
	int numOC = ordenDeCompra.getNumOC();
%>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">

	<tr>
		<td colspan="4"><b>Numero OC: </b></td>
		<td colspan="4"><b><%=numOC%></b></td>
	</tr>

	<tr>
		<td colspan="4"><b>Fecha Generacion: </b></td>
		<td colspan="4"><b><%=ordenDeCompra.getFecha()%></b></td>
	</tr>
		<tr>
		<td colspan="4"><b>Estado: </b></td>
		<td colspan="4"><b><%=ordenDeCompra.getEstado()%></b></td>
	</tr>
</table>	
<p>
<table cellspacing="3" cellpadding="3" border="1" width="500"> 
	<tr>
		<td>Articulo</td>
		<td>Cantidad Compra</td>
	</tr>
<%
	ItemOCDTO auxItemOC;
	ArticuloDTO auxArt;
	for (Iterator<ItemOCDTO> i = ordenDeCompra.getItems().iterator(); i.hasNext(); ) {
		auxItemOC = i.next();
		String codBarras = auxItemOC.getArticulo().getCodigoBarras();
		int cant = auxItemOC.getCantidad();
%>
		<tr>
			<td><%=codBarras%></td>
			<td><%=cant%></td>
		</tr>
<%			
	} 
%>
</table>
</p>
<p>
<table>
	<a href="ControladorWeb?action=ingresarArticulosEnStock&numOC=<%=numOC%>&primeraCarga=0">Ingresar Artículos En Stock</a>
</table>
</p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@page import="dto.ArticuloEnStockDTO"%>
<%@page import="dto.ItemOCDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Ingresar Articulo En Stock</title>
</head>
<body>
<%
	ArrayList<ItemOCDTO> itemsOC = (ArrayList<ItemOCDTO>)session.getAttribute("itemsOC");
	String numOC = (String)request.getAttribute("numOC");
	if (itemsOC.isEmpty()) {
		// Ya se termino la carga de los Articulos En Stock, se ofrece Cumplir la OC
		%>
		<p> Ya se han ingresado todos los Articulos de la Orden De Compra </p>
		<table>
			<a href="ControladorWeb?action=cumplirOrdenDeCompra&numOC=<%=numOC%>">Cumplir Orden De Compra</a>
		</table>
		<%
	}
	else {
		String codBarras = itemsOC.get(0).getArticulo().getCodigoBarras();
		int cantidad = itemsOC.get(0).getCantidad();
		itemsOC.remove(0);
		session.setAttribute("itemsOC", itemsOC);
	%>
	<form method="post" action="ControladorWeb?action=cargarArticulosEnStock&numOC=<%=numOC%>">
		Articulo: <%=codBarras%> <input type="hidden" name="codBarras" value="<%=codBarras%>"><br>
		Cantidad: <%=cantidad%> <input type="hidden" name="cantidad" value="<%=cantidad%>"><br>
		Lote: <input type="text" name="lote" size="30"><br>
		Fecha Vencimiento (yyyy-MM-dd): <input type="text" name="fechaVencimiento" size="30"><br>
		Precio Compra: <input type="text" name="precioCompra" size="30"><br>
		<input type="submit" value="Continuar">
	</form>			
	<%
	}
	%>
</body>
</html>
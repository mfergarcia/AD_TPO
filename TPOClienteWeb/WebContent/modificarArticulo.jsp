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
	<title>Alta Articulo</title>
</head>
<body>

	<form method="post" action="MainServlet?action=modificarArticulo">
		Descripcion: <input type="text" name="Descripcion" size="30"><br>
		Presentacion: <input type="text" name="Presentacion" size="30"><br>
		Tamaño: <input type="text" name="Tamaño" size="30"><br>
		Unidad: <input type="text" name="Unidad" size="30"><br>
		Precio Venta: <input type="text" name="PrecioVenta" size="30"><br>
		Cantidad fija de compra: <input type="text" name="cantFijaCompra" size="30"><br>
		Cantidad máxima ubicación: <input type="text" name="cantMaxUbicacion" size="30"><br>
		Estado: <input type="text" name="Estado" size="30"><br>
		<input type="submit" value="modificarArticulo">
	</form>			
</body>
</html>
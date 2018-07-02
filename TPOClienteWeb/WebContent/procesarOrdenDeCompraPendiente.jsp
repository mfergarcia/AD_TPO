<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Procesar Orden De Compra Pendiente</title>
</head>
<body>
	<form method="post" action="ControladorWeb?action=obtenerDetalleOrdenDeCompra">
		Numero de Orden de Compra a procesar: <input type="text" name="numOC" size="30"><br>
		<input type="submit" value="Consultar">
	</form>			
</body>
</html>
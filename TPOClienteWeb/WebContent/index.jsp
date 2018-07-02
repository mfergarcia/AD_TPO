<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<title>Menu Empleados</title>
</head>
<body>
	
	<a href="ControladorWeb?action=CrearPedido" target="_self">CrearPedido</a>
	
	<br></br>
	
	<a href="ControladorWeb?action=ObtenerPedidosPorCliente" target="_self">Lista de Pedidos por Cliente</a> 

	<br></br>

	<a href="ControladorWeb?action=obtenerPedidosAConfirmar" target="_self">Lista de Pedidos a Confirmar</a> 
	
	<br></br>
	
	<a href="ControladorWeb?action=obtenerPedidosCompletos" target="_self">Lista de Pedidos Completos</a> 
	
	<br></br>

	<a href="ControladorWeb?action=obtenerPedidosPendDepo" target="_self">Lista de Pedidos Pendientes de Depósito</a> 
	
	<br></br>

	<a href="ControladorWeb?action=obtenerPedidosADespachar" target="_self">Lista de Pedidos A Despachar</a> 
	
	<br></br>

	<a href="ControladorWeb?action=procesarOrdenDeCompra" target="_self">Procesar Orden De Compra Pendiente</a> 
	
	<br></br>

</body>
</html>
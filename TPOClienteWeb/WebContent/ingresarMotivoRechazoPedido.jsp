<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Rechazar Pedido</title>
</head>
<body>
<%
	String numPedidoARechazar = (String)request.getAttribute("numPedidoARechazar");
%>
	<form method="post" action="ControladorWeb?action=rechazarPedido&numPedidoARechazar=<%=numPedidoARechazar%>">
		Motivo de Rechazo: <input type="text" name="motivo" size="200"><br>
		<input type="submit" value="Confirmar">
	</form>			
</body>
</html>
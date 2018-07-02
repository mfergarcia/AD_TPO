<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<title>Ingresar Fecha De Entrega</title>
</head>
<body>
<%
	String numPedidoADespachar = (String)request.getAttribute("numPedidoADespachar");
%>
	<form method="post" action="ControladorWeb?action=despacharPedido&numPedido=<%=numPedidoADespachar%>">
		Fecha Entrega del Transportista (yyyy-MM-dd): <input type="text" name="fechaEntrega" size="200"><br>
		<input type="submit" value="Despachar">
	</form>			
</body>
</html>
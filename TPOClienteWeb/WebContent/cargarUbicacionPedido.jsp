<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingrese Ubicación</title>
</head>
<body>
Ingrese la ubicacion de destino del Pedido:
<form action="MainServlet" method="post">
Localidad: <input type="text" name="Localidad" id="Localidad">
<br></br>
Codigo Postal: <input type="text" name="CodigoPostal" id="CodigoPostal">
<br></br>
Calle: <input type="text" name="Calle" id="Calle">
<br></br>
Numero: <input type="text" name="Numero" id="Numero">
<br></br>
<input type="submit" name="action" value="CompletarPedido">
</form>

</body>
</html>
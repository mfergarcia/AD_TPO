<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OBTNER PEDIDOS</title>
<style>

body{
	background-color:#FFC;
}

table{
	background:#FF6;
	padding:10px;
	border:solid 2px #FF0000;
}

{
	padding:5px 0;
}


</style>
</head>
<body>
<h1 style="text-align:center">BUSCAR PEDIDOS X ID CLIENTE:</h1>
<form action="LoginWeb.jr" method="POST">
		<table align="center" width="87%">
			 <tr>
				<td style="font-weight: bold; size: 12px; color: blue;" align="left">ID Cliente : </td>
				<td><input type="text" name= "idCliente" id="idCliente">
			 </tr>
	  	 	 <tr>
				<td colspan="2" width="87%" align="center"><input type="submit" name="action" id="action" value="BuscarXiD"></td>
			 </tr>
		</table>
	</form>
</body>
</html>
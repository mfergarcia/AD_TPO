<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generar OC</title>
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
<h1 style="text-align:center">Generar Orden de compra:</h1>
<form action="LoginWeb.jr" method="POST" >
	<table align="center" width="50%">
	<!-- ingreso valores des tr a tr-->
	
	<!--  creo otro renglon para ingresar el usuario y guardarlo en un input del tipo TEXTO--> 
		<tr>
		<td style="font-weight: bold; size: 12px; color: blue;" align="left">Proveedor : </td>
		<td><input type="text" name= "proveedor" id="proveedor">
		</tr>
		
		<!--  creo un reglon para ingresar el password y guardarlo en un input del tipo TEXTO-->
		<tr>
		<td width="87%" style="font-weight: bold; size: 12px;color: blue;" align="left">Art. CodBarr: </td>
		<td width="87%"><input type="text" name= "articulos" id="articulos">
		</tr>
		
		<!--  creo otro renglon y le asigno un input del tipo BOTON, llamado Cancelar-->
		<tr>
		<!-- botn que invoca la pagina web-->
		<td width="87%"><input type="submit" name="action" id="action" value="GenerarOC"></td>
		
		</tr>
	
	</table>
</form>
</body>
</html>
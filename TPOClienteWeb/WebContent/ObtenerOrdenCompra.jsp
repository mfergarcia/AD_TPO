<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="JavaScript">

function carga(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	document.getElementById("numpedido").focus();
	
	
}



</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Orden Compra</title>
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
<body onload="carga();">
	<h1 style="text-align:center">Obtener Orden De compra:</h1>
	<form action="LoginWeb.jr" method="POST" >
		<table align="center" width="50%">
  	 		<tr>
     			<td width="13%"><label for="nombre">Numero OC: </label></td>
     			<td width="28%"><input type="text" name="obtenerOc" id="obtenerOc"></td>
     	 		<td width="87%"><input type="submit" name="action" id="action" value="BuscarOC"></td>
   			 </tr>
    	</table>
	</form>
</body>
</html>
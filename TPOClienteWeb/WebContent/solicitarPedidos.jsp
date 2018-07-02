<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar PEDIDOS</title>
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
<h1 style="text-align:center">Solicitar Pedido por Numero de Pedido:</h1>
<form action="LoginServlet" method="POST">
		<table align="center" width="87%">
  	 		   			
			<tr>
				<td colspan="2" width="20%" align="center">Numero Pedido: <input type="text" name="numPedido" id="numPedido"></td>
      			<td colspan="2" width="20%" align="left"><input type="submit" name="action" id="action" value="SolPedidos"></td>
   			</tr>
			
			
    	</table>
	</form>
</body>
</html>
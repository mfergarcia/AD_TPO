<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Documento sin título</title>
<style>

body{
	background-color:#FFC;
}

table{
	background:#FF6;
	padding:10px;
	border:solid 2px #FF0000;
}

td{
	padding:5px 0;
}




</style>
<script type="text/javascript" language="JavaScript">
function carga(){
	//seteo por id "usuario" que el focos se realice sobre usuario
	document.getElementById("usuario").focus();
	
	
}

function valideDatos(){
	//genero var para tomar los valores dentro de los jtext para poder copararlos luego
	var usuario = document.getElementById("usuario");
	var password = document.getElementById("password")
	
	//usuario.value obtengo el valor 
	if ((usuario.value == "" || usuario.value == '' || usuario.value == null) &&
			(password.value == "" || password.value ==''|| password.value == null)){
		//muestro mensaje en patalla
		alert("ingresa tu usuario y password");
		usuario.focus();
		return false; 
	}else if (password.value == "" || password.value == '' || password.value == null){
		//muestro mensaje en patalla
		alert("ingresa tu password");
		password.focus();
		return false;
	}else if(usuario.value =="" || usuario.value =='' || usuario.value == null){
		//muestro mensaje en patalla
		alert("ingresa tu usuario");
		usuario.focus();
		return false;
	} else {
		return true;
	}
}


</script>
</head>

<body onload="carga();">
<h1 style="text-align:center">Bienvenido, Por favor Ingrese su Usuario y contraseña: </h1>
<form action= "LoginServlet" method="POST" onsubmit="return valideDatos();">

  <table align="center" width="25%">
    <tr>
      <td><label for="usuario">Usuario: </label></td>
      <td><input type="text" name="usuario" id="usuario"></td>
    </tr>
    <tr>
      <td><label for="contra">Contraseña: </label></td>
      <td><input type="password" name="password" id="password"></td>
    </tr>
    <tr>
      <td>Tipo: </td>
  
      <td colspan="2" align="center">
      <label>
      	<select>
      	<option>Empresa</option>
      	<option>Consumidor Final</option>
      	</select>
      	</label>
      	</td>
   
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" name="button" id="button" value="Enviar"></td>
	 
	  <td colspan="2" align="center"><input type="submit" name="cancelar" id="cancelar" value="Cancelar"></td>
  
      <td Colspan="2" align="center"><input type="submit" name="registrar" id="registrar" value="Registrarse"></td>
     </tr>
  
  </table>
  <p><br>
  </p>
  
  
</form>
</body>
</html>

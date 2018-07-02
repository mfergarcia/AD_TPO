<%@page import= "dto.ClienteEmpresaDTO"%>
<%@page import= "dto.ClientePersonaDTO"%>

<html>

<%
ClienteEmpresaDTO cliEmpDto = (ClienteEmpresaDTO)session.getAttribute("datos");
ClientePersonaDTO cliPerDto = (ClientePersonaDTO)request.getAttribute("datos1");
%>
<body>
<br/>
<tr>
      <td width="13%"><label for="nombre">DIRECCION: </label><br/></td>
</tr>
<tr>      
      <td width="13%"><label for="nombre">CALLE: </label><br/></td>
      <td width="87%"><input type="text" name="calle" id="calle" value=<%=cliEmpDto.getDireccionFacturacion().getCalle() %>></td>
</tr>
<tr>      
      <td width="13%"><label for="nombre">NUMERO: </label><br/></td>
      <td width="87%"><input type="text" name="calle" id="calle" value=<%=cliEmpDto.getDireccionFacturacion().getNumero() %>></td>
</tr>
<tr>      
      <td width="13%"><label for="nombre">CP: </label></td>
      <td width="87%"><input type="text" name="calle" id="calle" value=<%=cliEmpDto.getDireccionFacturacion().getCodigoPostal() %>></td>
</tr>
<tr>
      <td width="13%"><label for="nombre">LOCALIDAD: </label></td>
      <td width="87%"><input type="text" name="calle" id="calle" value = <%=cliEmpDto.getDireccionFacturacion().getLocalidad() %>></td>
</tr>


<!-- 
Aqui ira el contenido de la web<br/>
Aqui ira el contenido de la web<br/>
Aqui ira el contenido de la web<br/>
Aqui ira el contenido de la web<br/>
Aqui ira el contenido de la web<br/>
Aqui ira el contenido de la web<br/>
 -->

</body>


</html>
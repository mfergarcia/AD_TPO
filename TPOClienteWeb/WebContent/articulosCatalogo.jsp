<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="delegados.SistemaBD" %>
<%@page import="excepciones.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Collection" %>
<%@page import="dto.ArticuloDTO" %>
<%@page import="dto.ItemArticuloDTO" %>
<%@page import="dto.PedidoDTO" %>
<%@page import="dto.DireccionDTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver Articulos</title>
 <a href="Controlador?action=CrearPedido" target="_self"></a>
<% 
try{
SistemaBD bd= new SistemaBD();
Collection<ArticuloDTO> la= new ArrayList<ArticuloDTO>();
la= bd.obtenerCatalogo();
%>

</head>
<body>
Catálogo
<select name="Articulo">
<option>Elije el articulo!</option>
<%
for(ArticuloDTO a: la){
%>
<option value="<%a.getCodigoBarras();%>"><%=a.getDescripcion()%></option>
<%
}
%>
</select>
Cantidad: <input type="text" name="Cantidad" id="Cantidad">
<td colspan="2"><input type="submit" value="Aceptar" onclick="ControladorWeb?action=ElegirArticulo" ></td>
<input type="submit" value="Completar Pedido" onclick="ControladorWeb?action=CompletarPedido">
</body>

<%
}catch (ExcepcionComunicacion e) {
	System.out.println(e.getMensaje());
}catch (ExcepcionSistema es) {
	System.out.println(es.getMensaje());
}
%>
</html>
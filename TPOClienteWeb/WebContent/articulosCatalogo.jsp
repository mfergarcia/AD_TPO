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

<% 
try{
SistemaBD bd= new SistemaBD();
Collection<ArticuloDTO> la= new ArrayList<ArticuloDTO>();
la= bd.obtenerCatalogo();
Collection<ItemArticuloDTO> li= new ArrayList<ItemArticuloDTO>();
PedidoDTO p= (PedidoDTO) request.getAttribute("pedido");
%>
<%!
public void agegarArticulo(String codBarras, int cantidad, PedidoDTO p ){
try{
SistemaBD bd= new SistemaBD();
ArticuloDTO a=bd.obtenerArticulo(codBarras);
ItemArticuloDTO i= new ItemArticuloDTO();
i.setArticuloDTO(a);
i.setCant(cantidad);
p.agregarItem(i);
	}catch (ExcepcionComunicacion e) {
		System.out.println(e.getMensaje());
	}catch (ExcepcionSistema es) {
		System.out.println(es.getMensaje());
	}

}


%>

</head>
<body>


Catálogo
<form action="ControladorWeb" method="post">
<select name="Articulo">
<option>Elije el articulo!</option>
<%
for(ArticuloDTO a: la){
String codBarras= a.getCodigoBarras();
%>
<option value="<%=codBarras%>"><%=a.getDescripcion()%></option>
<%

}
%>
</select>
<br></br>
Cantidad: <input type="text" name="Cantidad" id="Cantidad">
<br></br>
<input type="submit" name="action" value="ElegirArticulo" onclick="agregarArticulo('Articulo','Cantidad', p)">
<br></br>
<input type="submit" name="action" value="CompletarPedido">
</form>
</body>

<%
}catch (ExcepcionComunicacion e) {
	System.out.println(e.getMensaje());
}catch (ExcepcionSistema es) {
	System.out.println(es.getMensaje());
}
%>
</html>
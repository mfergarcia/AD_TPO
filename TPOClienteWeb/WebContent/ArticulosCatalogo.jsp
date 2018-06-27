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
<%! 
public void AgregarArticulo(ArticuloDTO a, int cantidad, PedidoDTO p){
	ItemArticuloDTO i= new ItemArticuloDTO();
	i.setArticuloDTO(a);
	i.setCant(cantidad);
	p.agregarItem(i);
}

%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver Articulos</title>
<% 
try{
SistemaBD bd= new SistemaBD();
Collection<ArticuloDTO> la= new ArrayList<ArticuloDTO>();
la= bd.obtenerCatalogo();
PedidoDTO p= new PedidoDTO();
DireccionDTO dirEntrega = new DireccionDTO();
dirEntrega.setCalle("Av de Mayo");
dirEntrega.setNumero(200);
dirEntrega.setCodigoPostal("1424");
dirEntrega.setLocalidad("C.A.B.A.");
p.setDirEntrega(dirEntrega);
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
<button type="submit" value="Submit" onclick="AgregarArticulo(bd.obtenerArticulo('Articulo'),Integer.parseInt('Cantidad'), p);">Aceptar</button>
<button type="submit" value="Submit" onclick="bd.generarPedido(p);">Generar Pedido</button>
</body>

<%
}catch (ExcepcionComunicacion e) {
	System.out.println(e.getMensaje());
}catch (ExcepcionSistema es) {
	System.out.println(es.getMensaje());
}
%>
</html>
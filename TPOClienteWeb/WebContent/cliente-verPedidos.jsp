<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dto.PedidoDTO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Collection" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver Pedidos</title>
</head>
<body>
Pedidos:
<br></br>
<%
Collection<PedidoDTO> lp=(Collection<PedidoDTO>)request.getAttribute("pedidos");
for(PedidoDTO p: lp){
%>
Número: <%=p.getNumPedido() %>
<br></br>
Estado: <%=p.getEstado() %>
<br></br>
<%
}
%>

</body>
</html>
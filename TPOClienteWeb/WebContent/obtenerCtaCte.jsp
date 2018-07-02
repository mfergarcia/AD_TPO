<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.ArrayList"%>
<%@ page import= "dto.CtaCteDTO"%>
<%@ page import= "dto.FacturaDTO"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<%
	String idClienteCtaCte = (String)request.getAttribute("idClienteCtaCte");
%>
	<title>Cuenta Corriente del Cliente <%=idClienteCtaCte%></title>
</head>
<body>
<table cellspacing="3" cellpadding="3" border="1" width="500">
<%
	CtaCteDTO ctaCte = (CtaCteDTO)request.getAttribute("ctaCte");
%>
	<tr>
		<td colspan="4"><b>Cliente: </b></td>
		<td colspan="4"><b><%=idClienteCtaCte%></b></td>
	</tr>

	<tr>
		<td colspan="4"><b>Limite de Credito: </b></td>
		<td colspan="4"><b><%=ctaCte.getLimiteCredito()%></b></td>
	</tr>
		<tr>
		<td colspan="4"><b>Saldo: </b></td>
		<td colspan="4"><b><%=ctaCte.getSaldo()%></b></td>
	</tr>
</table>	
<p>
<table>
	<%	
		String numPedido = (String)request.getAttribute("numPedido");
	 %>
	<a href="ControladorWeb?action=aprobarPedido&numPedido=<%=numPedido%>">Aprobar Pedido</a>
	<b>                             </b>
	<b>                             </b>
	<a href="ControladorWeb?action=ingresarMotivoRechazo&numPedido=<%=numPedido%>">Rechazar Pedido</a>
</table>
</p>
<%
	if (!ctaCte.getFacturas().isEmpty()) {
		%> <table cellspacing="3" cellpadding="3" border="1" width="500"> 
				<tr>
					<td>Tipo Factura</td>
					<td>Numero Factura</td>
					<td>Fecha Factura</td>
					<td>Importe</td>
					<td>Monto Adeudado</td>
				</tr>
		<%
		FacturaDTO aux;
		for (Iterator<FacturaDTO> i = ctaCte.getFacturas().iterator(); i.hasNext(); )
		{
			aux = i.next();
			%>
			<tr>
				<td><%=aux.getTipoFactura()%></td>
				<td><%=aux.getNumFactura()%></td>
				<td><%=aux.getFechaFactura()%></td>				
				<td><%=aux.getImporte()%></td>
				<td><%=aux.getMontoAdeudado()%></td>
			</tr>
			<%			
		} 
		%> </table> <% 
	}
%>
</body>
</html>
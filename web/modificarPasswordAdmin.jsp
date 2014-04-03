<%@page import="uo.sdi.model.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<TITLE>Catmpus Virtual</TITLE>
</HEAD>
<BODY background="img/background2.jpg">
	<br></br>
	<table align="center">
		<TD align="center"><IMG SRC="img/modificarPerfil.png"></TD>
		<td>
			<form action="controlador" method="post">
				<div align="center">
					<input type="submit" value="Logout"> <input type="hidden"
						name="opc" value="logout">
				</div>
			</form>
		</td>
	</table>
	<br></br>
	<FORM ACTION="controlador" METHOD="POST">
		<table BORDER="5" align="center">
			<tr>
				<th>Nueva contraseña</th>
				<th><input id="nueva" type="password" name="nueva"
					required="true" class="texto" /></th>
			</tr>
			<tr>
				<th>Repetir contraseña</th>
				<th><input id="repetir" type="password" name="repetir"
					required="true" class="texto" /></th>
			</tr>
			<TR>
				<TD><input type="submit" value="Guardar"> <input
					type="hidden" name="opc" value="modificarPasswordAdmin"></TD>
			</TR>
		</table>
	</FORM>
	<%
		if (request.getAttribute("ERROR") != null) {
	%>
	<div style="color: red;" align="center"font-weight:bold;">
		<%
			Object error = request.getAttribute("ERROR");
				out.println(error.toString());
		%>
	</div>
	<%
		}
	%>
	<div align="center">
		<a href="modificarUsuarioAdmin.jsp">Atrás</a>
	</div>

</BODY>
</HTML>
<%
	request.setAttribute("ERROR", null);
%>
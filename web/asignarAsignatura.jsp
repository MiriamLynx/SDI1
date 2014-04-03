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
		<TD align="center"><IMG SRC="img/asignacion.png"></TD>
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
	<table BORDER="5" align="center">
		<FORM ACTION="controlador" METHOD="POST">
			<tr>
				<th>Codigo Asignatura</th>
				<th><input id="idAsignatura" type="text" name="idAsignatura"
					required="true" class="texto"
					value="<%out.print(request.getSession().getAttribute("idEditar"));%>" /></th>

			</tr>
			<tr>
				<th>ID profesor</th>
				<th><input id="idProfesor" type="text" name="idProfesor"
					required="true" class="texto" /></th>
			</tr>
			<tr>
				<TD><input type="submit" value="Asignar"> <input
					type="hidden" name="opc" value="asignarImparte"></TD>
			</tr>
		</FORM>
		<tr>
		</tr>
	</table>
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
	<%
		if (request.getAttribute("EXITO") != null) {
	%>
	<div style="color: blue;" align="center"font-weight:bold;">
		<%
			Object exito = request.getAttribute("EXITO");
				out.println(exito.toString());
		%>
	</div>
	<%
		}
	%>
	<div align="center">
		<a href="gestionAsignaturas.jsp">Atrás</a>
	</div>
</BODY>
</HTML>
<%
	request.setAttribute("ERROR", null);
	request.setAttribute("EXITO", null);
%>
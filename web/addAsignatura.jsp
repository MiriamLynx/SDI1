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
		<TD align="center"><IMG SRC="img/nuevaAsignatura.png"></TD>
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
				<th>Codigo</th>
				<th><input id="Id" type="text" name="Id" required="true"
					class="texto" /></th>

			</tr>
			<tr>
				<th>Nombre</th>
				<th><input id="Nombre" type="text" name="Nombre"
					required="true" class="texto" /></th>
			</tr>
			<tr>
				<th>Curso</th>
				<th><input id="Curso" type="text" name="Curso" required="true"
					class="texto" /></th>
			</tr>
			<tr>
				<th>Creditos</th>
				<th><input id="Creditos" type="text" name="Creditos"
					required="true" class="texto" /></th>
			</tr>
			<TR>
				<TD><input type="submit" value="Guardar"> <input
					type="hidden" name="opc" value="añadirAsignatura"></TD>
			</TR>
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
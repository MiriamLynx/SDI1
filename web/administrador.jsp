<%@page import="uo.sdi.model.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<TITLE>Catmpus Virtual</TITLE>
</HEAD>
<BODY background="img/background2.jpg">
	<table align="center">
		<td><br></br>
			<H1>
				Bienvenid@ administrador
				<%
				out.println(((Usuario) request.getSession().getAttribute("usuario"))
						.getNombre());
			%>
			</H1></td>
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
		<tr>
			<th>Usuario</th>
			<th>
				<%
					out.println(((Usuario) request.getSession().getAttribute("usuario"))
							.getId());
				%>
			</th>
		</tr>
		<tr>
			<th>Nombre</th>
			<th>
				<%
					out.println(((Usuario) request.getSession().getAttribute("usuario"))
							.getNombre());
				%>
			</th>
		</tr>
		<tr>
			<th>Apellidos</th>
			<th>
				<%
					out.println(((Usuario) request.getSession().getAttribute("usuario"))
							.getApellidos());
				%>
			</th>
		</tr>
		<tr>
			<th>Correo</th>
			<th>
				<%
					out.println(((Usuario) request.getSession().getAttribute("usuario"))
							.getCorreo());
				%>
			</th>
		</tr>
		<TR>
			<TD><a href="editarPerfil.jsp">Modificar mis datos</a></TD>
			</form>
		</TR>
	</table>
	<br></br>
	<form action="controlador" method="post">
		<div align="center">
			<input type="submit" value="Gestion de usuarios"> <input
				type="hidden" name="opc" value="gestionarUsuarios">
		</div>
	</form>
	<form action="controlador" method="post">
		<div align="center">
			<input type="submit" value="Gestion de asignaturas"> <input
				type="hidden" name="opc" value="gestionarAsignaturas">
		</div>
	</form>
	<form action="controlador" method="post">
		<div align="center">
			<input type="submit" value="Gestion de profesores"> <input
				type="hidden" name="opc" value="gestionarProfesores">
		</div>
	</form>
	<form action="controlador" method="post">
		<div align="center">
			<input type="submit" value="Gestion de matriculas"> <input
				type="hidden" name="opc" value="gestionarMatriculas">
		</div>
	</form>
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
</BODY>
</HTML>
<%
	request.setAttribute("ERROR", null);
	request.setAttribute("EXITO", null);
%>

<%@page import="uo.sdi.model.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<TITLE>Catmpus Virtual</TITLE>
</HEAD>
<BODY background="img/background2.jpg">
	<br></br>
	<div align="center">
		<table align="center">
			<TD align="center"><IMG SRC="img/profesores.png"></TD>
			<td>
				<form action="controlador" method="post">
					<div align="center">
						<input type="submit" value="Logout"> <input type="hidden"
							name="opc" value="logout">
					</div>
				</form>
			</td>
		</table>
		<table BORDER="5" align="center">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Correo</th>
				<th>Asignaturas</th>
			</tr>

			<%
				List<Profesor> profesores = (List<Profesor>) request.getSession()
						.getAttribute("profesores");
				if (profesores.size() > 0) {
					for (Profesor p : profesores) {
			%>
			<tr>
				<td>
					<%
						out.println(p.getNombre().toString());
					%>
				</td>
				<td>
					<%
						out.println(p.getApellidos());
					%>
				</td>
				<td>
					<%
						out.println(p.getCorreo());
					%>
				</td>
				<%
					String asignaturas = "";
							for (Asignatura a : p.getAsignaturas()) {
								asignaturas += a.getNombre() + ", ";
							}
				%>
				<td>
					<%
						out.println(asignaturas);
					%>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idProfesor"
							value="<%out.print(p.getId());%>"> <input type="submit"
							value="Asignar asignatura"> <input type="hidden"
							name="opc" value="guardarContextoProfesor">
					</form>
				</td>
			</tr>
			<%
				}
			%>
			<%
				}
			%>
		</table>
		<div align="center">
			<a href="administrador.jsp">Atrás</a>
		</div>
	</div>
</BODY>
</HTML>
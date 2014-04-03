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
		<TD align="center"><IMG SRC="img/expediente.png"></TD>
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
			<th>Nombre</th>
			<th>Creditos</th>
			<th>Curso</th>
			<th>Profesores</th>
			<th>Calificación</th>
			<th>Código de la asignatura</th>
		</tr>

		<%
			List<Matricula> matriculas = (List<Matricula>) request.getSession()
					.getAttribute("matriculas");
			if (matriculas.size() > 0) {
				for (Matricula m : matriculas) {
		%>
		<tr>
			<td>
				<%
					out.println(m.getAsignatura().getNombre());
				%>
			</td>
			<td>
				<%
					out.println(m.getAsignatura().getCreditos());
				%>
			</td>
			<td>
				<%
					out.println(m.getAsignatura().getCurso());
				%>
			</td>
			<%
				String profesores = "";
						for (Profesor p : m.getAsignatura().getProfesores()) {
							profesores += p.getNombre() + " " + p.getApellidos()
									+ ", ";
						}
			%>
			<td>
				<%
					out.println(profesores);
				%>
			</td>
			<td>
				<%
					if (m.getCalificacion() == 12) {
								out.println("Sin calificar");
							} else {
								if (m.getCalificacion() == 11) {
									out.println("No presentado");
								} else {
									out.println(m.getCalificacion());
								}
							}
				%>
			</td>
			<td>
				<%
					out.println(m.getAsignatura().getId());
				%>
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
		<a href="alumno.jsp">Atrás</a>
	</div>
	</div>
</BODY>
</HTML>
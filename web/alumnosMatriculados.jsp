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
		<TD align="center"><IMG SRC="img/alumnos.png"></TD> <br></br> <br></br>
		<table BORDER="5">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Correo</th>
				<th>Calificación</th>
			</tr>

			<%
				List<Alumno> alumnos = (List<Alumno>) request.getSession()
						.getAttribute("alumnosMatriculados");
				if (alumnos.size() > 0) {
					for (Alumno a : alumnos) {
			%>
			<tr>
				<td>
					<%
						out.println(a.getNombre());
					%>
				</td>
				<td>
					<%
						out.println(a.getApellidos());
					%>
				</td>
				<td>
					<%
						out.println(a.getCorreo());
					%>
				</td>
				<%
					int calificacion = a.getMatricula().get(0)
									.getCalificacion();
				%>
				<td align="center">
					<%
						if (calificacion == 12) {
									out.print("Sin calificar");
								} else {
									if (calificacion == 11) {
										out.print("No presentado");
									} else {
										out.print(calificacion);
									}
								}
					%>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="alumno"
							value="<%out.print(a.getId());%>"> <input type="text"
							name="nuevaNota" required="true"> <input type="submit"
							value="Modificar nota"> <input type="hidden" name="opc"
							value="modificarNota">
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
		<H4>Introduza 11 para no presentado y 12 para sin calificar</H4>
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
			<a href="asignaturasImpartidas.jsp">Atrás</a>
		</div>
	</div>
</BODY>
</HTML>
<%
	request.setAttribute("EXITO", null);
%>
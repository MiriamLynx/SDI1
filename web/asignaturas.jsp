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
		<TD align="center"><IMG SRC="img/asignaturas.png"></TD> <br></br>
		<br></br>
		<FORM ACTION="controlador" METHOD="POST">
			<td><label for="criterio">Nombre:</label> <input id="criterio"
				type="text" name="criterio" required="true" /> <input type="submit"
				value="Buscar"> <input type="hidden" name="opc"
				value="listarAsignaturasPorNombre"></td>
		</FORM>
		<FORM ACTION="controlador" METHOD="POST">
			<td><input type="submit" value="Mostrar todas"> <input
				type="hidden" name="opc" value="verTodasAsignaturas"></td>
		</FORM>
		<table BORDER="5">
			<tr>
				<th>Nombre</th>
				<th>Creditos</th>
				<th>Curso</th>
				<th>Profesores</th>
			</tr>

			<%
				List<Asignatura> asignaturas = (List<Asignatura>) request
						.getAttribute("asignaturas");
				if (asignaturas.size() > 0) {
					for (Asignatura a : asignaturas) {
			%>
			<tr>
				<td>
					<%
						out.println(a.getNombre().toString());
					%>
				</td>
				<td>
					<%
						out.println(a.getCreditos());
					%>
				</td>
				<td>
					<%
						out.println(a.getCurso());
					%>
				</td>
				<%
					String profesores = "";
							for (Profesor p : a.getProfesores()) {
								profesores += p.getNombre() + " " + p.getApellidos()
										+ ", ";
							}
				%>
				<td>
					<%
						out.println(profesores);
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
			<a href="index.jsp">Atrás</a>
		</div>
	</div>
</BODY>
</HTML>
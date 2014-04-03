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
			<TD align="center"><IMG SRC="img/asignaturas.png"></TD>
			<td>
				<form action="controlador" method="post">
					<div align="center">
						<input type="submit" value="Logout"> <input type="hidden"
							name="opc" value="logout">
					</div>
				</form>
			</td>
		</table>
		<FORM ACTION="controlador" METHOD="POST">
			<td><label for="criterio">Nombre o apellido:</label> <input
				id="criterio" type="text" name="criterio" required="true" /> <input
				type="submit" value="Buscar"> <input type="hidden"
				name="opc" value="listarAsignaturasPorNombreAdmin"></td>
		</FORM>
		<FORM ACTION="controlador" METHOD="POST">
			<td><input type="submit" value="Mostrar todas"> <input
				type="hidden" name="opc" value="verTodasAsignaturasAdmin"></td>
		</FORM>
		<table BORDER="5" align="center">
			<tr>
				<TD align="right"><a href="addAsignatura.jsp">Añadir
						asignatura</a></TD>
			</tr>
			<tr>
				<th>Nombre</th>
				<th>Creditos</th>
				<th>Curso</th>
				<th>Profesores</th>
			</tr>

			<%
				List<Asignatura> asignaturas = (List<Asignatura>) request
						.getSession().getAttribute("asignaturas");
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
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idAsignatura"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Editar"> <input type="hidden" name="opc"
							value="guardarContextoAsignatura">
					</form>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idAsignatura"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Asignar profesor"> <input type="hidden" name="opc"
							value="guardarContextoAsignarAsignatura">
					</form>
					<form action="controlador" method="post">
						<input type="hidden" name="idAsignatura"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Añadir alumno"> <input type="hidden" name="opc"
							value="guardarContextoAddAlumno">
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
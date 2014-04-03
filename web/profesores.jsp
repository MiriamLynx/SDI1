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
		<TD align="center"><IMG SRC="img/profesores.png"></TD> <br></br>
		<br></br>
		<td>
			<FORM ACTION="controlador" METHOD="POST">
				<label for="criterio">Nombre o apellido:</label> <input
					id="criterio" type="text" name="criterio" required="true" /> <input
					type="submit" value="Buscar"> <input type="hidden"
					name="opc" value="listarProfesoresPorNombreYApellido">
			</FORM>
			<FORM ACTION="controlador" METHOD="POST">
				<td><input type="submit" value="Mostrar todos"> <input
					type="hidden" name="opc" value="verTodosProfesores"></td>
			</FORM>
		</td>
		<table BORDER="5">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Correo</th>
				<th>Asignaturas</th>
			</tr>

			<%
				List<Profesor> profesores = (List<Profesor>) request
						.getAttribute("profesores");
				if (profesores.size() > 0) {
					for (Profesor p : profesores) {
			%>
			<tr>
				<td>
					<%
						out.println(p.getNombre());
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
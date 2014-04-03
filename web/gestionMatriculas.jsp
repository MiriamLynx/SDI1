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
		<TD align="center"><IMG SRC="img/alumnos.png"></TD>
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
			</tr>

			<%
				List<Alumno> alumnos = (List<Alumno>) request.getSession()
						.getAttribute("alumnos");
				if(alumnos.size() > 0){
				for (Alumno a : alumnos) {
			%>
			<tr>
				<td>
					<%
						out.println(a.getNombre().toString());
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
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idAlumno"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Matricular en asignatura"> <input type="hidden"
							name="opc" value="guardarContextoAlumno">
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
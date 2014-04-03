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
		<table BORDER="5">
			<tr>
				<th>Nombre</th>
				<th>Creditos</th>
				<th>Curso</th>
			</tr>

			<%
				List<Asignatura> asignaturas = (List<Asignatura>) request
						.getSession().getAttribute("asignaturasImpartidas");
				if (asignaturas.size() > 0) {
					for (Asignatura a : asignaturas) {
			%>
			<tr>
				<td>
					<%
						out.println(a.getNombre());
					%>
				</td>
				<td align="center">
					<%
						out.println(a.getCreditos());
					%>
				</td>
				<td align="center">
					<%
						out.println(a.getCurso());
					%>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="asignatura"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Ver alumnos"> <input type="hidden" name="opc"
							value="verAlumnos">
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
			<a href="profesor.jsp">Atrás</a>
		</div>
	</div>
</BODY>
</HTML>
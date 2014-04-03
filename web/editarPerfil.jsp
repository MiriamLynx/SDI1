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
		<TD align="center"><IMG SRC="img/modificarPerfil.png"></TD>
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
				<th>Usuario</th>
				<th>
					<%
						out.print(((Usuario) request.getSession().getAttribute("usuario"))
								.getId());
					%>
				</th>
			</tr>
			<tr>
				<th>Nombre</th>
				<th><input id="nombre" type="text" name="nombre"
					required="true" class="texto"
					value="<%out.print(((Usuario) request.getSession().getAttribute("usuario"))
					.getNombre());%>" />
				</th>
			</tr>
			<tr>
				<th>Apellidos</th>
				<th><input id="apellidos" type="text" name="apellidos"
					required="true" class="texto"
					value="<%out.print(((Usuario) request.getSession().getAttribute("usuario"))
					.getApellidos());%>" />
				</th>
			</tr>
			<tr>
				<th>Correo</th>
				<th><input id="correo" type="text" name="correo"
					required="true" class="texto"
					value="<%out.print(((Usuario) request.getSession().getAttribute("usuario"))
					.getCorreo());%>" />
				</th>
			</tr>
			<TR>
				<TD><input type="submit" value="Guardar"> <input
					type="hidden" name="opc" value="modificarDatosUsuario"> <a
					href="modificarPassword.jsp">Modificar contraseña</a></TD>
			</TR>
		</FORM>
	</table>
	<c:if test="${not empty ERROR}">
		<div style="color: red; font-weight: bold;">
			<%
				Object error = request.getAttribute("ERROR");
					out.println(error.toString());
			%>
		</div>
	</c:if>
	<div align="center">
		<%
			if ((((Usuario) request.getSession().getAttribute("usuario"))
					.getPrivilegios()).equals("alumno")) {
		%>
		<a href="alumno.jsp">Atrás</a>
		<%
			}
		%>
	</div>
	<div align="center">
		<%
			if ((((Usuario) request.getSession().getAttribute("usuario"))
					.getPrivilegios()).equals("profesor")) {
		%>
		<a href="profesor.jsp">Atrás</a>
		<%
			}
		%>
	</div>
	<div align="center">
		<%
			if ((((Usuario) request.getSession().getAttribute("usuario"))
					.getPrivilegios()).equals("administrador")) {
		%>
		<a href="administrador.jsp">Atrás</a>
		<%
			}
		%>
	</div>
</BODY>
</HTML>
<%
	request.setAttribute("ERROR", null);
%>

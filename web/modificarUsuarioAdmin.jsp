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
				<th><input id="nuevoId" type="text" name="nuevoId"
					required="true" class="texto"
					value="<%out.print(request.getSession().getAttribute("idEditar"));%>" />
				</th>

			</tr>
			<tr>
				<th>Nombre</th>
				<th><input id="nuevoNombre" type="text" name="nuevoNombre"
					required="true" class="texto"
					value="<%out.print(request.getSession().getAttribute("nombreEditar"));%>" />
				</th>
			</tr>
			<tr>
				<th>Apellidos</th>
				<th><input id="nuevoApellidos" type="text"
					name="nuevoApellidos" required="true" class="texto"
					value="<%out.print(request.getSession().getAttribute("apellidosEditar"));%>" />
				</th>
			</tr>
			<tr>
				<th>Correo</th>
				<th><input id="nuevoCorreo" type="text" name="nuevoCorreo"
					required="true" class="texto"
					value="<%out.print(request.getSession().getAttribute("correoEditar"));%>" />
				</th>
			</tr>
			<TR>
				<TD><input type="submit" value="Guardar"> <input
					type="hidden" name="opc" value="modificarDatosUsuarioAdmin">
					<a href="modificarPasswordAdmin.jsp">Modificar contraseña</a></TD>
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
		<a href="gestionUsuarios.jsp">Atrás</a>
	</div>
</BODY>
</HTML>
<%
	request.setAttribute("EXITO", null);
%>
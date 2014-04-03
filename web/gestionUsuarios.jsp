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
			<TD align="center"><IMG SRC="img/usuarios.png"></TD>
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
				name="opc" value="listarUsuariosPorNombreYApellido"></td>
		</FORM>
		<FORM ACTION="controlador" METHOD="POST">
			<td><input type="submit" value="Mostrar todas"> <input
				type="hidden" name="opc" value="verTodosUsuarios"></td>
		</FORM>
		<table BORDER="5" align="center">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Correo</th>
				<th>Activado</th>
			</tr>
			<%
				List<Usuario> desactivados = (List<Usuario>) request.getSession()
						.getAttribute("usuariosDesactivados");
				if (desactivados.size() > 0) {
					for (Usuario a : desactivados) {
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
				<td align="center">
					<%
						out.print("No");
					%>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idUsuario"
							value="<%out.print(a.getId());%>"> <input type="hidden"
							name="activacion" value="<%out.print("false");%>"><input
							type="submit" value="Activar/Desactivar"> <input
							type="hidden" name="opc" value="activarODesactivar">
					</form>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idUsuario"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Editar"> <input type="hidden" name="opc"
							value="guardarContexto">
					</form>
				</td>
			</tr>
			<%
				}
			%>
			<%
				}
			%>
			<%
				List<Usuario> activados = (List<Usuario>) request.getSession()
						.getAttribute("usuariosActivados");
				if (activados.size() > 0) {
					for (Usuario a : activados) {
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
				<td align="center">
					<%
						out.print("Si");
					%>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idUsuario"
							value="<%out.print(a.getId());%>"> <input type="hidden"
							name="activacion" value="<%out.print("true");%>"> <input
							type="submit" value="Activar/Desactivar"> <input
							type="hidden" name="opc" value="activarODesactivar">
					</form>
				</td>
				<td>
					<form action="controlador" method="post">
						<input type="hidden" name="idUsuario"
							value="<%out.print(a.getId());%>"> <input type="submit"
							value="Editar"> <input type="hidden" name="opc"
							value="guardarContexto">
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
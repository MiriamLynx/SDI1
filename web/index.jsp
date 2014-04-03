<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<TITLE>Catmpus Virtual</TITLE>
</HEAD>
<BODY background="img/background2.jpg">
	<TABLE ALIGN="CENTER">
		<TR>
			<TD align="center"><IMG SRC="img/titulo1.png"></TD>
		</TR>
		<TR>
			<TD align="center"><img src="img/titulo2.png"></TD>
		</TR>
		<TR>
			<TD align="center"><img src="img/titulo3.png"></TD>
		</TR>
	</TABLE>
	<br>
	<TABLE ALIGN="CENTER">
		<TR>
			<TD ALIGN="LEFT">
				<FORM ACTION="controlador" METHOD="POST">
					<div>
						<label for="usuario">Usuario:</label> <input id="usuario"
							type="text" name="usuario" required="true" /> <label
							for="password">Contraseña:</label> <input id="password"
							type="password" name="password" required="true" />
					</div>
					<div>
						<input type="submit" value="Enviar"> <input type="hidden"
							name="opc" value="loguear">
					</div>
					</div>
					<c:if test="${not empty ERROR}">
						<div style="color: red; font-weight: bold;">
							<%
								Object error = request.getAttribute("ERROR");
									out.println(error.toString());
							%>
						</div>
					</c:if>
					<c:if test="${not empty EXITO}">
						<div style="color: blue; font-weight: bold;">
							<%
								Object exito = request.getAttribute("EXITO");
									out.println(exito.toString());
							%>
						</div>
					</c:if>
			</TD>
		</TR>
		</FORM>
		<TR>
			<TD><a href="registrarUsuario.jsp">Registrarse</a></TD>
		</TR>
		<form action="controlador" method="post">
			<TR>
				<TD><input type="submit" value="Conoce nuestras asignaturas"></TD>
			</TR>
			<input type="hidden" name="opc" value="listarAsignaturas">
		</form>
		<form action="controlador" method="post">
			<TR>
				<TD><input type="submit" value="Conoce nuestros profesores"></TD>
			</TR>
			<TR>
				<TD align="center"><IMG SRC="img/neko.png"></TD>
			</TR>
			<input type="hidden" name="opc" value="listarProfesores">
		</form>
	</TABLE>
</BODY>
</HTML>
<%
	request.setAttribute("ERROR", null);
%>

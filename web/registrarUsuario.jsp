<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<TITLE>Catmpus Virtual</TITLE>
</HEAD>
<BODY background="img/background2.jpg">
	<FORM ACTION="controlador" METHOD="POST">
		<TABLE ALIGN="CENTER">
			<TD align="center"><IMG SRC="img/registro.png"></TD>
			<TR>
				<TD ALIGN="left"><br></br>
					<H2>Introduzca sus datos:</H2> <SELECT NAME="privilegios" SIZE="1">
						<OPTION VALUE="alumno">Alumno</OPTION>
						<OPTION VALUE="profesor">Profesor</OPTION>
				</SELECT>
					<div>
						<label for="id">Identificador*:</label> <input id="id" type="text"
							name="id" required="true" class="texto" /> <a>(ej.: (UOXXX))</a>
					</div>
					<div>
						<label for="nombre">Nombre*:</label> <input id="nombre"
							type="text" name="nombre" required="true" class="texto" />
					</div>
					<div>
						<label for="apellidos">Apellidos*:</label> <input id="apellidos"
							type="text" name="apellidos" required="true" class="texto" />
					</div>
					<div>
						<label for="correo">Correo electrónico*:</label> <input
							id="correo" type="text" name="correo" required="true"
							class="texto" />
					</div>
					<div>
						<label for="password">Contraseña*:</label> <input id="password"
							type="password" name="password" required="true" class="texto" />
					</div>
					<div>
						<label for="repetirPassword">Repetir contraseña*:</label> <input
							id="repetirPassword" type="password" name="repetirPassword"
							required="true" class="texto" />
					</div>
					<div>
						<input type="submit" value="Enviar"> <input type="hidden"
							name="opc" value="registrarUsuario">
					</div>
					<div>
						<H4>(*) Campos obligatorios</H4>
					</div> <c:if test="${not empty ERROR}">
						<div style="color: red; font-weight: bold;">
							<%
								Object error = request.getAttribute("ERROR");
									out.println(error.toString());
							%>
						</div>
					</c:if>
					</div> <c:if test="${not empty EXITO}">
						<div style="color: blue; font-weight: bold;">
							<%
								Object exito = request.getAttribute("EXITO");
									out.println(exito.toString());
							%>
						</div>
					</c:if>
			</TR>
		</TABLE>
		<div align="center">
			<a href="index.jsp">Atrás</a>
		</div>
	</FORM>
</BODY>
<%
	request.setAttribute("ERROR", null);
	request.setAttribute("EXITO", null);
%>
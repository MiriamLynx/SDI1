package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.BusinessException;
import uo.sdi.persistence.Persistencia;
import uo.sdi.util.Check;

public class RegistrarUsuarioAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String id = request.getParameter("id");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			String privilegios = request.getParameter("privilegios");
			String repetirPassword = request.getParameter("repetirPassword");

			assertPasswordsCoinciden(request, password, repetirPassword);

			Persistencia.insertarUsuario(id, nombre, apellidos, correo,
					password, privilegios);

			request.setAttribute("EXITO", "Usuario creado correctamente :)");

		} catch (SQLException e) {
			request.setAttribute("ERROR", "Ya existe un usuario con ese ID");
			return "FRACASO";
		} catch (BusinessException bex) {
			return "FRACASO";
		}
		return "EXITO";
	}

	private void assertPasswordsCoinciden(HttpServletRequest request,
			String password, String repetirPassword) throws BusinessException {
		if (!Check.passwordsCoinciden(password, repetirPassword)) {
			request.setAttribute("ERROR", "Las contraseñas no coinciden");
			throw new BusinessException("Cuenta inactiva");
		}
	}

}

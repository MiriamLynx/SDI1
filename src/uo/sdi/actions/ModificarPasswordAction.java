package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.BusinessException;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;
import uo.sdi.util.Check;

public class ModificarPasswordAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute(
					"usuario");

			String actual = request.getParameter("actual");
			String nueva = request.getParameter("nueva");
			String repetir = request.getParameter("repetir");

			assertValidez(request, actual, nueva, repetir,
					usuario.getPassword());

			Persistencia.modificarPassword(nueva, (String) request.getSession()
					.getAttribute("id"));

			usuario.setPassword(nueva);

			request.getSession().setAttribute("usuario", usuario);

			request.setAttribute("EXITO", "Contraseña guardada correctamente");

			String privilegios = usuario.getPrivilegios();

			switch (privilegios) {

			case "alumno":
				return "EXITO_ALUMNO";

			case "profesor":
				return "EXITO_PROFESOR";

			case "administrador":
				return "EXITO_ADMINISTRADOR";

			}

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		} catch (BusinessException e) {
			return "FRACASO";
		}
		return "EXITO";
	}

	private void assertValidez(HttpServletRequest request, String actual,
			String nueva, String repetir, String verdadera)
			throws BusinessException {
		if (!Check.nuevaContraseñaValida(actual, nueva, repetir, verdadera)) {
			request.setAttribute("ERROR",
					"Los datos introducidos no son validos");
			throw new BusinessException("Datos invalidos");
		}
	}
}

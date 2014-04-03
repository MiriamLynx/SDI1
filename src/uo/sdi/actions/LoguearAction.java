package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.BusinessException;
import uo.sdi.model.Usuario;
import uo.sdi.util.Check;

public class LoguearAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String id = request.getParameter("usuario");
			String password = request.getParameter("password");

			Usuario usuario = Check.getUsuarioById(id);

			assertExisteUsuario(request, id);

			assertPasswordCorrecta(request, usuario, password);

			assertCuentaActiva(request, usuario);

			request.getSession().setAttribute("usuario", usuario);

			String privilegios = usuario.getPrivilegios();

			switch (privilegios) {

			case "alumno":
				return "EXITO_ALUMNO";

			case "profesor":
				return "EXITO_PROFESOR";

			case "administrador":
				return "EXITO_ADMINISTRADOR";

			}

			return "EXITO";

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		} catch (BusinessException bex) {
			return "FRACASO";
		}

	}

	private void assertCuentaActiva(HttpServletRequest request, Usuario usuario)
			throws BusinessException {
		if (!Check.cuentaActiva(usuario)) {
			request.setAttribute(
					"ERROR",
					"Su cuenta no está activa ahora mismo, póngase en contacto con el administrador del sistema");
			throw new BusinessException("Cuenta inactiva");
		}
	}

	private void assertPasswordCorrecta(HttpServletRequest request,
			Usuario usuario, String password) throws BusinessException {
		if (!Check.passwordCorrecta(usuario, password)) {
			request.setAttribute("ERROR",
					"La contraseña introducida no es correcta");
			throw new BusinessException("Contraseña incorrecta");
		}
	}

	private void assertExisteUsuario(HttpServletRequest request, String id)
			throws BusinessException, SQLException {
		if (!Check.yaExisteId(id)) {
			request.setAttribute("ERROR",
					"El usuario introducido no existe");
			throw new BusinessException("El usuario no existe");
		}
	}
}

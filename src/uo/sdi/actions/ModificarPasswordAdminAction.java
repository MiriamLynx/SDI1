package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.BusinessException;
import uo.sdi.persistence.Persistencia;
import uo.sdi.util.Check;

public class ModificarPasswordAdminAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idUsuario = (String) request.getSession().getAttribute(
					"idEditar");

			String nueva = request.getParameter("nueva");
			String repetir = request.getParameter("repetir");

			assertValidez(request, nueva, repetir);

			Persistencia.modificarPassword(nueva, idUsuario);

			request.setAttribute("EXITO", "Contraseña guardada correctamente");

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		} catch (BusinessException e) {
			return "FRACASO";
		}

		return "EXITO";
	}

	private void assertValidez(HttpServletRequest request, String nueva,
			String repetir) throws BusinessException {
		if (!Check.nuevaContraseñaValidaAdmin(nueva, repetir)) {
			request.setAttribute("ERROR", "Las contraseñas no coinciden");
			throw new BusinessException("Datos invalidos");
		}
	}
}

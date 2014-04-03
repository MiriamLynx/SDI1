package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Asignatura;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class VerAsignaturasAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Usuario usuario = (Usuario) request.getSession().getAttribute(
					"usuario");

			List<Asignatura> asignaturas = Persistencia
					.getAsignaturasByProfesorId(usuario.getId());

			request.getSession().setAttribute("asignaturasImpartidas",
					asignaturas);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";

	}

}

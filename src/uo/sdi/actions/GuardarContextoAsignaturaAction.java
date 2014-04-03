package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Asignatura;
import uo.sdi.persistence.Persistencia;

public class GuardarContextoAsignaturaAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idAsignatura = request.getParameter("idAsignatura");

			Asignatura asignatura = Persistencia
					.getAsignaturaById(idAsignatura);

			request.getSession().setAttribute("idEditar", idAsignatura);

			request.getSession().setAttribute("nombreEditar",
					asignatura.getNombre());

			request.getSession().setAttribute("cursoEditar",
					asignatura.getCurso());

			request.getSession().setAttribute("creditosEditar",
					asignatura.getCreditos());

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}

}

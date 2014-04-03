package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Asignatura;
import uo.sdi.persistence.Persistencia;

public class ListarAsignaturasAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			List<Asignatura> asignaturas = Persistencia.listarAsignaturas();

			request.setAttribute("asignaturas", asignaturas);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}

}

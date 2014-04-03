package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class GuardarContextoAlumnoAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idAlumno = request.getParameter("idAlumno");

			Usuario alumno = Persistencia.getUsuarioById(idAlumno);

			request.getSession().setAttribute("alumnoAsignar", alumno);

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}

}

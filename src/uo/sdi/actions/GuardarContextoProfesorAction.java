package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class GuardarContextoProfesorAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idProfesor = request.getParameter("idProfesor");

			Usuario profesor = Persistencia.getUsuarioById(idProfesor);

			request.getSession().setAttribute("profesorAsignar", profesor);

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}
}

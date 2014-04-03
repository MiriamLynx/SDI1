package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Profesor;
import uo.sdi.persistence.Persistencia;

public class GestionarProfesoresAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			List<Profesor> profesores = Persistencia.listarProfesores();

			request.getSession().setAttribute("profesores", profesores);

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}

}

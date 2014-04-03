package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Profesor;
import uo.sdi.persistence.Persistencia;
import alb.util.log.Log;

public class BuscarProfesoresPorNombreYApellidoAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String criterio = request.getParameter("criterio");
			
			List<Profesor> profesores = Persistencia
					.buscarProfesoresPorNombreYApellido(criterio);
			request.setAttribute("profesores", profesores);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}

}

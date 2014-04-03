package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Matricula;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class VerExpedienteAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Usuario usuario = (Usuario) request.getSession().getAttribute(
					"usuario");

			List<Matricula> matriculas = Persistencia
					.encontrarExpediente(usuario);

			request.getSession().setAttribute("matriculas", matriculas);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}

}

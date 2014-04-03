package uo.sdi.actions;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.*;
import uo.sdi.persistence.*;

public class ListarProfesoresAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			List<Profesor> profesores = Persistencia.listarProfesores();
			request.setAttribute("profesores", profesores);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}
}

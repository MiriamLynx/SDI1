package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Asignatura;
import uo.sdi.persistence.Persistencia;

public class ModificarAsignaturaAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String viejoId = (String) request.getSession().getAttribute(
					"idEditar");
			String id = request.getParameter("nuevoId");
			String nombre = request.getParameter("nuevoNombre");
			String curso = request.getParameter("nuevoCurso");
			String creditos = request.getParameter("nuevoCreditos");

			Persistencia.modificarAsignatura(id, nombre, curso, creditos,
					viejoId);

			List<Asignatura> asignaturas = Persistencia.listarAsignaturas();

			request.getSession().setAttribute("asignaturas", asignaturas);

			request.setAttribute("EXITO", "Datos guardados correctamente");

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}

}

package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Asignatura;
import uo.sdi.model.Profesor;
import uo.sdi.persistence.Persistencia;

public class AsignarImparticionAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idProfesor = request.getParameter("idProfesor");

			String idAsignatura = request.getParameter("idAsignatura");

			List<Asignatura> asignaturas = Persistencia
					.getAsignaturasByProfesorId(idProfesor);

			for (Asignatura a : asignaturas) {
				if (a.getId().equals(idAsignatura)) {
					request.setAttribute("ERROR",
							"Ese profesor ya imparte esa asignatura");
					return "FRACASO";
				}
			}

			Persistencia.asignarImparticion(idProfesor, idAsignatura);

			List<Profesor> profesores = Persistencia.listarProfesores();

			request.getSession().setAttribute("profesores", profesores);

			asignaturas = Persistencia.listarAsignaturas();

			request.getSession().setAttribute("asignaturas", asignaturas);

			request.setAttribute("EXITO", "Profesor asignado correctamente");

		} catch (SQLException e) {
			request.setAttribute("ERROR", "El valor introducido no es válido");
			return "FRACASO";
		}
		return "EXITO";
	}

}

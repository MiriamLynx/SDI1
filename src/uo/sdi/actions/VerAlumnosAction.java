package uo.sdi.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Alumno;
import uo.sdi.model.Matricula;
import uo.sdi.persistence.Persistencia;

public class VerAlumnosAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String idAsignatura = request.getParameter("asignatura");

			request.getSession().setAttribute("idAsignatura", idAsignatura);

			List<Alumno> alumnos = Persistencia
					.getAlumnosByAsignaturaId(idAsignatura);

			for (Alumno a : alumnos) {

				List<Matricula> matriculas = Persistencia
						.encontrarExpediente(a);

				List<Matricula> matriculaDeEstaAsignatura = new ArrayList<Matricula>();

				for (Matricula m : matriculas) {

					if (m.getAsignatura().getId().equals(idAsignatura)) {

						matriculaDeEstaAsignatura.add(m);

					}

				}

				a.setMatricula(matriculaDeEstaAsignatura);
			}

			request.getSession().setAttribute("alumnosMatriculados", alumnos);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";

	}

}

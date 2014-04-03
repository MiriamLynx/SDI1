package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Matricula;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class AsignarMatriculaAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idAlumno = request.getParameter("idAlumno");

			Usuario alumno = Persistencia.getUsuarioById(idAlumno);

			if (alumno == null) {
				request.setAttribute("ERROR", "El alumno introducido no existe");
				return "FRACASO";
			}

			String idAsignatura = request.getParameter("idAsignatura");

			List<Matricula> matriculas = Persistencia
					.encontrarExpediente(alumno);

			for (Matricula m : matriculas) {
				if (m.getAsignatura().getId().equals(idAsignatura)) {
					request.setAttribute("ERROR",
							"Ese alumno ya esta matriculado en esa asignatura");
					return "FRACASO";
				}
			}

			Persistencia.asignarMatricula(idAlumno, idAsignatura);

			request.setAttribute("EXITO", "Alumno matriculado correctamente");

		} catch (SQLException e) {
			request.setAttribute("ERROR", "El valor introducido no es válido");
			return "FRACASO";
		}
		return "EXITO";
	}

}

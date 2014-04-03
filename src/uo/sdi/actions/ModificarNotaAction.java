package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Alumno;
import uo.sdi.model.Matricula;
import uo.sdi.persistence.Persistencia;

public class ModificarNotaAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String idAlumno = request.getParameter("alumno");

			int nuevaNota = Integer.parseInt(request.getParameter("nuevaNota"));

			@SuppressWarnings("unchecked")
			List<Alumno> alumnos = (List<Alumno>) request.getSession()
					.getAttribute("alumnosMatriculados");

			for (Alumno a : alumnos) {

				if (a.getId().equals(idAlumno)) {
					Persistencia.modificarNota((Matricula) a.getMatricula()
							.get(0), nuevaNota);
					((Matricula) a.getMatricula().get(0))
							.setCalificacion(nuevaNota);
					request.setAttribute("EXITO", "Nota guardada");
					request.getSession().setAttribute("alumnosMatriculados",
							alumnos);
					return "EXITO";

				}

			}

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "FRACASO";
	}
}
